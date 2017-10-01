package hello.storage


import java.nio.file.Path
import java.util.stream.Stream
import java.nio.file.Files
import java.io.IOException
import java.net.MalformedURLException
import java.nio.file.StandardCopyOption
import java.nio.file.Paths

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.util.FileSystemUtils
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile

interface StorageService {

    fun init()

    fun store(file: MultipartFile)

    fun loadAll(): Stream<Path>

    fun load(filename : String): Path

    fun loadAsResource(filename : String): Resource

    fun deleteAll()

}

@ConfigurationProperties("storage")
class StorageProperties {

    /**
     * Folder location for storing files
     */
    val location = "upload-dir";
}

@Service
class FileSystemStorageService(properties:StorageProperties): StorageService {

	var rootLocation : Path
	init {
        rootLocation = Paths.get(properties.location)
    }

    override fun store(file: MultipartFile) {
        val filename : String = StringUtils.cleanPath(file.getOriginalFilename())
        try {
            if (file.isEmpty()) {
                throw StorageException("Failed to store empty file ${filename}")
            }
            if (filename.contains("..")) {
                // This is a security check
                throw StorageException(
                        "Cannot store file with relative path outside current directory ${filename}");
            }
            Files.copy(file.getInputStream(), rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
        }
        catch (e : IOException) {
            throw StorageException("Failed to store file ${filename}", e);
        }
    }

    override fun loadAll(): Stream<Path> {
        try {
            return Files.walk(rootLocation, 1)
                    .filter {path -> !path.equals(rootLocation)}
                    .map {path -> rootLocation.relativize(path)};
        }
        catch (e : IOException) {
            throw StorageException("Failed to read stored files", e);
        }

    }

    override fun load(filename:String): Path = rootLocation.resolve(filename)

    override fun loadAsResource(filename:String): Resource {
        try {
            val file:Path = load(filename)
            val resource: Resource = UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw StorageFileNotFoundException(
                        "Could not read file: ${filename}");

            }
        }
        catch (e : MalformedURLException) {
            throw StorageFileNotFoundException("Could not read file: ${filename}", e);
        }
    }

    override fun deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    override fun init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (e : IOException) {
            throw StorageException("Could not initialize storage", e);
        }
    }
}


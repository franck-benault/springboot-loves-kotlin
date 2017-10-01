package hello

import hello.storage.StorageFileNotFoundException
import hello.storage.StorageProperties
import hello.storage.StorageService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import java.util.stream.Collectors


@SpringBootApplication
@EnableConfigurationProperties(StorageProperties::class)
open class Application 

@Bean
fun init(storageService: StorageService) = CommandLineRunner {
		storageService.deleteAll()
		storageService.init()
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}


@Controller
class FileUploadController(var storageService : StorageService) {

    @GetMapping("/")
    fun listUploadedFiles(model : Model) : String {

        model.addAttribute("files", storageService.loadAll().map 
                { path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController::class.java,
                        "serveFile", path.getFileName().toString()).build().toString()} 
                .collect(Collectors.toList()))

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    fun serveFile(@PathVariable filename : String) : ResponseEntity<Resource> {

        var file: Resource = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    fun handleFileUpload(@RequestParam("file") file : MultipartFile,
            redirectAttributes : RedirectAttributes) : String {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException::class)
    fun handleStorageFileNotFound(exc : StorageFileNotFoundException): ResponseEntity<Any> {
        return ResponseEntity.notFound().build();
    }

}

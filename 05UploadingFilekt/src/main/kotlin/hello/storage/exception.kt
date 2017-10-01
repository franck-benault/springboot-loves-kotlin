package hello.storage

open class StorageException(message : String, cause:Throwable?=null):
		RuntimeException(message, cause)

class StorageFileNotFoundException(message : String, cause:Throwable?=null):
		StorageException(message, cause)


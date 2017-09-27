package hello

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.util.concurrent.atomic.AtomicLong
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
open class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}

class Greeting(val id: Long, val content:String) 


@RestController
class GreetingController(val counter: AtomicLong = AtomicLong()) {

    @RequestMapping("/greeting")
    fun greeting(@RequestParam(value="name", defaultValue="World") name : String) 
        = Greeting(counter.incrementAndGet(),"Hello, ${name}!");
    
}


package hello

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong
import org.springframework.web.bind.annotation.ResponseBody

@SpringBootApplication
open class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}

class Greeting(val id: Long, val content:String) 

@Service
open class GreetingService {
    fun greetTemplate() = "Hello, %s!"
    
}

@RestController
class GreetingController(val counter: AtomicLong = AtomicLong()) {

	@Autowired
	lateinit var service : GreetingService
	
    @RequestMapping("/greeting")
    fun greeting(@RequestParam(value="name", defaultValue="World") name : String) 
        = Greeting(counter.incrementAndGet(),
			String.format(service.greetTemplate(), name))
			
    
}

@RestController
class HomeController {
    @RequestMapping("/")
    fun greeting() = "Hello World"
}


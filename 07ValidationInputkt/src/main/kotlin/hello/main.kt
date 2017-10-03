package hello

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult

import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@SpringBootApplication
open class Application 


fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}

class PersonForm() {
    @NotNull
    @Size(min=2, max=30)
    var name : String=""
    @NotNull
    @Min(18)
    var age : Int? =null

    override fun toString() =  "Person(Name: ${name}, Age: ${age})"
}

@Controller
class WebController() : WebMvcConfigurerAdapter() {

    override fun addViewControllers(registry : ViewControllerRegistry) {
    	registry.addViewController("/results").setViewName("results")
	}

    @GetMapping("/")
    fun showForm(personForm : PersonForm) = "form"

    @PostMapping("/")
    fun checkPersonInfo(@Valid personForm: PersonForm, bindingResult: BindingResult): String {

        if (bindingResult.hasErrors()) {
            return "form"
        }

        return "redirect:/results"
    }
}
package hello

import hello.marketplace.MarketPlace
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import javax.annotation.PostConstruct

@SpringBootApplication
open class Application(
	@Qualifier("ANDROID") val android: MarketPlace,
	@Qualifier("IOS") val ios: MarketPlace
) {
	
	@PostConstruct
	fun qualifyTheTweets() {
		System.out.println("ios: ${ios}")
		System.out.println("android: ${android}")
	}

} 


fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
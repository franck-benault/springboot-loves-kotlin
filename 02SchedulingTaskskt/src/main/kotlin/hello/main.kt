package hello

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.Date
import mu.KotlinLogging

@SpringBootApplication
@EnableScheduling
open class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}

@Component
class ScheduledTasks {

	private val log = KotlinLogging.logger {}

	companion object {
		private val dateFormat = SimpleDateFormat("HH:mm:ss")
	}

	@Scheduled(fixedRate = 5000)
	fun scheduleFixedRate() =
		log.info("Fixed rate task - {}", dateFormat.format(Date()))

    @Scheduled(fixedDelay = 1000)
    fun scheduleFixedDelayTask() =
    	log.info("Fixed delay task - {}",System.currentTimeMillis() / 1000);
    
    
    @Scheduled(cron = "0 * * * * ? ")
    fun scheduleTaskUsingCronExpression() =
    	log.info("Cron expression {}",System.currentTimeMillis() / 1000)

}
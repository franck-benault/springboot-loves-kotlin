package hello.health

import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.stereotype.Component
import java.util.Random

@Component(value="first health")
class HealthCheck1 : HealthIndicator {

	override fun health() : Health {
        var errorCode = check()
        if (errorCode > 8) {
            return Health.down()
              .withDetail("Error Code", errorCode).build()
        }
        return Health.up().withDetail("Low value", errorCode).build()
    }
}

@Component(value="second health")
class HealthCheck2 : HealthIndicator {

	override fun health() : Health {
        var errorCode = check()
        if (errorCode > 8) {
            return Health.down()
              .withDetail("Error Code", errorCode).build()
        }
        return Health.up().withDetail("Low value", errorCode).build()
    }
}

fun check() : Int {
   var rand = Random(); 
   return rand.nextInt(10); 
}
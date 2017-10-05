package hello.health;

import java.util.Random;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component(value="second health")
public class HealthCheck2 implements HealthIndicator {

	@Override
    public Health health() {
        int errorCode = check(); // perform some specific health check
        if (errorCode > 8) {
            return Health.down()
              .withDetail("Error Code", errorCode).build();
        }
        return Health.up().withDetail("Low value", errorCode).build();
    }
     
    public int check() {
        // Your logic to check health
    	Random rand = new Random(); 
    	return rand.nextInt(10); 
        
    }
}

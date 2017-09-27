package hello;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void scheduleFixedRate() {
        log.info("Fixed rate task - {}", dateFormat.format(new Date()));
    }
    
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() {
    	log.info("Fixed delay task - {}",System.currentTimeMillis() / 1000);
    }
    
    @Scheduled(cron = "0 * * * * ? ")
    public void scheduleTaskUsingCronExpression() {
      
    	log.info("Cron expression {}",System.currentTimeMillis() / 1000);
    }

}

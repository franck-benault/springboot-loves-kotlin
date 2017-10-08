package hello;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
    	System.out.println("Starting springboot application");
        SpringApplication.run(Application.class);
    }
}
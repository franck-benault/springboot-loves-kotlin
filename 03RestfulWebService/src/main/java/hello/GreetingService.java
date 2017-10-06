package hello;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public String greetTemplate() {
        return "Hello, %s!";
    }
}
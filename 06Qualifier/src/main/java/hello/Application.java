package hello;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hello.marketplace.MarketPlace;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	@Qualifier("ANDROID")
	private MarketPlace android;

	@Autowired
	@Qualifier("IOS")
	private MarketPlace ios;

	@PostConstruct
	public void qualifyTheTweets() {
		System.out.println("ios:" + this.ios);
		System.out.println("android:" + this.android);
	}
}
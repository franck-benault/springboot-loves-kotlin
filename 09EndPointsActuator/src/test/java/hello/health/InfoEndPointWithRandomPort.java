package hello.health;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class InfoEndPointWithRandomPort {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


	@Test
	public void testGreetingNoName() throws Exception {
		
		String jsonContent = this.restTemplate.getForObject("http://localhost:" + port + "/info",
                String.class);
		
        assertThat(jsonContent).contains("This is my first spring boot application");      
        assertThatJson(jsonContent).node("app.description").isEqualTo("This is my first spring boot application");
		
	}

	


}

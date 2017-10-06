package hello;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GreetingControllerWithRandomPort {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


	@Test
	public void testGreetingNoName() throws Exception {
		
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/greeting",
                String.class)).contains("Hello, World!");
		
	}

	
	@Test
	public void testGreetingWithName() throws Exception {

	    assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/greeting?name=toto",
	                String.class)).contains("Hello, toto!");

	}

}

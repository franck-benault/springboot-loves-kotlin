package hello

import org.assertj.core.api.Assertions.*

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GreetingControllerWithRandomPortTest {

    @LocalServerPort
    lateinit var port : Integer

    @Autowired
    lateinit var restTemplate : TestRestTemplate

	@Test
	fun testGreetingNoName() {
		
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/greeting",
                String::class.java)).contains("Hello, World!");
		
	}

	
	@Test
	fun testGreetingWithName() {

	    assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/greeting?name=toto",
	                String::class.java)).contains("Hello, toto!");

	}

}

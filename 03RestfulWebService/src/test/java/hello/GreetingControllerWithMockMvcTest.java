package hello;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static net.javacrumbs.jsonunit.spring.JsonUnitResultMatchers.json;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
public class GreetingControllerWithMockMvcTest {

	@Autowired
	private MockMvc mockMvc;

    @MockBean
    private GreetingService service;


	@Test
	public void testGreetingNoName() throws Exception {
        when(service.greetTemplate()).thenReturn("Hello, %s!");
		this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
			.andExpect(json().node("content").isEqualTo("Hello, World!"));
	}

	
	@Test
	public void testGreetingWithName() throws Exception {
        when(service.greetTemplate()).thenReturn("Hello, %s!");
		this.mockMvc.perform(get("/greeting?name=toto")).andDo(print()).andExpect(status().isOk())
				.andExpect(json().node("content").isEqualTo("Hello, toto!"));
	}

}

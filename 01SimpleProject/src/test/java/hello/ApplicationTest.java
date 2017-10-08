package hello;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;


public class ApplicationTest {
	
	@Rule
	public OutputCapture capture = new OutputCapture();

	@Test
	public void testMain() throws Exception {
		Application.main(new String[0]);
		assertThat(capture.toString(), containsString("Starting springboot application"));
		
	}
	
}

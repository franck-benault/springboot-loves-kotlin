package hello;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

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

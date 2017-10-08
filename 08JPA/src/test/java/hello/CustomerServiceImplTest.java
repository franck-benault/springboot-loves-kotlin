package hello;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
public class CustomerServiceImplTest {
	
	@Before
	public void setUp() {
	    Customer alex = new Customer("alex", "Nemo");
	    Customer jan = new Customer("jan", "Nemo");	    
	 
	    Mockito.when(repository.findByLastName(alex.getLastName()))
	      .thenReturn(Arrays.asList(alex,jan));
	}
	

	 
	    @TestConfiguration
	    static class CustomerServiceImplTestContextConfiguration {
	  
	        @Bean
	        public CustomerService customerService() {
	            return new CustomerServiceImpl();
	        }
	    }
	 
	    @Autowired
	    private CustomerService service;
	 
	    @MockBean
	    private CustomerRepository repository;
	    
	    @Test
	    public void testFindFirstNameByLastName() {
	    	List<String> res = service.findFirstNameByLastName("Nemo");
	    	
	    	assertThat(res).isNotEmpty().contains("alex","jan").hasSize(2);
	    }

}

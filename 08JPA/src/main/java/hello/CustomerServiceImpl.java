package hello;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService  {
	
	@Autowired
    private CustomerRepository customerRepository;

	@Override
	public List<String> findFirstNameByLastName(String lastName) {
		List<String> res = customerRepository.findByLastName(lastName).stream().map(Customer::getFirstName).collect(Collectors.toList());
		return res;
	}

}

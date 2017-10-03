package hello

import mu.KotlinLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.repository.CrudRepository
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@SpringBootApplication
open class Application {
	@Bean
	open fun init(repository: CustomerRepository) = CommandLineRunner {
		var log = KotlinLogging.logger {}
		// save a couple of customers
		repository.save(Customer("Jack", "Bauer"));
		repository.save(Customer("Chloe", "O'Brian"));
		repository.save(Customer("Kim", "Bauer"));
		repository.save(Customer("David", "Palmer"));
		repository.save(Customer("Michelle", "Dessler"));

		// fetch all customers
		log.info("Customers found with findAll():");
		log.info("-------------------------------");
		for (customer in repository.findAll()) {
			log.info(customer.toString());
		}
		log.info("");

		// fetch an individual customer by ID
		var customer = repository.findOne(1L);
		log.info("Customer found with findOne(1L):");
		log.info("--------------------------------");
		log.info(customer.toString());
		log.info("");

		// fetch customers by last name
		log.info("Customer found with findByLastName('Bauer'):");
		log.info("--------------------------------------------");
		for (bauer in repository.findByLastName("Bauer")) {
			log.info(bauer.toString());
		}
		log.info("");
	}
}


fun main(args: Array<String>) {
	SpringApplication.run(Application::class.java, *args)
}


@Entity
class Customer(
		var firstName: String? = null,
		var lastName: String? = null,
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		var id: Long? = null
) {


	override fun toString() =
			String.format(
					"Customer[id=%d, firstName='%s', lastName='%s']",
					id, firstName, lastName)


}


interface CustomerRepository : CrudRepository<Customer, Long> {

	fun findByLastName(lastName: String): List<Customer>
}

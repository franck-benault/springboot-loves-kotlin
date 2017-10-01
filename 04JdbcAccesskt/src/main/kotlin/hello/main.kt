package hello


import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.jdbc.core.JdbcTemplate
import java.util.Arrays

//see example here https://github.com/MarioAriasC/kt-relational-data-access


@SpringBootApplication
open class Application:CommandLineRunner{

	@Autowired lateinit var jdbcTemplate: JdbcTemplate
	
	private val log = KotlinLogging.logger {}
	
    override fun run(strings:Array<String>) {

        log.info("Creating tables")

        with(jdbcTemplate) {
        	execute("DROP TABLE customers IF EXISTS")
        	execute("""
        		CREATE TABLE customers(
        				id SERIAL,
        				first_name VARCHAR(255),
        				last_name VARCHAR(255)
        		)"""
			)
        }

        // Split up the array of whole names into an array of first/last names
		val splitUpNames = arrayOf("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").map { name -> name.split(" ").toTypedArray() }


        // Use a Java 8 stream to print out each tuple of the list
        splitUpNames.forEach {log.info(String.format("Inserting customer record for ${it[0]} ${it[1]}"))}
		

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames)

        log.info("Querying for customer records where first_name = 'Josh':")
		jdbcTemplate.query("SELECT id, first_name, last_name FROM customers WHERE first_name = ?",
				arrayOf("Josh")) { rs, rowNum -> Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        }.forEach { customer -> log.info(customer.toString()) }
    }	
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}

data class Customer(val id:Long, val firstName:String, val lastName:String) 
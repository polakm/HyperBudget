package pl.com.michalpolak.hyperbudget.transaction.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionService;
import pl.com.michalpolak.hyperbudget.transaction.data.InMemoryTransactionRepository;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionRepository;

@SpringBootApplication
@Configuration
@ComponentScan("pl.com.michalpolak")
@EnableWebMvc
public class TransactionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionServiceApplication.class, args);
	}

	@Bean
	TransactionRepository transactionRepositoryBean(){
		return new InMemoryTransactionRepository();
	}

	@Bean
	TransactionService transactionServiceBean(@Autowired TransactionRepository transactionRepository){
		return new BasicTransactionService(transactionRepository);
	}

}

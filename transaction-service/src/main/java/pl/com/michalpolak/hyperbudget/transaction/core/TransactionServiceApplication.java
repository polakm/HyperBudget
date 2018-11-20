package pl.com.michalpolak.hyperbudget.transaction.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionValidator;
import pl.com.michalpolak.hyperbudget.transaction.data.InMemoryTransactionRepository;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Configuration
@ComponentScan("pl.com.michalpolak")
@EnableWebMvc
@EnableDiscoveryClient
public class TransactionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionServiceApplication.class, args);
    }

    @Bean
    public TransactionRepository transactionRepositoryBean() {
        return new InMemoryTransactionRepository();
    }

    @Bean
    public TransactionValidator transactionValdiatorBean() {

        List<ValidationRule> rules = new ArrayList<>();
        rules.add(new AmountIsRequired());
        rules.add(new ExecutionDateIsRequired());
        rules.add(new AccountIsRequired());
        return new BasicTransactionValidator(rules);
    }

    @Bean
    @Autowired
    public TransactionService transactionServiceBean(TransactionRepository transactionRepository, TransactionValidator transactionValidator) {

        return new BasicTransactionService(transactionRepository, transactionValidator);
    }

}

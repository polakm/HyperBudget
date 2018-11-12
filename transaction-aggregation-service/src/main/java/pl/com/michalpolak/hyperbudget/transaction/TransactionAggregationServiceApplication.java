package pl.com.michalpolak.hyperbudget.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionRepository;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionValidator;
import pl.com.michalpolak.hyperbudget.transaction.data.InMemoryTransactionRepository;

@SpringBootApplication
@Configuration
@ComponentScan("pl.com.michalpolak")
@EnableWebMvc
public class TransactionAggregationServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(TransactionAggregationServiceApplication.class, args);
    }


    @Bean
    public TransactionAggregationService transactionAggregationServicBean(){
        TransactionServiceClient transactionServiceClient = null;
        CategoryServiceClient categoryServiceClient = null;
        AccountServiceClient accountServiceClient = null;
        TransactionAggregationRepository  transactionAggregationRepository = null;
        return new BasicTransactionAggregationService(transactionServiceClient,categoryServiceClient,accountServiceClient,transactionAggregationRepository );
    }

}

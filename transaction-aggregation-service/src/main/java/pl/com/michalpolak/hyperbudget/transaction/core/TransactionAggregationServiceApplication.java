package pl.com.michalpolak.hyperbudget.transaction.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionAggregationService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.AccountService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.CategoryService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionService;

@SpringBootApplication
@Configuration
@ComponentScan("pl.com.michalpolak")
@EnableWebMvc
@EnableDiscoveryClient
@EnableFeignClients("pl.com.michalpolak")
public class TransactionAggregationServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(TransactionAggregationServiceApplication.class, args);
    }


    @Bean
    @Autowired
    public TransactionAggregationService transactionAggregationServicBean(TransactionService transactionService,
                                                                          CategoryService categoryService,
                                                                          AccountService accountService
                                                                         ){

        return new BasicTransactionAggregationService(transactionService,categoryService,accountService );
    }

}

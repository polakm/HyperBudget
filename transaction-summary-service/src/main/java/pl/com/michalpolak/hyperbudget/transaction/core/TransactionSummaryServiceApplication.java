package pl.com.michalpolak.hyperbudget.transaction.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionSummaryService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.AccountService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.CategoryService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionService;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Configuration
@ComponentScan("pl.com.michalpolak")
@EnableDiscoveryClient
@EnableFeignClients("pl.com.michalpolak")
public class TransactionSummaryServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(TransactionSummaryServiceApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }


    @Bean
    @Autowired
    public static TransactionSummaryService transactionSummaryServicBean(TransactionService transactionService,
                                                                          CategoryService categoryService,
                                                                          AccountService accountService
                                                                         ){

        return new BasicTransactionSummaryService(transactionService,categoryService,accountService );
    }

}

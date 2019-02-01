package pl.com.michalpolak.hyperbudget.transaction.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("pl.com.michalpolak")
@EnableDiscoveryClient
@EnableFeignClients("pl.com.michalpolak")
public class TransactionSummaryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionSummaryServiceApplication.class, args);
    }

}

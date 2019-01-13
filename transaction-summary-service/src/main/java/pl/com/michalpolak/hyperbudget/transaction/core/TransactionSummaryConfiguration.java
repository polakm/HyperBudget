package pl.com.michalpolak.hyperbudget.transaction.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionSummaryService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.AccountService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.CategoryService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionService;

@Configuration
public class TransactionSummaryConfiguration {

    @Bean
    @Autowired
    public static TransactionSummaryService transactionSummaryServiceBean(TransactionService transactionService,
                                                                          CategoryService categoryService,
                                                                          AccountService accountService
    ){

        return new BasicTransactionSummaryService(transactionService,categoryService,accountService );
    }

}

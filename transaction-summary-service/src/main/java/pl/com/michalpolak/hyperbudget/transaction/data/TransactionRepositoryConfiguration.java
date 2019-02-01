package pl.com.michalpolak.hyperbudget.transaction.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionRepository;
import pl.com.michalpolak.hyperbudget.transaction.data.spi.AccountService;
import pl.com.michalpolak.hyperbudget.transaction.data.spi.CategoryService;
import pl.com.michalpolak.hyperbudget.transaction.data.spi.TransactionService;

@Configuration
public class TransactionRepositoryConfiguration {

    @Bean
    public static TransactionRepository getTransactionRepository(TransactionService transactionService, CategoryService categoryService, AccountService accountService) {
        return new RestClientsTransactionRepository(transactionService,categoryService,accountService);
    }
}

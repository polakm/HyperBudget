package pl.com.michalpolak.hyperbudget.transaction.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionRepository;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionValidator;
import pl.com.michalpolak.hyperbudget.transaction.data.InMemoryTransactionRepository;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("pl.com.michalpolak")
class TransactionServiceConfiguration {

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

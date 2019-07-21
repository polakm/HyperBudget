package pl.com.michalpolak.hyperbudget.transaction.core;

import org.joda.money.CurrencyUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.EventPublisher;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionRepository;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionValidator;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("pl.com.michalpolak")
public class TransactionServiceConfiguration {

    @Bean
    static TransactionValidator transactionValdiatorBean() {
        List<ValidationRule> rules = new ArrayList<>();
        rules.add(new AmountIsRequired());
        rules.add(CurrencyIsAccepted.of(CurrencyUnit.USD));
        rules.add(new ExecutionDateIsRequired());
        rules.add(new AccountIsRequired());
        return BasicTransactionValidator.of(rules);
    }

    @Bean("basic")
    @ConditionalOnProperty(prefix = "hyperbudget.transaction.service", name = "mode", havingValue = "basic", matchIfMissing = false)
    @Autowired
    public static TransactionService transactionServiceBean(TransactionRepository transactionRepository, TransactionValidator transactionValidator) {
        return new BasicTransactionService(transactionRepository, transactionValidator);
    }


    @Bean("evented")
    @ConditionalOnProperty(prefix = "hyperbudget.transaction.service", name = "mode", havingValue = "evented", matchIfMissing = true)
    @Autowired
    public static TransactionService transactionServiceBean(@Qualifier("basic") TransactionService service, EventPublisher publisher) {
        return new EventedTransactionServiceDecorator(service, publisher);
    }


    public static TransactionService createTransactionService(TransactionRepository transactionRepository) {
        TransactionValidator transactionValidator = transactionValdiatorBean();
        return new BasicTransactionService(transactionRepository, transactionValidator);
    }

}

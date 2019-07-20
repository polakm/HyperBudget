package pl.com.michalpolak.hyperbudget.transaction.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionSummaryService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionRepository;

@Configuration
class TransactionSummaryConfiguration {

    @Bean
    @Autowired
    static TransactionSummaryService transactionSummaryServiceBean(TransactionRepository transactionRepository){

        return new BasicTransactionSummaryService(transactionRepository);
    }

}

package pl.com.michalpolak.hyperbudget.transaction.client.transaction;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Transaction;

import java.util.List;
import java.util.stream.Collectors;

@Component
class TransactionDataMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionDataMapper.class);

    Transaction mapToEntity(TransactionData transactionData) {
        // The class is safe to extend when no method that can be overridden is called internally by the class.
        return this.mapDataToEntity(transactionData);
    }

    private Transaction mapDataToEntity(TransactionData transactionData) {

        Transaction.Builder builder = new Transaction.Builder();
        builder.withTitle(transactionData.getTitle());
        builder.forAccount(transactionData.getAccountId());
        builder.inCategory(transactionData.getCategoryId());

        if (transactionData.getCurrencyCode() != null && transactionData.getAmount() != null) {
            LOGGER.debug("Parse currency code and decimal value to Money - Transaction ID: {} ", transactionData.getId());
        }
        if (transactionData.getExecutionDate() != null && !transactionData.getExecutionDate().isEmpty()) {
            LOGGER.debug("Parse date format and string date to DateTime - Transaction ID: {} ", transactionData.getId());
            builder.onExecutionDate(DateTime.parse(transactionData.getExecutionDate()));
        }
        return builder.build();
    }

    List<Transaction> mapToEntities(List<TransactionData> transactions) {
        return transactions.stream().map(t -> this.mapDataToEntity(t)).collect(Collectors.toList());
    }


}

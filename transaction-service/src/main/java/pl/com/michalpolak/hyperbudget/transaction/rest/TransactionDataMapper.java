package pl.com.michalpolak.hyperbudget.transaction.rest;

import org.joda.money.Money;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
class TransactionDataMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionDataMapper.class);

    TransactionData mapToData(Transaction transaction) {
        // The class is safe to extend when no method that can be overridden is called internally by the class.
        return mapEntityToData(transaction);
    }

    final TransactionData mapEntityToData(Transaction transaction) {
        TransactionData.Builder builder = new TransactionData.Builder();
        builder.withId(transaction.getId());
        builder.withTitle(transaction.getTitle());
        builder.withAccountId(transaction.getAccountId());
        builder.withCategoryId(transaction.getCategoryId());

        if (transaction.getAmount() != null) {
            builder.withAmount(transaction.getAmount().getAmount().toPlainString());
            builder.withCurrencyCode(transaction.getAmount().getCurrencyUnit().getCode());
        }

        if (transaction.getAmount() != null && transaction.getAmount().isPositive()) {
            builder.withType("income");
        }

        if (transaction.getAmount() != null && transaction.getAmount().isNegative()) {
            builder.withType("expense");
        }

        if (transaction.getExecutionDate() != null) {
            builder.withExecutionDate(transaction.getExecutionDate().toString());
        }
        return builder.build();
    }

    Transaction mapToEntity(TransactionData transactionData) {
        // The class is safe to extend when no method that can be overridden is called internally by the class.
        return this.mapDataToEntity(transactionData);
    }

    final Transaction mapDataToEntity(TransactionData transactionData) {

        Transaction.Builder builder = new Transaction.Builder();
        builder.withTitle(transactionData.getTitle());
        builder.forAccount(transactionData.getAccountId());
        builder.inCategory(transactionData.getCategoryId());

        if (transactionData.getCurrencyCode() != null && transactionData.getAmount() != null) {
            LOGGER.debug("Parse currency code and decimal value to Money - Transaction ID: {} ", transactionData.getId());

            Money moneyValue = Money.parse(transactionData.getCurrencyCode() + " " + transactionData.getAmount());
            if (transactionData.getType().equals("expense")) {
                moneyValue = moneyValue.abs().negated();
            }

            if (transactionData.getType().equals("income")) {
                moneyValue = moneyValue.abs();
            }
            builder.withAmount(moneyValue);
        }
        if (transactionData.getExecutionDate() != null && !transactionData.getExecutionDate().isEmpty()) {
            LOGGER.debug("Parse date format and string date to DateTime - Transaction ID: {} ", transactionData.getId());
            builder.onExecutionDate(DateTime.parse(transactionData.getExecutionDate()));
        }
        return builder.build();
    }

    Transaction mapToEntity(String id, TransactionData transactionData) {

        Transaction transaction = this.mapDataToEntity(transactionData);
        return Transaction.builder().from(transaction).withId(id).build();
    }


    List<TransactionData> mapToDataList(Set<Transaction> transactions) {
        return transactions.stream().map(a -> this.mapEntityToData(a)).collect(Collectors.toList());
    }


}

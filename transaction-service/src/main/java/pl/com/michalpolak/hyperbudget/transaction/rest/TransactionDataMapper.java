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
        TransactionData transactionData = new TransactionData();
        transactionData.setId(transaction.getId());
        transactionData.setTitle(transaction.getTitle());
        transactionData.setAccountId(transaction.getAccountId());
        transactionData.setCategoryId(transaction.getCategoryId());

        if (transaction.getAmount() != null) {
            transactionData.setAmount(transaction.getAmount().getAmount().toPlainString());
            transactionData.setCurrencyCode(transaction.getAmount().getCurrencyUnit().getCode());
        }

        if(transaction.getAmount() != null && transaction.getAmount().isPositive()) {
            transactionData.setType("income");
        }

        if(transaction.getAmount() != null && transaction.getAmount().isNegative()){
            transactionData.setType("expense");
        }

        if (transaction.getExecutionDate() != null) {
            transactionData.setExecutionDate(transaction.getExecutionDate().toString());
        }
        return transactionData;
    }

    Transaction mapToEntity(TransactionData transactionData) {

        Transaction transaction = new Transaction();
        transaction.setTitle(transactionData.getTitle());
        transaction.setAccountId(transactionData.getAccountId());
        transaction.setCategoryId(transactionData.getCategoryId());

        if (transactionData.getCurrencyCode() != null && transactionData.getAmount() != null) {
            LOGGER.debug("Parse currency code and decimal value to Money - Transaction ID: {} ", transactionData.getId());

            Money moneyValue = Money.parse(transactionData.getCurrencyCode() + " " + transactionData.getAmount());
            if(transactionData.getType().equals("expense")){
                moneyValue = moneyValue.abs().negated();
            }

            if(transactionData.getType().equals("income")){
                moneyValue =  moneyValue.abs();
            }
            transaction.setAmount(moneyValue);
        }
        if (transactionData.getExecutionDate() != null && !transactionData.getExecutionDate().isEmpty()) {
            LOGGER.debug("Parse date format and string date to DateTime - Transaction ID: {} ", transactionData.getId());
            transaction.setExecutionDate(DateTime.parse(transactionData.getExecutionDate()));
        }
        return  transaction;
    }

    Transaction mapToEntity(String id,TransactionData transactionData) {
        Transaction transaction = this.mapToEntity(transactionData);
        transaction.setId(id);
        return transaction;
    }

    List<TransactionData> mapToDataList(Set<Transaction> transactions) {
        return transactions.stream().map(a -> mapToData(a)).collect(Collectors.toList());
    }
    
    
}

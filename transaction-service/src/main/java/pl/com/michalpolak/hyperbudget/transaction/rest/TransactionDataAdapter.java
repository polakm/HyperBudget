package pl.com.michalpolak.hyperbudget.transaction.rest;

import org.joda.money.Money;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

class TransactionDataAdapter extends Transaction {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionDataAdapter.class);

    TransactionDataAdapter(TransactionData transactionData) {
        this.setTitle(transactionData.getTitle());
        this.setAccountId(transactionData.getAccountId());
        this.setCategoryId(transactionData.getCategoryId());

        if (transactionData.getCurrencyCode() != null && transactionData.getAmount() != null) {
            LOGGER.debug("Parse currency code and decimal value to Money - Transaction ID: {} ", this.getId());
            this.setAmount(Money.parse(transactionData.getCurrencyCode() + " " + transactionData.getAmount()));
        }
        if (transactionData.getExecutionDate() != null && transactionData.getDateFormat() != null) {
            LOGGER.debug("Parse date format and string date to DateTime - Transaction ID: {} ", this.getId());
            this.setExecutionDate(DateTime.parse(transactionData.getExecutionDate(), DateTimeFormat.forPattern(transactionData.getDateFormat())));
        }
    }

    public TransactionDataAdapter(String id, TransactionData transactionData) {
        this(transactionData);
        setId(id);
    }


}

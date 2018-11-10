package pl.com.michalpolak.hyperbudget.transaction.rest;

import org.joda.money.Money;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

class TransactionDataAdapter extends Transaction {

    TransactionDataAdapter(TransactionData transactionData){

        this.setTitle(transactionData.getTitle());

        this.setAccountId(transactionData.getAccountId());

        if(transactionData.getCurrencyCode()!=null && transactionData.getAmount()!=null) {
            this.setAmount(Money.parse(transactionData.getCurrencyCode() + " " + transactionData.getAmount()));
        }
        if(transactionData.getExecutionDate()!=null && transactionData.getDateFormat() !=null) {
            this.setExecutionDate(DateTime.parse(transactionData.getExecutionDate(), DateTimeFormat.forPattern(transactionData.getDateFormat())));
        }
    };






}

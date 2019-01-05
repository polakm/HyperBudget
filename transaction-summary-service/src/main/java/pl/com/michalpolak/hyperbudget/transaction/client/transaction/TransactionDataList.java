package pl.com.michalpolak.hyperbudget.transaction.client.transaction;


import pl.com.michalpolak.hyperbudget.transaction.core.spi.Transaction;

import java.util.List;

public class TransactionDataList {

    private List<Transaction> transactions;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}

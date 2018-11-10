package pl.com.michalpolak.hyperbudget.transaction.rest;

import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TransactionDataList {

    private List<TransactionData> transactions;

    public TransactionDataList(Set<Transaction> transactionSet) {

        transactions = new ArrayList<>();

        transactionSet.forEach(t -> {
            transactions.add(new TransactionData(t));
        });

    }


    public List<TransactionData> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionData> transactions) {
        this.transactions = transactions;
    }
}

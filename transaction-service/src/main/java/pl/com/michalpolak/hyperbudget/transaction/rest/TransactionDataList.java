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
            TransactionData data = new TransactionData();
            data.setId(t.getId());
            data.setTitle(t.getTitle());
            data.setAmount(t.getAmount().getAmount().toPlainString());
            data.setCurrencyCode(t.getAmount().getCurrencyUnit().getCode());
            data.setExecutionDate(t.getExecutionDate().toString("YYYY-MM-DD"));
            data.setDateFormat("YYYY-MM-DD");
            transactions.add(data);
        });

    }


    public List<TransactionData> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionData> transactions) {
        this.transactions = transactions;
    }
}

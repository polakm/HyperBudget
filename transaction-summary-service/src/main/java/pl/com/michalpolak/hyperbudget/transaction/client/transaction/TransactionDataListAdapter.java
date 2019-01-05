package pl.com.michalpolak.hyperbudget.transaction.client.transaction;

import pl.com.michalpolak.hyperbudget.transaction.core.spi.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionDataListAdapter {
   private  List<Transaction> transactions;
    public TransactionDataListAdapter(List<TransactionData> dataList) {

        transactions = new ArrayList<>();

        dataList.forEach(t -> {
            transactions.add(new TransactionDataAdapter(t));
        });
    }

    public List<Transaction> asList() {
        return transactions;
    }
}

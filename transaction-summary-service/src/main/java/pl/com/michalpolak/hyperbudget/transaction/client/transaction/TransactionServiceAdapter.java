package pl.com.michalpolak.hyperbudget.transaction.client.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionService;

import java.util.List;

@Component
public class TransactionServiceAdapter implements TransactionService {


    private TransactionServiceClient client;

    @Autowired
    public TransactionServiceAdapter(TransactionServiceClient client){
        this.client=client;
    }

    @Override
    public Transaction addTranasaction(Transaction transaction) {
        TransactionData data = client.addTranasaction(new TransactionData(transaction));
        return new TransactionDataAdapter(data);
    }

    @Override
    public List<Transaction> transactionList() {
        List<TransactionData> list = client.transactionList();
        return new TransactionDataListAdapter(list).asList();
    }

    @Override
    public Transaction getTranaction(String id) {
        TransactionData data = client.getTranaction(id);
        return new TransactionDataAdapter(data);
    }

    @Override
    public void removeTranaction(String id) {
        client.removeTranaction(id);
    }

    @Override
    public void updateTranaction(String id, Transaction transaction) {
        client.updateTranaction(id, new TransactionData(transaction));
    }
}

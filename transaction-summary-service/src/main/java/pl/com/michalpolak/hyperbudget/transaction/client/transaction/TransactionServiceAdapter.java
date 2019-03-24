package pl.com.michalpolak.hyperbudget.transaction.client.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.data.spi.TransactionService;

import java.util.List;

@Component
public class TransactionServiceAdapter implements TransactionService {

    private TransactionServiceClient client;

    @Autowired
    public TransactionServiceAdapter(TransactionServiceClient client){
        this.client = client;
    }

    @Override
    public List<Transaction> transactionList() {
        List<TransactionData> list = client.transactionList();
        return new TransactionDataListAdapter(list).asList();
    }

}

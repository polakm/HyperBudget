package pl.com.michalpolak.hyperbudget.transaction.client.transaction;

import org.springframework.stereotype.Component;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Transaction;

import java.util.List;

@Component
public class FakeTransactionServiceClient implements TransactionServiceClient {
    @Override
    public Transaction addTranasaction(Transaction transactionData) {
        return null;
    }

    @Override
    public List<Transaction> transactionList() {
        return null;
    }

    @Override
    public Transaction getTranaction(String id) {
        return null;
    }

    @Override
    public void removeTranaction(String id) {

    }

    @Override
    public void updateTranaction(String id, Transaction transactionData) {

    }
}

package pl.com.michalpolak.hyperbudget.transaction.client.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.data.spi.TransactionService;

import java.util.List;

@Component
public class TransactionServiceAdapter implements TransactionService {

    private TransactionServiceClient client;
    private TransactionDataMapper mapper;

    @Autowired
    public TransactionServiceAdapter(TransactionServiceClient client, TransactionDataMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    @Override
    public List<Transaction> transactionList() {
        return mapper.mapToEntities(client.transactionList());

    }
}

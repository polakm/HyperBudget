package pl.com.michalpolak.hyperbudget.transaction.data;

        import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionRepository;
        import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

        import java.util.HashMap;
        import java.util.Map;
        import java.util.UUID;


public class InMemoryTransactionRepository implements TransactionRepository {

    private Map<String, Transaction> storage;

    public InMemoryTransactionRepository(){

        this.storage = new HashMap<>();
    }

    @Override
    public Transaction save(Transaction transaction) {

        String id = UUID.randomUUID().toString();
        transaction.setId(id);
        this.storage.put(id,transaction);
        return transaction;

    }
}

package pl.com.michalpolak.hyperbudget.transaction.data;

        import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionRepository;
        import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

        import java.util.*;


public class InMemoryTransactionRepository implements TransactionRepository {

    private Map<String, Transaction> storage;

    public InMemoryTransactionRepository(){

        this.storage = new HashMap<>();
    }

    @Override
    public Transaction save(Transaction transaction) {

        this.storage.put(transaction.getId(),transaction);
        return transaction;

    }

    @Override
    public Set<Transaction> getAll() {
        return new HashSet(this.storage.values());
    }
}

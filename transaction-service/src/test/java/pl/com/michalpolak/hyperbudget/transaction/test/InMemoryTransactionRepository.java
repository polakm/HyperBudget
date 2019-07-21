package pl.com.michalpolak.hyperbudget.transaction.test;

import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionRepository;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryTransactionRepository implements TransactionRepository {

    private final Map<String, Transaction> storage;

    private InMemoryTransactionRepository() {
        this.storage = new ConcurrentHashMap<>();
    }

    public static TransactionRepository empty() {
        return new InMemoryTransactionRepository();
    }

    @Override
    public Transaction save(Transaction transaction) {

        this.storage.put(transaction.getId(), transaction);
        return transaction;
    }

    @Override
    public Set<Transaction> getAll() {

        return new HashSet(this.storage.values());
    }

    @Override
    public Transaction remove(String id) {

        return this.storage.remove(id);
    }

    @Override
    public Transaction findById(String id) {

        return this.storage.get(id);
    }

    @Override
    public Transaction update(Transaction transaction) {

        this.storage.put(transaction.getId(), transaction);
        return transaction;
    }
}

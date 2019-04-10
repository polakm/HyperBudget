package pl.com.michalpolak.hyperbudget.transaction.data;

import org.springframework.stereotype.Repository;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionRepository;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryTransactionRepository implements TransactionRepository {

    private Map<String, Transaction> storage;

    public InMemoryTransactionRepository() {

        this.storage = new ConcurrentHashMap<>();
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

      return  this.storage.remove(id);
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

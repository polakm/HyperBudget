package pl.com.michalpolak.hyperbudget.transaction.data;

import org.springframework.stereotype.Repository;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionRepository;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
class InMemoryTransactionRepository implements TransactionRepository {

    private final Map<UUID, Transaction> storage;

    public InMemoryTransactionRepository() {
        this.storage = new ConcurrentHashMap<>();
    }

    public InMemoryTransactionRepository(Map<UUID, Transaction> initialData) {
        this.storage = new ConcurrentHashMap<>(initialData);
    }

    @Override
    public Transaction save(Transaction transaction) {

        this.storage.put(transaction.getId().toUUID(), transaction);
        return transaction;
    }

    @Override
    public Set<Transaction> getAll() {

        return new HashSet(this.storage.values());
    }

    @Override
    public Transaction remove(UUID id) {

        return this.storage.remove(id);
    }

    @Override
    public Transaction findById(UUID id) {

        return this.storage.get(id);
    }

    @Override
    public Transaction update(Transaction transaction) {

        this.storage.put(transaction.getId().toUUID(), transaction);
        return transaction;
    }
}

package pl.com.michalpolak.hyperbudget.transaction.core.spi;

import org.springframework.stereotype.Repository;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

import java.util.Set;
import java.util.UUID;

@Repository
public interface TransactionRepository {

    Transaction save(Transaction transaction);

    Set<Transaction> getAll();

    Transaction remove(UUID id);

    Transaction findById(UUID id);

    Transaction update(Transaction transaction);
}

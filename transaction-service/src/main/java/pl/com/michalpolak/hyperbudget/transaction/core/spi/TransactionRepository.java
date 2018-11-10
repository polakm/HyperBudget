package pl.com.michalpolak.hyperbudget.transaction.core.spi;

import org.springframework.stereotype.Repository;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

import java.util.Set;

@Repository
public interface TransactionRepository {

    Transaction save(Transaction transaction);

   Set<Transaction> getAll();

    void remove(String id);

    Transaction findById(String id);

    Transaction update(Transaction transaction);
}

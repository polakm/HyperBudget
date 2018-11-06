package pl.com.michalpolak.hyperbudget.transaction.core.spi;

import org.springframework.stereotype.Repository;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

import java.util.Set;

@Repository
public interface TransactionRepository {

    Transaction save(Transaction transaction);

   Set<Transaction> getAll();
}

package pl.com.michalpolak.hyperbudget.transaction.core.spi;

import pl.com.michalpolak.hyperbudget.transaction.core.InvalidTransactionException;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

public interface TransactionValidator {
    
    void validate(Transaction transaction) throws InvalidTransactionException;
}

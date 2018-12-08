package pl.com.michalpolak.hyperbudget.transaction.core;

import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

interface ValidationRule {

    void validate(Transaction transaction) throws InvalidTransactionException;
}

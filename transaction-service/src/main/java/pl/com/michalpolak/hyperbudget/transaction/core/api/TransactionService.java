package pl.com.michalpolak.hyperbudget.transaction.core.api;


import pl.com.michalpolak.hyperbudget.transaction.core.InvalidTransactionException;

import java.util.Set;

public interface TransactionService {

    Transaction addTransaction(Transaction transaction) throws InvalidTransactionException;

    void removeTransaction(String id) throws TransactionNotFoundException;

    Transaction getTransaction(String id) throws TransactionNotFoundException;

    Set<Transaction> allTrascations();

    Transaction updateTransaction(Transaction transaction) throws TransactionNotFoundException;
}

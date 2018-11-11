package pl.com.michalpolak.hyperbudget.transaction.core.api;


import pl.com.michalpolak.hyperbudget.transaction.core.TransactionNotFoundException;

import java.util.Set;

public interface TransactionService {

    Transaction addTransaction(Transaction transaction);

    void removeTransaction(String id);

    Transaction getTransaction(String id) throws TransactionNotFoundException;

    Set<Transaction> allTrascations();

    Transaction updateTransaction(Transaction transaction);
}

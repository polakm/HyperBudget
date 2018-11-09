package pl.com.michalpolak.hyperbudget.transaction.core.api;


import java.util.Set;

public interface TransactionService {

    Transaction addTransaction( Transaction transaction);

    void removeTransaction(String id);

    Transaction getTransaction(String id);

    Set<Transaction> allTrascations();
}

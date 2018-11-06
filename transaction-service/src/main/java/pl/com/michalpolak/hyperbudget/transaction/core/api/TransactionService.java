package pl.com.michalpolak.hyperbudget.transaction.core.api;


import java.util.Set;

public interface TransactionService {

    Transaction addTransaction( Transaction transaction);

    Set<Transaction> allTrascations();
}

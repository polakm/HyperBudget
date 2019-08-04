package pl.com.michalpolak.hyperbudget.transaction.core.api;


import java.util.Set;

public interface TransactionService {

    Transaction addTransaction(Transaction transaction) throws InvalidTransactionException;

    Transaction removeTransaction(TransactionId id) throws TransactionNotFoundException;

    Transaction getTransaction(TransactionId id) throws TransactionNotFoundException;

    Set<Transaction> allTrascations();

    Transaction updateTransaction(Transaction transaction) throws TransactionNotFoundException;
}

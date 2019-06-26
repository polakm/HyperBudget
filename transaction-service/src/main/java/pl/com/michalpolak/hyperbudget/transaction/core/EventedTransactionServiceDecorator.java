package pl.com.michalpolak.hyperbudget.transaction.core;

import pl.com.michalpolak.hyperbudget.transaction.core.api.InvalidTransactionException;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionNotFoundException;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.EventPublisher;

import java.util.Set;

public class EventedTransactionServiceDecorator implements TransactionService {

    private final TransactionService service;
    private final EventPublisher publisher;

    public EventedTransactionServiceDecorator(TransactionService service, EventPublisher publisher) {
        this.service = service;
        this.publisher = publisher;
    }

    @Override
    public Transaction addTransaction(Transaction transaction) throws InvalidTransactionException {
        Transaction result = service.addTransaction(transaction);
        publisher.publish(new AddedTransactionEvent(result));
        return result;
    }

    @Override
    public Transaction removeTransaction(String id) throws TransactionNotFoundException {
        Transaction removedTransaction = service.removeTransaction(id);
        publisher.publish(new RemovedTransactionEvent(removedTransaction));
        return removedTransaction;
    }

    @Override
    public Transaction getTransaction(String id) throws TransactionNotFoundException {
        Transaction result = service.getTransaction(id);
        return result;
    }

    @Override
    public Set<Transaction> allTrascations() {
        Set<Transaction> results = service.allTrascations();
        return results;
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) throws TransactionNotFoundException {
        Transaction result = service.updateTransaction(transaction);
        publisher.publish(new UpdatedTransactionEvent(result));
        return result;
    }
}
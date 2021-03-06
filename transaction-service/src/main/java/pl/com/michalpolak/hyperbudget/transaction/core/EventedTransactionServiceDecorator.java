package pl.com.michalpolak.hyperbudget.transaction.core;

import pl.com.michalpolak.hyperbudget.transaction.core.api.*;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.EventPublisher;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionEvent;

import java.util.Set;

public class EventedTransactionServiceDecorator implements TransactionService {

    private final TransactionService service;
    private final EventPublisher publisher;

    EventedTransactionServiceDecorator(TransactionService service, EventPublisher publisher) {
        this.service = service;
        this.publisher = publisher;
    }

    @Override
    public Transaction addTransaction(Transaction transaction) throws InvalidTransactionException {
        Transaction result = service.addTransaction(transaction);
        publisher.publish(TransactionEvent.added(result));
        return result;
    }

    @Override
    public Transaction removeTransaction(TransactionId id) throws TransactionNotFoundException {
        Transaction removedTransaction = service.removeTransaction(id);
        publisher.publish(TransactionEvent.removed(removedTransaction));
        return removedTransaction;
    }

    @Override
    public Transaction getTransaction(TransactionId id) throws TransactionNotFoundException {
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
        publisher.publish(TransactionEvent.updated(result));
        return result;
    }
}

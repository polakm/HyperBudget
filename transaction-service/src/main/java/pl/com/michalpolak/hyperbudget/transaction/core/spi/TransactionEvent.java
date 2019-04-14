package pl.com.michalpolak.hyperbudget.transaction.core.spi;

import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

public abstract class TransactionEvent {

    public interface Actions{
        String ADDED = "transaction_added";
        String UPDATED = "transaction_updated";
        String REMOVED = "transaction_removed";
    }

    private String action;

    private final Transaction transaction;

    public TransactionEvent(String action, Transaction transaction){
        this.action = action;
        this.transaction = transaction;
    }

    public Transaction getEntity() {
        return transaction;
    }

    public String getAction() {
        return  action;
    }
}

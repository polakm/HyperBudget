package pl.com.michalpolak.hyperbudget.transaction.core.spi;

import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

public class TransactionEvent {

    public interface Actions {
        String ADDED = "transaction_added";
        String UPDATED = "transaction_updated";
        String REMOVED = "transaction_removed";
    }

    private final String action;
    private final Transaction transaction;

    private TransactionEvent(String action, Transaction transaction) {
        this.action = action;
        this.transaction = transaction;
    }

    public Transaction getEntity() {
        return transaction;
    }

    public String getAction() {
        return action;
    }

    public static TransactionEvent of(String action, Transaction transaction){
        return  new TransactionEvent(action,transaction);
    }

    public static TransactionEvent added( Transaction transaction){
        return  new TransactionEvent(Actions.ADDED,transaction);
    }

    public static TransactionEvent updated(Transaction transaction){
        return  new TransactionEvent(Actions.UPDATED,transaction);
    }

    public static TransactionEvent removed( Transaction transaction){
        return  new TransactionEvent(Actions.REMOVED,transaction);
    }

}

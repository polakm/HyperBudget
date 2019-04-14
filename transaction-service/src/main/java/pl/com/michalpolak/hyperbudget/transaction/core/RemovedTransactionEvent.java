package pl.com.michalpolak.hyperbudget.transaction.core;

import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionEvent;

class RemovedTransactionEvent extends TransactionEvent {

    public RemovedTransactionEvent(Transaction transaction) {
        super(Actions.REMOVED, transaction);
    }

}

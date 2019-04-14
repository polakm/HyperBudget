package pl.com.michalpolak.hyperbudget.transaction.core;

import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionEvent;

class UpdatedTransactionEvent extends TransactionEvent {

    public UpdatedTransactionEvent(Transaction transaction) {
        super(Actions.UPDATED, transaction);
    }

}

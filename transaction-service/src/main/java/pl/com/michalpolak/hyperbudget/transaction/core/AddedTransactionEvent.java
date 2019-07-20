package pl.com.michalpolak.hyperbudget.transaction.core;

import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionEvent;

class AddedTransactionEvent extends TransactionEvent {

    AddedTransactionEvent(Transaction transaction) {
        super(Actions.ADDED, transaction);
    }

}

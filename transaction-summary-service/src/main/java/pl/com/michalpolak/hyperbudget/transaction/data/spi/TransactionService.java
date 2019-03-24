package pl.com.michalpolak.hyperbudget.transaction.data.spi;

import pl.com.michalpolak.hyperbudget.transaction.core.spi.Transaction;

import java.util.List;


public interface TransactionService {

    List<Transaction> transactionList();

}

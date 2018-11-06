package pl.com.michalpolak.hyperbudget.transaction.core;

import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionRepository;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionService;

import java.util.Set;


class BasicTransactionService implements TransactionService {

    private TransactionRepository transactionRepository;

    BasicTransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction addTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    @Override
    public Set<Transaction> allTrascations() {
        return this.transactionRepository.getAll();
    }
}


package pl.com.michalpolak.hyperbudget.transaction.core;

import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionNotFoundException;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionRepository;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionValidator;

import java.util.Set;


class BasicTransactionService implements TransactionService {

    private TransactionRepository transactionRepository;
    private TransactionValidator validator;

    BasicTransactionService(TransactionRepository transactionRepository, TransactionValidator validator) {
        this.transactionRepository = transactionRepository;
        this.validator = validator;
    }

    @Override
    public Transaction addTransaction(Transaction transaction) throws InvalidTransactionException {

        validator.validate(transaction);
        return transactionRepository.save(transaction);
    }

    @Override
    public void removeTransaction(String id) {
        this.transactionRepository.remove(id);
    }

    @Override
    public Transaction getTransaction(String id) throws TransactionNotFoundException {

        Transaction result = this.transactionRepository.findById(id);
        if (result == null) {
            throw new TransactionNotFoundException(id);
        }
        return result;
    }

    @Override
    public Set<Transaction> allTrascations() {
        return this.transactionRepository.getAll();
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {

        return this.transactionRepository.update(transaction);
    }
}


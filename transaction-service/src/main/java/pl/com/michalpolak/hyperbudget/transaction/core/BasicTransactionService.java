package pl.com.michalpolak.hyperbudget.transaction.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.michalpolak.hyperbudget.transaction.core.api.*;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionRepository;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionValidator;

import java.util.Collections;
import java.util.Set;

class BasicTransactionService implements TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicTransactionService.class);

    private final TransactionRepository transactionRepository;
    private final TransactionValidator validator;

    BasicTransactionService(TransactionRepository transactionRepository, TransactionValidator validator) {
        this.transactionRepository = transactionRepository;
        this.validator = validator;
    }

    @Override
    public Transaction addTransaction(Transaction transaction) throws InvalidTransactionException {

        LOGGER.info("Add new transaction - Transaction ID: {}", transaction.getId());
        validator.validate(transaction);
        Transaction result = transactionRepository.save(transaction);
        LOGGER.info("New transaction has added - Transaction ID: {}", transaction.getId());
        return result;
    }

    @Override
    public Transaction removeTransaction(TransactionId id) throws TransactionNotFoundException {

        LOGGER.info("Remove transaction - Transaction ID: {}", id);
        this.getTransaction(id);
        Transaction removedTranastion = this.transactionRepository.remove(id.toUUID());
        LOGGER.info("Transaction has removed - Transaction ID: {}", id);
        return removedTranastion;
    }

    @Override
    public Transaction getTransaction(TransactionId id) throws TransactionNotFoundException {

        Transaction result = this.transactionRepository.findById(id.toUUID());
        if (result == null) {
            throw new TransactionNotFoundException(id);
        }
        return result;
    }

    @Override
    public Set<Transaction> allTrascations() {
        return Collections.unmodifiableSet(this.transactionRepository.getAll());
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) throws TransactionNotFoundException {

        LOGGER.info("Update transaction - Transaction ID: {}", transaction.getId());
        this.getTransaction(transaction.getId());
        Transaction result = this.transactionRepository.update(transaction);
        LOGGER.info("Transaction has updated - Transaction ID: {}", transaction.getId());
        return result;
    }
}


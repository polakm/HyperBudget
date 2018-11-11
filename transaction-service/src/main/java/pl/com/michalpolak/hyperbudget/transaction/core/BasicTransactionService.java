package pl.com.michalpolak.hyperbudget.transaction.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionNotFoundException;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionRepository;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionValidator;

import java.util.Set;


class BasicTransactionService implements TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicTransactionService.class);

    private TransactionRepository transactionRepository;
    private TransactionValidator validator;

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
    public void removeTransaction(String id) throws TransactionNotFoundException {

        LOGGER.info("Remove transaction - Transaction ID: {}", id);
        this.getTransaction(id);
         this.transactionRepository.remove(id);
        LOGGER.info("Transaction has removed - Transaction ID: {}", id);
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
    public Transaction updateTransaction(Transaction transaction) throws TransactionNotFoundException {

        LOGGER.info("Update transaction - Transaction ID: {}", transaction.getId());
        this.getTransaction(transaction.getId());
        Transaction result = this.transactionRepository.update(transaction);
        LOGGER.info("Transaction has updated - Transaction ID: {}", transaction.getId());
        return result;
    }
}


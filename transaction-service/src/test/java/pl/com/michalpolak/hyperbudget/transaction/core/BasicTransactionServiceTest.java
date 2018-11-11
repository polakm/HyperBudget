package pl.com.michalpolak.hyperbudget.transaction.core;

import org.junit.Test;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionNotFoundException;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionRepository;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionValidator;
import pl.com.michalpolak.hyperbudget.transaction.data.InMemoryTransactionRepository;

import java.util.Set;

import static org.junit.Assert.*;

public class BasicTransactionServiceTest {

    @Test
    public void addTransaction() throws TransactionNotFoundException, InvalidTransactionException {

        //given
        TransactionRepository repository = new InMemoryTransactionRepository();
        TransactionValidator validator = new BasicTransactionValidator();
        TransactionService transactionService = new BasicTransactionService(repository, validator);
        Transaction transaction = new Transaction();

        //when
        transactionService.addTransaction(transaction);
        Transaction resultTransaction = transactionService.getTransaction(transaction.getId());

        //then
        assertNotNull(resultTransaction);

    }

    @Test
    public void transactionNotFoundException() {

        //given
        TransactionRepository repository = new InMemoryTransactionRepository();
        TransactionValidator validator = new BasicTransactionValidator();
        TransactionService transactionService = new BasicTransactionService(repository, validator);

        //when
        try {
            transactionService.getTransaction("non-exist-id");
        } catch (TransactionNotFoundException e) {
            return;
        }

        //then
        fail("Method should throw TransactionNotFoundException.");
    }

    @Test
    public void invalidTransactionException() {

        //given
        TransactionRepository repository = new InMemoryTransactionRepository();
        ValidationRule rule = new AmountIsRequired();
        TransactionValidator validator = new BasicTransactionValidator(rule);
        TransactionService transactionService = new BasicTransactionService(repository, validator);
        Transaction transaction = new Transaction();

        //when
        try {
            transactionService.addTransaction(transaction);
        } catch (InvalidTransactionException e) {
            return;
        }

        //then
        fail("Method should throw InvalidTransactionException.");
    }


    @Test
    public void updateTransaction() throws TransactionNotFoundException {

        //given
        TransactionRepository repository = new InMemoryTransactionRepository();
        TransactionValidator validator = new BasicTransactionValidator();
        TransactionService transactionService = new BasicTransactionService(repository, validator);
        Transaction transaction = new Transaction();
        transaction.setTitle("test");
        Transaction updatedTransaction = new Transaction();
        updatedTransaction.setId(transaction.getId());
        updatedTransaction.setTitle("updated");

        //when
        transactionService.updateTransaction(updatedTransaction);
        Transaction resultTransaction = transactionService.getTransaction(transaction.getId());

        //then
        assertEquals("updated", resultTransaction.getTitle());

    }


    @Test
    public void removeTransaction() throws TransactionNotFoundException, InvalidTransactionException {

        //given
        TransactionRepository repository = new InMemoryTransactionRepository();
        TransactionValidator validator = new BasicTransactionValidator();
        TransactionService transactionService = new BasicTransactionService(repository, validator);
        Transaction transaction = new Transaction();

        //when
        transactionService.addTransaction(transaction);
        transactionService.removeTransaction(transaction.getId());

        //when
        try {
            transactionService.getTransaction(transaction.getId());
        } catch (TransactionNotFoundException e) {
            return;
        }

        //then
        fail("After remove transaction, method getTransaction should throw TransactionNotFoundException.");

    }


    @Test
    public void allTrascations() throws InvalidTransactionException {

        //given
        TransactionRepository repository = new InMemoryTransactionRepository();
        TransactionValidator validator = new BasicTransactionValidator();
        TransactionService transactionService = new BasicTransactionService(repository, validator);

        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();
        Transaction transaction3 = new Transaction();

        //when
        transactionService.addTransaction(transaction1);
        transactionService.addTransaction(transaction2);
        transactionService.addTransaction(transaction3);
        Set<Transaction> transactions = transactionService.allTrascations();

        //then
        assertEquals(3, transactions.size());
    }
}
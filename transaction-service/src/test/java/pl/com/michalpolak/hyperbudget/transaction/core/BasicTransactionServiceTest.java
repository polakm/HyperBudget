package pl.com.michalpolak.hyperbudget.transaction.core;

import org.joda.money.Money;
import org.joda.time.DateTime;
import org.junit.Test;
import pl.com.michalpolak.hyperbudget.transaction.core.api.InvalidTransactionException;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionNotFoundException;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionService;
import pl.com.michalpolak.hyperbudget.transaction.data.InMemoryTransactionRepository;

import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.*;

public class BasicTransactionServiceTest {

    @Test
    public void addTransaction() throws TransactionNotFoundException, InvalidTransactionException {

        //given
        TransactionService transactionService = TransactionServiceConfiguration.createTransactionService(new InMemoryTransactionRepository());
        Transaction transaction = getExampleTransaction();

        //when
        transactionService.addTransaction(transaction);
        Transaction resultTransaction = transactionService.getTransaction(transaction.getId());

        //then
        assertNotNull(resultTransaction);

    }

    @Test
    public void transactionNotFoundException() {

        //given
        TransactionService transactionService = TransactionServiceConfiguration.createTransactionService(new InMemoryTransactionRepository());

        //when
        try {
            transactionService.getTransaction("non-exist-transaction-id");
        } catch (TransactionNotFoundException e) {
            return;
        }

        //then
        fail("Method should throw TransactionNotFoundException.");
    }

    @Test
    public void invalidTransactionException() {

        //given
        TransactionService transactionService = TransactionServiceConfiguration.createTransactionService(new InMemoryTransactionRepository());
        Transaction transaction = getExampleTransaction();
        transaction.setAmount(null);

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
    public void updateTransaction() throws TransactionNotFoundException, InvalidTransactionException {

        //given
        TransactionService transactionService = TransactionServiceConfiguration.createTransactionService(new InMemoryTransactionRepository());

        Transaction transaction = getExampleTransaction();
        transactionService.addTransaction(transaction);

        Transaction updatedTransaction = getExampleTransaction();
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
        TransactionService transactionService = TransactionServiceConfiguration.createTransactionService(new InMemoryTransactionRepository());
        Transaction transaction = getExampleTransaction();

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
        TransactionService transactionService = TransactionServiceConfiguration.createTransactionService(new InMemoryTransactionRepository());
        Transaction transaction1 = getExampleTransaction();
        Transaction transaction2 = getExampleTransaction();
        Transaction transaction3 = getExampleTransaction();

        //when
        transactionService.addTransaction(transaction1);
        transactionService.addTransaction(transaction2);
        transactionService.addTransaction(transaction3);
        Set<Transaction> transactions = transactionService.allTrascations();

        //then
        assertEquals(3, transactions.size());
    }



    private Transaction getExampleTransaction() {
        Transaction transaction = new Transaction();
        transaction.setTitle("title");
        transaction.setExecutionDate(new DateTime());
        transaction.setAccountId(UUID.randomUUID().toString());
        transaction.setAmount(Money.parse("PLN 299.99"));
        return transaction;
    }


}
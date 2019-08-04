package pl.com.michalpolak.hyperbudget.transaction.core;

import org.joda.money.Money;
import org.joda.time.DateTime;
import org.junit.Test;
import pl.com.michalpolak.hyperbudget.transaction.core.api.*;
import pl.com.michalpolak.hyperbudget.transaction.test.InMemoryTransactionRepository;

import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.*;

public class BasicTransactionServiceTest {

    @Test
    public void addTransaction() throws TransactionNotFoundException, InvalidTransactionException {

        //given
        TransactionService transactionService = TransactionServiceConfiguration.createTransactionService(InMemoryTransactionRepository.empty());
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
        TransactionService transactionService = TransactionServiceConfiguration.createTransactionService(InMemoryTransactionRepository.empty());

        //when
        try {
            transactionService.getTransaction(TransactionId.generate());
        } catch (TransactionNotFoundException e) {
            return;
        }

        //then
        fail("Method should throw TransactionNotFoundException.");
    }

    @Test
    public void invalidTransactionException() {

        //given
        TransactionService transactionService = TransactionServiceConfiguration.createTransactionService(InMemoryTransactionRepository.empty());

        Transaction transaction = Transaction.builder().withAmount(null).withTitle(TransactionTitle.fromString("updated")).build();

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
        TransactionService transactionService = TransactionServiceConfiguration.createTransactionService(InMemoryTransactionRepository.empty());

        Transaction transaction = getExampleTransaction();
        transactionService.addTransaction(transaction);

        Transaction updatedTransaction = Transaction.builder().withId(transaction.getId()).withTitle(TransactionTitle.fromString("updated")).build();

        //when
        transactionService.updateTransaction(updatedTransaction);
        Transaction resultTransaction = transactionService.getTransaction(transaction.getId());

        //then
        assertEquals("updated", resultTransaction.getTitle().toString());

    }

    @Test
    public void removeTransaction() throws TransactionNotFoundException, InvalidTransactionException {

        //given
        TransactionService transactionService = TransactionServiceConfiguration.createTransactionService(InMemoryTransactionRepository.empty());
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
        TransactionService transactionService = TransactionServiceConfiguration.createTransactionService(InMemoryTransactionRepository.empty());
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

        Transaction.Builder builder = new Transaction.Builder();
        builder.withTitle(TransactionTitle.fromString("title"));
        builder.onExecutionDate(new DateTime());
        builder.forAccount(AccountId.generate());
        builder.withAmount(Money.parse("USD 299.99"));
        return builder.build();
    }
}
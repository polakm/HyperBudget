package pl.com.michalpolak.hyperbudget.transaction.core;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.junit.Test;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionRepository;
import pl.com.michalpolak.hyperbudget.transaction.data.InMemoryTransactionRepository;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class BasicTransactionServiceTest {

    @Test
    public void addTransaction() {

        //given
        TransactionRepository repository = new InMemoryTransactionRepository();
        TransactionService transactionService = new BasicTransactionService(repository);
        Transaction transaction = new Transaction();

        //when
        transactionService.addTransaction(transaction);
        Transaction resultTransaction = transactionService.getTransaction(transaction.getId());

        //then
        assertNotNull(resultTransaction);

    }


    @Test
    public void removeTransaction() {

        //given
        TransactionRepository repository = new InMemoryTransactionRepository();
        TransactionService transactionService = new BasicTransactionService(repository);
        Transaction transaction = new Transaction();

        //when
        transactionService.addTransaction(transaction);
        transactionService.removeTransaction(transaction.getId());
        Transaction resultTransaction = transactionService.getTransaction(transaction.getId());

        //then
        assertNull(resultTransaction);

    }


    @Test
    public void allTrascations() {

        //given
        TransactionRepository repository = new InMemoryTransactionRepository();
        TransactionService transactionService = new BasicTransactionService(repository);

        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();
        Transaction transaction3 = new Transaction();

        //when
        transactionService.addTransaction(transaction1);
        transactionService.addTransaction(transaction2);
        transactionService.addTransaction(transaction3);
        Set<Transaction> transactions = transactionService.allTrascations();

        //then
        assertEquals(3,transactions.size());
    }
}
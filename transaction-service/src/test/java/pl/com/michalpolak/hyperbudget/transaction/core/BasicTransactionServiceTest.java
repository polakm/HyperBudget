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

import static org.junit.Assert.assertNotNull;

public class BasicTransactionServiceTest {

    @Test
    public void addTransaction() {

        //given
        TransactionRepository repository = new InMemoryTransactionRepository();
        TransactionService transactionService = new BasicTransactionService(repository);

        Transaction transaction = new Transaction();
        transaction.setTitle("Test");
        transaction.setAmount(Money.parse("PLN 999.99"));
        transaction.setExecutionDate(DateTime.now());
        //when
       Transaction resultTransaction = transactionService.addTransaction(transaction);

        //then
        assertNotNull(resultTransaction);

    }

    @Test
    public void allTrascations() {

        //given
        TransactionRepository repository = new InMemoryTransactionRepository();
        TransactionService transactionService = new BasicTransactionService(repository);

        Transaction transaction = new Transaction();
        transaction.setTitle("Test");
        transaction.setAmount(Money.parse("PLN 999.99"));
        transaction.setExecutionDate(DateTime.now());

        //when
        transactionService.addTransaction(transaction);
        Set<Transaction> transactions =transactionService.allTrascations();
        Transaction resultTransaction = transactions.iterator().next();

        //then
        assertNotNull(resultTransaction);

    }
}
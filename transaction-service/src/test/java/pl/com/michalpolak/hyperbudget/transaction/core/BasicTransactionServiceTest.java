package pl.com.michalpolak.hyperbudget.transaction.core;

import org.junit.Test;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionRepository;
import pl.com.michalpolak.hyperbudget.transaction.data.InMemoryTransactionRepository;

import java.util.Set;

import static org.junit.Assert.*;

public class BasicTransactionServiceTest {

    @Test
    public void addTransaction() throws TransactionNotFoundException {

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
    public void transactionNotFoundException() {

        //given
        TransactionRepository repository = new InMemoryTransactionRepository();
        TransactionService transactionService = new BasicTransactionService(repository);

        //when
        try {
            transactionService.getTransaction("non-exist-id");
        }catch (TransactionNotFoundException e){
            return;
        }

        //then
        fail("Method should throw TransactionNotFoundException.");
    }


    @Test
    public void updateTransaction() throws TransactionNotFoundException {

        //given
        TransactionRepository repository = new InMemoryTransactionRepository();
        TransactionService transactionService = new BasicTransactionService(repository);
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
    public void removeTransaction() throws TransactionNotFoundException {

        //given
        TransactionRepository repository = new InMemoryTransactionRepository();
        TransactionService transactionService = new BasicTransactionService(repository);
        Transaction transaction = new Transaction();

        //when
        transactionService.addTransaction(transaction);
        transactionService.removeTransaction(transaction.getId());

        //when
        try {
            transactionService.getTransaction("non-exist-id");
        }catch (TransactionNotFoundException e){
            return;
        }

        //then
        fail("After remove transaction, method getTransaction should throw TransactionNotFoundException.");

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
        assertEquals(3, transactions.size());
    }
}
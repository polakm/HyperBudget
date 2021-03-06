package pl.com.michalpolak.hyperbudget.transaction.integration;

import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.junit.Test;
import pl.com.michalpolak.hyperbudget.transaction.core.TransactionServiceConfiguration;
import pl.com.michalpolak.hyperbudget.transaction.core.api.*;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.EventPublisher;
import pl.com.michalpolak.hyperbudget.transaction.event.EventConfiguration;
import pl.com.michalpolak.hyperbudget.transaction.event.spi.ProducerCreator;
import pl.com.michalpolak.hyperbudget.transaction.test.InMemoryTransactionRepository;
import pl.com.michalpolak.hyperbudget.transaction.test.IntegrationTest;

import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@IntegrationTest
public class TransactionServiceEventedDecoratorTest {

    @Test
    public void addTransaction() throws TransactionNotFoundException, InvalidTransactionException {

        //given
        TransactionService transactionService = getTransactionService();
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
        TransactionService transactionService = getTransactionService();
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
        TransactionService transactionService = getTransactionService();
        Transaction transaction = getExampleTransaction();
        transaction = Transaction.builder().from(transaction).withAmount(null).build();

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
        TransactionService transactionService = getTransactionService();
        Transaction transaction = getExampleTransaction();
        transactionService.addTransaction(transaction);

        Transaction updatedTransaction = getExampleTransaction();
        updatedTransaction = Transaction.builder().from(updatedTransaction)
                .withId(transaction.getId())
                .withTitle(TransactionTitle.fromString("updated"))
                .build();

        //when
        transactionService.updateTransaction(updatedTransaction);
        Transaction resultTransaction = transactionService.getTransaction(transaction.getId());

        //then
        assertEquals(TransactionTitle.fromString("updated"), resultTransaction.getTitle());

    }

    @Test
    public void removeTransaction() throws TransactionNotFoundException, InvalidTransactionException {

        //given
        TransactionService transactionService = getTransactionService();
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
    public void allTrasacations() throws InvalidTransactionException {

        //given
        TransactionService transactionService = getTransactionService();
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
        builder.inCategory(CategoryId.generate());
        builder.withAmount(Money.parse("USD 299.99"));
        return builder.build();
    }

    private TransactionService getTransactionService() {
        TransactionService service = TransactionServiceConfiguration.createTransactionService(InMemoryTransactionRepository.empty());
        ProducerCreator producerCreator = mockProducerCreator();
        EventPublisher publisher = EventConfiguration.createEventPublisher("test-topic", producerCreator);
        return TransactionServiceConfiguration.transactionServiceBean(service, publisher);
    }

    private ProducerCreator mockProducerCreator() {
        ProducerCreator producerCreator = mock(ProducerCreator.class);
        when(producerCreator.createProducer()).thenReturn(new MockProducer<>(true, new LongSerializer(), new StringSerializer()));
        return producerCreator;
    }

}
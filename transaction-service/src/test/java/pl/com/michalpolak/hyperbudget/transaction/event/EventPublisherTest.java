package pl.com.michalpolak.hyperbudget.transaction.event;

import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.junit.Test;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.EventPublisher;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionEvent;
import pl.com.michalpolak.hyperbudget.transaction.event.spi.ProducerCreator;

import java.util.UUID;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.com.michalpolak.hyperbudget.transaction.event.EventConfiguration.createEventPublisher;

public class EventPublisherTest {


    @Test
    public void publish() {

        //given
        MockProducer<Long, String> producer = new MockProducer<>(true, new LongSerializer(), new StringSerializer());
        EventPublisher publisher = createEventPublisher("test-topic",  mockProducerCreator(producer));
        TransactionEvent event = mockTransactionEvent(TransactionEvent.Actions.UPDATED, "test-title","USD 1023.33");

        //when
        publisher.publish(event);

        //then
        producer.history().stream().findFirst().ifPresent(e -> {
            assertEquals(e.topic(), "test-topic");
            assertNotNull(e.value());
        });
    }

    private TransactionEvent mockTransactionEvent(String action,Transaction transaction) {

        TransactionEvent event = mock(TransactionEvent.class);
        when(event.getAction()).thenReturn(action);
        when(event.getEntity()).thenReturn(transaction);

        return event;
    }

    private TransactionEvent mockTransactionEvent(String action, String title, String amount) {

        TransactionEvent event = mock(TransactionEvent.class);
        when(event.getAction()).thenReturn(action);
        when(event.getEntity()).thenReturn(createTransaction(title,amount));

        return event;
    }


    private Transaction createTransaction(String title, String amount) {

        Transaction transaction = new Transaction();
        transaction.setTitle(title);
        transaction.setExecutionDate(new DateTime());
        transaction.setAccountId(UUID.randomUUID().toString());
        transaction.setAmount(Money.parse(amount));
        return transaction;
    }

    private BasicProducerCreator mockProducerCreator(MockProducer<Long, String> producer) {
        BasicProducerCreator producerCreator = mock(BasicProducerCreator.class);
        when(producerCreator.createProducer()).thenReturn(producer);
        return producerCreator;
    }
}
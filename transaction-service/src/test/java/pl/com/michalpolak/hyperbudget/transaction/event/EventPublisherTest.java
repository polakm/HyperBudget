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

        Transaction transaction = new Transaction();
        transaction.setAccountId(UUID.randomUUID().toString());
        transaction.setAmount(Money.parse("USD 1023.33"));
        transaction.setExecutionDate(DateTime.now());
        transaction.setTitle("TRANSACTION-TEST ");
        transaction.setCategoryId(UUID.randomUUID().toString());
        TransactionEvent event = new TransactionEvent(TransactionEvent.Actions.UPDATED, transaction);

        //when
        publisher.publish(event);

        //then
        producer.history().stream().findFirst().ifPresent(e -> {
            assertEquals(e.topic(), "test-topic");
            assertNotNull(e.value());
        });
    }

    private ProducerCreator mockProducerCreator(MockProducer<Long, String> producer) {
        ProducerCreator producerCreator = mock(ProducerCreator.class);
        when(producerCreator.createProducer()).thenReturn(producer);
        return producerCreator;
    }
}
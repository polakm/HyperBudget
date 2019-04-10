package pl.com.michalpolak.hyperbudget.transaction.event;

import org.joda.money.Money;
import org.joda.time.DateTime;
import org.junit.Test;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.EventPublisher;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionEvent;
import pl.com.michalpolak.hyperbudget.transaction.test.IntegrationTest;

import java.util.UUID;

@IntegrationTest // need working kafka
public class EventPublisherTest {


    @Test
    public void publish() {

        EventPublisher publisher = new KafkaEventPublisher(new TransactionEventMapper(), new ProducerCreator(),"test");

        for(int i=0; i<=100; i++) {

            Transaction transaction = new Transaction();
            transaction.setAccountId(UUID.randomUUID().toString());
            transaction.setAmount(Money.parse("USD 1023.33"));
            transaction.setExecutionDate(DateTime.now());
            transaction.setTitle("TRANSACTION-TEST "+i);
            transaction.setCategoryId(UUID.randomUUID().toString());
            TransactionEvent event = new TransactionEvent(TransactionEvent.Actions.UPDATED, transaction);
            publisher.publish(event);
        }
    }
}
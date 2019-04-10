package pl.com.michalpolak.hyperbudget.transaction.event;

import org.joda.money.Money;
import org.joda.time.DateTime;
import org.junit.Test;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionEvent;

import java.util.UUID;

import static junit.framework.Assert.*;


public class TransactionEventMapperTest {

    @Test
    public void mapTransactionEventToEventDataWithAction() {

        //given
        TransactionEvent event = createTransactionEvent("test-action","test-title","USD 299.99");
        TransactionEventMapper mapper = new TransactionEventMapper();

        //when
       EventData eventData = mapper.mapToEventData(event);

        //then
        assertEquals(eventData.getAction(),"test-action");
    }

    @Test
    public void mapTransactionEventToEventDataWithContext() {

        //given
        TransactionEvent event = createTransactionEvent("test-action","test-title","USD 299.99");
        TransactionEventMapper mapper = new TransactionEventMapper();

        //when
        EventData eventData = mapper.mapToEventData(event);

        //then
        assertEquals(eventData.getContext().getTitle(),"test-title");

    }

    @Test
    public void mapTransactionEventToEventDataWithPositiveAmount() {

        //given
        TransactionEvent event = createTransactionEvent("test-action","test-title","USD 1099.99");
        TransactionEventMapper mapper = new TransactionEventMapper();

        //when
        EventData eventData = mapper.mapToEventData(event);

        //then
        assertEquals(eventData.getContext().getAmount(),"1099.99");
        assertEquals(eventData.getContext().getCurrencyCode(),"USD");
        assertEquals(eventData.getContext().getType(),"income");

    }

    @Test
    public void mapTransactionEventToEventDataWithNegativeAmount() {

        //given
        TransactionEvent event = createTransactionEvent("test-action","test-title","EUR -1099.99");
        TransactionEventMapper mapper = new TransactionEventMapper();

        //when
        EventData eventData = mapper.mapToEventData(event);

        //then
        assertEquals(eventData.getContext().getAmount(),"-1099.99");
        assertEquals(eventData.getContext().getCurrencyCode(),"EUR");
        assertEquals(eventData.getContext().getType(),"expense");

    }

    private TransactionEvent createTransactionEvent(String action,String title,String amount) {

        return new TransactionEvent(action,createTranaction(title,amount));
    }

    private Transaction createTranaction(String title, String amount) {

            Transaction transaction = new Transaction();
            transaction.setTitle(title);
            transaction.setExecutionDate(new DateTime());
            transaction.setAccountId(UUID.randomUUID().toString());
            transaction.setAmount(Money.parse(amount));
            return transaction;
    }
}
package pl.com.michalpolak.hyperbudget.transaction.event;

import org.joda.money.Money;
import org.joda.time.DateTime;
import org.junit.Test;
import pl.com.michalpolak.hyperbudget.transaction.core.api.AccountId;
import pl.com.michalpolak.hyperbudget.transaction.core.api.CategoryId;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionTitle;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionEvent;

import java.util.UUID;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class TransactionEventMapperTest {

    @Test
    public void mapTransactionEventToEventDataWithAction() {

        //given
        TransactionEvent event = mockTransactionEvent("test-action", "test-title", "USD 299.99");
        TransactionEventMapper mapper = new TransactionEventMapper();

        //when
        EventData eventData = mapper.mapToEventData(event);

        //then
        assertEquals(eventData.getAction(), "test-action");
    }

    @Test
    public void mapTransactionEventToEventDataWithContext() {

        //given
        TransactionEvent event = mockTransactionEvent("test-action", "test-title", "USD 299.99");
        TransactionEventMapper mapper = new TransactionEventMapper();

        //when
        EventData eventData = mapper.mapToEventData(event);

        //then
        assertEquals(eventData.getContext().getTitle(), "test-title");

    }

    @Test
    public void mapTransactionEventToEventDataWithPositiveAmount() {

        //given
        TransactionEvent event = mockTransactionEvent("test-action", "test-title", "USD 1099.99");
        TransactionEventMapper mapper = new TransactionEventMapper();

        //when
        EventData eventData = mapper.mapToEventData(event);

        //then
        assertEquals(eventData.getContext().getAmount(), "1099.99");
        assertEquals(eventData.getContext().getCurrencyCode(), "USD");
        assertEquals(eventData.getContext().getType(), "income");

    }

    @Test
    public void mapTransactionEventToEventDataWithNegativeAmount() {

        //given
        TransactionEvent event = mockTransactionEvent("test-action", "test-title", "EUR -1099.99");
        TransactionEventMapper mapper = new TransactionEventMapper();

        //when
        EventData eventData = mapper.mapToEventData(event);

        //then
        assertEquals(eventData.getContext().getAmount(), "-1099.99");
        assertEquals(eventData.getContext().getCurrencyCode(), "EUR");
        assertEquals(eventData.getContext().getType(), "expense");

    }

    private TransactionEvent mockTransactionEvent(String action, String title, String amount) {

        TransactionEvent event = mock(TransactionEvent.class);
        when(event.getAction()).thenReturn(action);
        when(event.getEntity()).thenReturn(createTransaction(title, amount));

        return event;
    }


    private Transaction createTransaction(String title, String amount) {

        Transaction.Builder builder = new Transaction.Builder();
        builder.withTitle(TransactionTitle.fromString(title));
        builder.onExecutionDate(new DateTime());
        builder.forAccount(AccountId.generate());
        builder.inCategory(CategoryId.generate());
        builder.withAmount(Money.parse(amount));
        return builder.build();
    }
}
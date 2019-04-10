package pl.com.michalpolak.hyperbudget.transaction.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionEvent;

@Component
class TransactionEventMapper {

    String mapToEventMessage(TransactionEvent event) throws JsonProcessingException {

        EventData eventData = mapToEventData(event);
        String message = new ObjectMapper().writeValueAsString(eventData);
        return message;
    }

    private EventData mapToEventData(TransactionEvent event) {
        EventContext context = this.mapToEventContext(event.getEntity());
        return new EventData(event.getAction(), context);
    }

    private EventContext mapToEventContext(Transaction transaction) {
        EventContext eventContext = new EventContext();
        eventContext.setId(transaction.getId());
        eventContext.setTitle(transaction.getTitle());
        eventContext.setAccountId(transaction.getAccountId());
        eventContext.setCategoryId(transaction.getCategoryId());

        if (transaction.getAmount() != null) {
            eventContext.setAmount(transaction.getAmount().getAmount().toPlainString());
            eventContext.setCurrencyCode(transaction.getAmount().getCurrencyUnit().getCode());
        }

        if (transaction.getAmount() != null && transaction.getAmount().isPositive()) {
            eventContext.setType("income");
        }

        if (transaction.getAmount() != null && transaction.getAmount().isNegative()) {
            eventContext.setType("expense");
        }

        if (transaction.getExecutionDate() != null) {
            eventContext.setExecutionDate(transaction.getExecutionDate().toString());
        }
        return eventContext;
    }

}

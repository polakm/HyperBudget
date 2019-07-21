package pl.com.michalpolak.hyperbudget.transaction.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionEvent;

class TransactionEventMapper {

   String mapToEventMessage(TransactionEvent event) throws JsonProcessingException {

        EventData eventData = mapToEventData(event);
        String message = new ObjectMapper().writeValueAsString(eventData);
        return message;
    }

    final EventData mapToEventData(TransactionEvent event) {

        EventContext context = this.mapToEventContext(event.getEntity());
        return EventData.of(event.getAction(), context);
    }

    final EventContext mapToEventContext(Transaction transaction) {

        EventContext.Builder builder = EventContext.builder();
        builder.withId(transaction.getId());
        builder.withTitle(transaction.getTitle());
        builder.withAccountId(transaction.getAccountId());
        builder.withCategoryId(transaction.getCategoryId());

        if (transaction.getAmount() != null) {
            builder.withAmount(transaction.getAmount().getAmount().toPlainString());
            builder.withCurrencyCode(transaction.getAmount().getCurrencyUnit().getCode());
        }

        if (transaction.getAmount() != null && transaction.getAmount().isPositive()) {
            builder.withType("income");
        }

        if (transaction.getAmount() != null && transaction.getAmount().isNegative()) {
            builder.withType("expense");
        }

        if (transaction.getExecutionDate() != null) {
            builder.withExecutionDate(transaction.getExecutionDate().toString());
        }
        return builder.build();
    }

}

package pl.com.michalpolak.hyperbudget.transaction.core.spi;

public interface EventPublisher {

    void publish(TransactionEvent event);
}

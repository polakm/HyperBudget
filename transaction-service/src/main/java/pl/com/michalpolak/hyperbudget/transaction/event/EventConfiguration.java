package pl.com.michalpolak.hyperbudget.transaction.event;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.EventPublisher;

public class EventConfiguration {


    @Bean
    @Value("kafka.producer.topic.name")
    public static EventPublisher createEventPublisher(String topic) {

        TransactionEventMapper mapper = new TransactionEventMapper();
        ProducerCreator producerCreator = new ProducerCreator();
        return new KafkaEventPublisher(mapper, producerCreator, topic);
    }
}

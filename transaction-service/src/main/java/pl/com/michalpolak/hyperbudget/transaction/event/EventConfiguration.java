package pl.com.michalpolak.hyperbudget.transaction.event;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.EventPublisher;
import pl.com.michalpolak.hyperbudget.transaction.event.spi.ProducerCreator;

import java.util.Properties;

@Configuration
public class EventConfiguration {

    @Bean
    @Autowired
    public static EventPublisher createEventPublisher( @Value("kafka.producer.topic") String topic, ProducerCreator producerCreator) {

        TransactionEventMapper mapper = new TransactionEventMapper();
        return new KafkaEventPublisher(mapper, producerCreator, topic);
    }

    @Bean
    static ProducerCreator createProducerCreator(
            @Value("kafka.producer.brokers") String kafkaBrokers,
            @Value("kafka.producer.clientId") String clientId) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokers);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new BasicProducerCreator(properties);
    }

    static EventPublisher createEventPublisher(String topic, String kafkaBrokers, String clientId) {

        return createEventPublisher(topic, createProducerCreator(kafkaBrokers, clientId));
    }
}

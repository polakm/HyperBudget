package pl.com.michalpolak.hyperbudget.transaction.event;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import pl.com.michalpolak.hyperbudget.transaction.event.spi.ProducerCreator;

import java.util.Properties;


class BasicProducerCreator extends ProducerCreator {

    private final Properties properties;

    BasicProducerCreator(Properties properties) {
        this.properties = new Properties(properties);
    }

    @Override
    public Producer<Long, String> createProducer() {
        return new KafkaProducer<>(this.properties);
    }
}
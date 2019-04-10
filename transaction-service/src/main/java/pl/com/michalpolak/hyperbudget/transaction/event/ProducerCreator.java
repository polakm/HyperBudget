package pl.com.michalpolak.hyperbudget.transaction.event;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Properties;


class ProducerCreator {

   private Properties properties;

    ProducerCreator(Properties properties){
        this.properties = properties;
    }

    Producer<Long, String> createProducer() {

        return new KafkaProducer<>(this.properties);
    }
}
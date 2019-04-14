package pl.com.michalpolak.hyperbudget.transaction.event.spi;

import org.apache.kafka.clients.producer.Producer;

public abstract class ProducerCreator {

   public abstract Producer<Long, String> createProducer();
}

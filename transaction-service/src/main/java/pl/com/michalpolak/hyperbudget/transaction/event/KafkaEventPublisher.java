package pl.com.michalpolak.hyperbudget.transaction.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.EventPublisher;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionEvent;

import java.text.MessageFormat;
import java.util.concurrent.ExecutionException;

@Component
class KafkaEventPublisher implements EventPublisher {

    private static String MESSAGE_PATTERN = "Error during sending event for topic: \"{0}\"  with transaction ID : \"{1}\" ";

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEventPublisher.class);

    private ProducerCreator producerCreator;

    private TransactionEventMapper mapper;

    private String topic;

    @Autowired
    KafkaEventPublisher(TransactionEventMapper mapper, ProducerCreator producerCreator, @Value("kafka.producer.topic.name") String topic){
        this.mapper = mapper;
        this.producerCreator = producerCreator;
        this.topic = topic;
    }


    @Override
    public void publish(TransactionEvent event) {

        try {
            String message = mapper.mapToEventMessage(event);
            ProducerRecord<Long, String> record = new ProducerRecord<>(topic, message);
            Producer<Long, String> producer = producerCreator.createProducer();
            producer.send(record).get();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            LOGGER.error(MessageFormat.format(MESSAGE_PATTERN, topic, event.getEntity().getId()),e);
        } catch (JsonProcessingException e) {
           throw new RuntimeException(e);
        }

    }
}

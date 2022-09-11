package com.github.vlsidlyarevich.udemy_kafka_for_beginners.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Map;

/**
 * SimpleKafkaProducer
 *
 * @author Vladislav Sidlyarevich <vlsidlyarevich@gmail.com>
 * Created on 9/10/22.
 */
@Slf4j
public class SimpleKafkaProducer<K, V> {

    private final KafkaProducer<K, V> delegate;

    public SimpleKafkaProducer(String bootstrapServers,
                               Class<?> keySerializer,
                               Class<?> valueSerializer) {
        this.delegate = new KafkaProducer<>(
                Map.of(
                        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
                        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer,
                        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer
                )
        );
    }

    public void send(String topic, K key, V value) {
        var toSent = new ProducerRecord<>(topic, key, value);
        // send - asynchronous
        delegate.send(toSent, auditCallback(topic));
        // flush - synchronous
        delegate.flush();
    }

    public void close() {
        delegate.close();
    }

    private Callback auditCallback(String topic) {
        return (metadata, exception) -> {
            if (exception == null) {
                log.info(""" 
                                Message sent with metadata:
                                topic - {}
                                partition - {}
                                offset - {}
                                timestamp - {}""",
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp());
                return;
            }

            log.error("Error during sending to topic: {}, exception: {}", topic, exception);
        };
    }
}

package com.github.vlsidlyarevich.udemy_kafka_for_beginners.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;
import java.util.concurrent.Future;

/**
 * SimpleKafkaProducer
 *
 * @author Vladislav Sidlyarevich <vlsidlyarevich@gmail.com>
 * Created on 9/10/22.
 */
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
        delegate.send(new ProducerRecord<>(topic, key, value));
        delegate.flush();
    }

    public void close() {
        delegate.close();
    }
}

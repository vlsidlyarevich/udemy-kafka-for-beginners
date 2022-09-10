package com.github.vlsidlyarevich.udemy_kafka_for_beginners.producer;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * SimpleKafkaProducer
 *
 * @author Vladislav Sidlyarevich <vlsidlyarevich@gmail.com>
 * Created on 9/10/22.
 */
public class SimpleKafkaProducer<K, V> {

    private final KafkaProducer<K, V> delegate;

    public SimpleKafkaProducer(String bootstrapServers,
                               Class<K> keySerializer,
                               Class<V> valueSerializer) {
        this.delegate = new KafkaProducer<>(
                Map.of(
                        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
                        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer,
                        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer
                )
        );
    }
}

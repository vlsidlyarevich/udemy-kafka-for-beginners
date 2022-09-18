package com.github.vlsidlyarevich.udemy_kafka_for_beginners.consumer;

import com.github.vlsidlyarevich.udemy_kafka_for_beginners.configs.KafkaConsumerProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Map;

/**
 * KafkaConsumerFactory
 *
 * @author Vladislav Sidlyarevich <vlsidlyarevich@gmail.com>
 * Created on 9/15/22.
 */
@Slf4j
public class KafkaConsumerFactory {

    public KafkaConsumer<?, ?> build(KafkaConsumerProperties consumerProperties) {
        log.info("Creating KafkaConsumer with properties: {}", consumerProperties);

        return new KafkaConsumer<>(Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerProperties.getBootstrapServers(),
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, consumerProperties.getKeySerializer().getName(),
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, consumerProperties.getValueSerializer().getName(),
                ConsumerConfig.GROUP_ID_CONFIG, consumerProperties.getGroupId(),
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"
        ));
    }
}

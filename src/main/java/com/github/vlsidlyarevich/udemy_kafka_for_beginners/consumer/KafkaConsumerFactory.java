package com.github.vlsidlyarevich.udemy_kafka_for_beginners.consumer;

import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * KafkaConsumerFactory
 *
 * @author Vladislav Sidlyarevich <vlsidlyarevich@gmail.com>
 * Created on 9/15/22.
 */
public class KafkaConsumerFactory {

    public KafkaConsumer<?, ?> build() {
        return new KafkaConsumer<Object, Object>()
    }
}

package com.github.vlsidlyarevich.udemy_kafka_for_beginners.configs;

import org.apache.kafka.common.serialization.StringSerializer;

/**
 * KafkaConsumerProperties
 *
 * @author Vladislav Sidlyarevich <vlsidlyarevich@gmail.com>
 * Created on 9/13/22.
 */
public class KafkaConsumerProperties {

    private boolean enabled = false;
    private int pollInterval = 1000;
    private String bootstrapServers;
    private Class<?> keySerializer = StringSerializer.class;
    private Class<?> valueSerializer = StringSerializer.class;
}

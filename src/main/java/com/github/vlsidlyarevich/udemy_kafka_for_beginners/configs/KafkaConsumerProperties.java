package com.github.vlsidlyarevich.udemy_kafka_for_beginners.configs;

import lombok.Data;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * KafkaConsumerProperties
 *
 * @author Vladislav Sidlyarevich <vlsidlyarevich@gmail.com>
 * Created on 9/13/22.
 */
@Data
@ConfigurationProperties(prefix = "kafka.consumer")
public class KafkaConsumerProperties {

    private boolean enabled = false;
    private int pollInterval = 1000;
    private int timeout = 10000;
    private int threadPoolSize = 1;
    private String bootstrapServers;
    private String groupId;
    private Class<?> keySerializer = StringSerializer.class;
    private Class<?> valueSerializer = StringSerializer.class;
}

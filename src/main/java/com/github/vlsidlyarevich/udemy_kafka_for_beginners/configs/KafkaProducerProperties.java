package com.github.vlsidlyarevich.udemy_kafka_for_beginners.configs;

import lombok.Data;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * KafkaProducerProperties
 *
 * @author Vladislav Sidlyarevich <vlsidlyarevich@gmail.com>
 * Created on 9/11/22.
 */
@Data
@ConfigurationProperties(prefix = "kafka.producer")
public class KafkaProducerProperties {

    private boolean enabled = false;
    private String bootstrapServers;
    private Class<?> keySerializer = StringSerializer.class;
    private Class<?> valueSerializer = StringSerializer.class;
}

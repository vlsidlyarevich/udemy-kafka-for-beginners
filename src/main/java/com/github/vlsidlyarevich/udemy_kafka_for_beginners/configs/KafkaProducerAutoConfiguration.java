package com.github.vlsidlyarevich.udemy_kafka_for_beginners.configs;

import com.github.vlsidlyarevich.udemy_kafka_for_beginners.producer.SimpleKafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * KafkaProducerConfiguration
 *
 * @author Vladislav Sidlyarevich <vlsidlyarevich@gmail.com>
 * Created on 9/10/22.
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(value = KafkaProducerProperties.class)
@ConditionalOnProperty(prefix = "kafka.producer", name = "enabled", havingValue = "true")
public class KafkaProducerAutoConfiguration {

    @Bean
    public SimpleKafkaProducer<?, ?> kafkaProducer(KafkaProducerProperties producerProperties) {
        log.info("Creating kafka producer with {}", producerProperties);
        return new SimpleKafkaProducer<>(
                producerProperties.getBootstrapServers(),
                producerProperties.getKeySerializer(),
                producerProperties.getValueSerializer());
    }
}

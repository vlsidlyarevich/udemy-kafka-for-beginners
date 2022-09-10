package com.github.vlsidlyarevich.udemy_kafka_for_beginners.configs;

import com.github.vlsidlyarevich.udemy_kafka_for_beginners.producer.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
@ConditionalOnProperty(prefix = "kafka.producer", name = "enabled", havingValue = "true")
public class KafkaProducerAutoConfiguration {

    @Bean
    public KafkaProducer kafkaProducer() {
        log.info("Creating kafka producer");
        return new KafkaProducer() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        };
    }
}

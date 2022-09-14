package com.github.vlsidlyarevich.udemy_kafka_for_beginners.configs;

import com.github.vlsidlyarevich.udemy_kafka_for_beginners.consumer.KafkaConsumerBeanPostProcessor;
import com.github.vlsidlyarevich.udemy_kafka_for_beginners.consumer.SimpleKafkaConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * KafkaConsumerAutoConfiguration
 *
 * @author Vladislav Sidlyarevich <vlsidlyarevich@gmail.com>
 * Created on 9/13/22.
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(value = KafkaConsumerProperties.class)
@ConditionalOnProperty(prefix = "kafka.consumer", name = "enabled", havingValue = "true")
public class KafkaConsumerAutoConfiguration {

    @Bean
    public KafkaConsumerBeanPostProcessor kafkaConsumerBeanPostProcessor() {
        return new KafkaConsumerBeanPostProcessor();
    }

    @Bean
    public SimpleKafkaConsumer<String, String> kafkaConsumer() {
//        log.info("Creating kafka consumer with {}", producerProperties);
        return new SimpleKafkaConsumer<>();
    }
}

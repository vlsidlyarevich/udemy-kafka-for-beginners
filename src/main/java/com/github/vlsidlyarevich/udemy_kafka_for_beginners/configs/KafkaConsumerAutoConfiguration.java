package com.github.vlsidlyarevich.udemy_kafka_for_beginners.configs;

import com.github.vlsidlyarevich.udemy_kafka_for_beginners.consumer.KafkaConsumerFactory;
import com.github.vlsidlyarevich.udemy_kafka_for_beginners.consumer.KafkaListenerBeanPostProcessor;
import com.github.vlsidlyarevich.udemy_kafka_for_beginners.consumer.KafkaListenerEndpointRegistrar;
import com.github.vlsidlyarevich.udemy_kafka_for_beginners.consumer.SimpleKafkaConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
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
    public KafkaConsumerFactory kafkaConsumerFactory() {
        return new KafkaConsumerFactory();
    }

    @Bean
    public KafkaListenerEndpointRegistrar listenerEndpointRegistrar(KafkaConsumerFactory consumerFactory,
                                                                    KafkaConsumerProperties consumerSettings) {
        KafkaConsumer<?, ?> kafkaConsumer = consumerFactory.build(consumerSettings);
        return new KafkaListenerEndpointRegistrar(kafkaConsumer, consumerSettings.getPollInterval());
    }

    @Bean
    public KafkaListenerBeanPostProcessor kafkaConsumerBeanPostProcessor(
            KafkaListenerEndpointRegistrar kafkaListenerEndpointRegistrar) {

        return new KafkaListenerBeanPostProcessor(kafkaListenerEndpointRegistrar);
    }

    @Bean
    public SimpleKafkaConsumer<String, String> kafkaConsumer() {
//        log.info("Creating kafka consumer with {}", producerProperties);
        return new SimpleKafkaConsumer<>();
    }
}

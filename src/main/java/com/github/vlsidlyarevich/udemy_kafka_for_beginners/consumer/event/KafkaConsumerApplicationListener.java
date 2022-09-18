package com.github.vlsidlyarevich.udemy_kafka_for_beginners.consumer.event;

import com.github.vlsidlyarevich.udemy_kafka_for_beginners.consumer.listener.registry.PeriodicPoolingKafkaConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * KafkaConsumerApplicationListener
 *
 * @author Vladislav Sidlyarevich <vlsidlyarevich@gmail.com>
 * Created on 9/18/22.
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KafkaConsumerApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private final PeriodicPoolingKafkaConsumer poolingKafkaConsumer;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        log.info("Starting kafka consumer {}", poolingKafkaConsumer.getClass().getName());
        poolingKafkaConsumer.startPolling();
    }
}

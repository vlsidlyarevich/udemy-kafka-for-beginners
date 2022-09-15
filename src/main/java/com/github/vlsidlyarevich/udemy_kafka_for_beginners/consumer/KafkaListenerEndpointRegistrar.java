package com.github.vlsidlyarevich.udemy_kafka_for_beginners.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * KafkaListenerEndpointRegistrar
 *
 * @author Vladislav Sidlyarevich <vlsidlyarevich@gmail.com>
 * Created on 9/15/22.
 */
public class KafkaListenerEndpointRegistrar {

    private final KafkaConsumer<?, ?> kafkaConsumer;
    private final Map<String, List<Consumer<ConsumerRecords<?, ?>>>> consumers;
    private final ThreadPoolTaskScheduler threadPoolTaskScheduler;

    public KafkaListenerEndpointRegistrar(final KafkaConsumer<?, ?> kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
        consumers = new HashMap<>();
        threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
    }

    public void register(Consumer<ConsumerRecords<?, ?>> consumer, String topic) {
        consumers.compute(topic, (k, v) -> {
            if (v == null) {
                return Arrays.asList(consumer);
            } else {
                v.add(consumer);
                return v;
            }
        });
    }

//    public void register(Consumer<ConsumerRecords<?, ?>> consumer, String topic, String partitionName) {
//        consumers.compute(topic, (k, v) -> {
//            if (v == null) {
//                return Arrays.asList(consumer);
//            } else {
//                v.add(consumer);
//                return v;
//            }
//        });
//    }

    public void startPolling() {

    }
}

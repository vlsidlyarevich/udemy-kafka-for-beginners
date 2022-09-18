package com.github.vlsidlyarevich.udemy_kafka_for_beginners.consumer.listener.registry;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;

/**
 * KafkaConsumerPollCommand
 *
 * @author Vladislav Sidlyarevich <vlsidlyarevich@gmail.com>
 * Created on 9/18/22.
 */
@RequiredArgsConstructor
public class KafkaConsumerPollCommand implements Runnable {

    private final int consumerTimeoutMs;

    private final KafkaConsumer<?, ?> kafkaConsumer;

    private final List<Consumer<ConsumerRecords<?, ?>>> subscribers;

    @Override
    public void run() {
        var consumerRecords = kafkaConsumer.poll(Duration.ofMillis(consumerTimeoutMs));
        if (!consumerRecords.isEmpty()) subscribers.forEach(callback -> callback.accept(consumerRecords));
    }
}

package com.github.vlsidlyarevich.udemy_kafka_for_beginners.consumer.listener.registry;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * KafkaListenerEndpointRegistrar
 *
 * @author Vladislav Sidlyarevich <vlsidlyarevich@gmail.com>
 * Created on 9/15/22.
 */
@Slf4j
public class PeriodicPoolingKafkaConsumer {

    private final int pollInterval;
    private final int consumerTimeoutMs;
    private final KafkaConsumer<?, ?> kafkaConsumer;
    private final Map<String, List<Consumer<ConsumerRecords<?, ?>>>> subscribedListeners;
    private final ScheduledThreadPoolExecutor scheduledExecutor;
    private final Map<String, ScheduledFuture<?>> runningTopicPoolingTasks;
    private final ExecutorService listenersExecutor;

    public PeriodicPoolingKafkaConsumer(final KafkaConsumer<?, ?> kafkaConsumer,
                                        int consumerTimeoutMs,
                                        int threadPoolSize,
                                        int pollInterval) {
        //TODO refactor
        this.pollInterval = pollInterval;
        this.consumerTimeoutMs = consumerTimeoutMs;
        this.kafkaConsumer = kafkaConsumer;
        this.subscribedListeners = new HashMap<>();
        this.scheduledExecutor = new ScheduledThreadPoolExecutor(threadPoolSize, new KafkaConsumerPoolingThreadFactory());
        this.runningTopicPoolingTasks = new HashMap<>();
        this.listenersExecutor = Executors.newCachedThreadPool();
    }

    public void register(Consumer<ConsumerRecords<?, ?>> consumer, String topic) {
        subscribedListeners.compute(topic, (k, v) -> {
            if (v == null) {
                return Arrays.asList(consumer);
            } else {
                v.add(consumer);
                return v;
            }
        });
    }

    //TODO support partitions
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
        // stop all listeners
        stopPolling();

        // start all listeners
        subscribedListeners.forEach((topic, subscribers) -> {
            // subscribe consumer
            kafkaConsumer.subscribe(List.of(topic));

            // schedule periodical pooling
            var scheduledFuture = this.scheduledExecutor
                    .scheduleWithFixedDelay(() -> {

                        // forward computation to separate thread pool
                        listenersExecutor.submit(new KafkaConsumerPollCommand(consumerTimeoutMs, kafkaConsumer, subscribers));

                    }, 0, pollInterval, TimeUnit.MILLISECONDS);

            // save pooling tasks
            this.runningTopicPoolingTasks.put(topic, scheduledFuture);
        });
    }

    public void stopPolling() {
        this.runningTopicPoolingTasks.forEach((topic, poolingTask) -> {
            log.warn("Stop pooling from topic: {}", topic);
            poolingTask.cancel(true);
        });

        kafkaConsumer.unsubscribe();
    }
}

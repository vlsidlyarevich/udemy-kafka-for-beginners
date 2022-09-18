package com.github.vlsidlyarevich.udemy_kafka_for_beginners.consumer.listener.registry;

import java.util.UUID;
import java.util.concurrent.ThreadFactory;

/**
 * KafkaConsumerPoolingThreadFactory
 *
 * @author Vladislav Sidlyarevich <vlsidlyarevich@gmail.com>
 * Created on 9/18/22.
 */
public class KafkaConsumerPoolingThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(final Runnable r) {
        return new Thread(r, "KafkaConsumerPoolingThread@" + UUID.randomUUID());
    }
}

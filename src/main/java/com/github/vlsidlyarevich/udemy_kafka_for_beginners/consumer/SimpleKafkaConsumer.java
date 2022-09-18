package com.github.vlsidlyarevich.udemy_kafka_for_beginners.consumer;

import com.github.vlsidlyarevich.udemy_kafka_for_beginners.consumer.listener.KafkaListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;

/**
 * SimpleKafkaConsumer
 *
 * @author Vladislav Sidlyarevich <vlsidlyarevich@gmail.com>
 * Created on 9/13/22.
 */
@Slf4j
public class SimpleKafkaConsumer<K, V> {

    @KafkaListener(topicName = "first_topic")
    public void listen(ConsumerRecords<K, V> records) {
        log.error("kek {}", records);
    }
}

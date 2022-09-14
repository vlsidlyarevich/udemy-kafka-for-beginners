package com.github.vlsidlyarevich.udemy_kafka_for_beginners.consumer;

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

    @KafkaConsumer(topicName = "first_topic")
    public void listen(ConsumerRecords<K, V> records) {

    }

    @KafkaConsumer(topicName = "first_topic")
    public void wronglisten(String value, Integer va) {

    }
}

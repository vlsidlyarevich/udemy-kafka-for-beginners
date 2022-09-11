package com.github.vlsidlyarevich.udemy_kafka_for_beginners.web.rest;

import com.github.vlsidlyarevich.udemy_kafka_for_beginners.producer.SimpleKafkaProducer;
import com.github.vlsidlyarevich.udemy_kafka_for_beginners.web.rest.model.KafkaProducerPublishRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * KafkaProducerController
 *
 * @author Vladislav Sidlyarevich <vlsidlyarevich@gmail.com>
 * Created on 9/11/22.
 */
@Slf4j
@RestController
@RequestMapping("/kafka/producer")
@ConditionalOnBean(SimpleKafkaProducer.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class KafkaProducerController {

    private final SimpleKafkaProducer<String, String> producer;

    @PostMapping
    public ResponseEntity<?> publish(@RequestBody KafkaProducerPublishRequest request) {
        producer.send(request.topic(), request.key(), request.body());
        return ResponseEntity.ok().build();
    }
}

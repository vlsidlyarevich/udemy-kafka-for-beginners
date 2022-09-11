package com.github.vlsidlyarevich.udemy_kafka_for_beginners.web.rest.model;

import jakarta.validation.constraints.NotEmpty;

/**
 * KafkaProducerPublishRequest
 *
 * @author Vladislav Sidlyarevich <vlsidlyarevich@gmail.com>
 * Created on 9/11/22.
 */
public record KafkaProducerPublishRequest(@NotEmpty String topic, String key, @NotEmpty String body) {

}

package com.github.vlsidlyarevich.udemy_kafka_for_beginners.configs;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * KafkaConfiguration
 *
 * @author Vladislav Sidlyarevich <vlsidlyarevich@gmail.com>
 * Created on 9/10/22.
 */
@Configuration
@ImportAutoConfiguration(KafkaProducerAutoConfiguration.class)
public class KafkaConfiguration {

}

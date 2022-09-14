package com.github.vlsidlyarevich.udemy_kafka_for_beginners.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * KafkaConsumerBeanPostProcessor
 *
 * @author Vladislav Sidlyarevich <vlsidlyarevich@gmail.com>
 * Created on 9/13/22.
 */
public class KafkaConsumerBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
        for (Method method : bean.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(KafkaConsumer.class)) continue;

            if (method.getParameterCount() > 1) {
                throw new BeanInitializationException("Found multiple arguments on @KafkaConsumer method in bean:" + bean);
            }

            if (!isValidParameters(method.getParameterTypes())) {
                throw new BeanInitializationException("Invalid method parameters for @KafkaConsumer method in bean:" + bean);
            }

            if (!Modifier.isPublic(method.getModifiers())) {
                throw new BeanInitializationException("@KafkaConsumer must be non static and public in bean:" + bean);
            }

            try {
                method.invoke(bean);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new BeanInitializationException("Exception during method invocation", e);
            }
        }

        return bean;
    }

    private boolean isValidParameters(final Class<?>[] parameterTypes) {
        return parameterTypes[0].equals(ConsumerRecords.class);
    }
}

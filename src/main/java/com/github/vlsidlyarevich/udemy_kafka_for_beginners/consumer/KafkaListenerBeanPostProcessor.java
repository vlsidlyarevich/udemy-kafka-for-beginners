package com.github.vlsidlyarevich.udemy_kafka_for_beginners.consumer;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
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
@Setter
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KafkaListenerBeanPostProcessor implements BeanPostProcessor {

    private final KafkaListenerEndpointRegistrar listenerEndpointRegistrar;

    @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
        for (Method method : bean.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(KafkaListener.class)) continue;

            if (method.getParameterCount() > 1) {
                throw new BeanInitializationException("Found multiple arguments on @KafkaConsumer method in bean:" + bean);
            }

            if (!isValidParameters(method.getParameterTypes())) {
                throw new BeanInitializationException("Invalid method parameters for @KafkaConsumer method in bean:" + bean);
            }

            if (!Modifier.isPublic(method.getModifiers())) {
                throw new BeanInitializationException("@KafkaConsumer must be non static and public in bean:" + bean);
            }

            //TODO multiple consumers called from one place?
            ProxyFactory proxyFactory = new ProxyFactory(bean);
            //TODO Луп в одном месте с настройками кол-ва потоков?
            //TODO регистрируем себя на прослушивание топика
            //TODO пул фиксированный по всем топикам и оповещениям всех слушающих

            listenerEndpointRegistrar.register((consumerRecords) -> {
                try {
                    method.invoke(bean, consumerRecords);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }, method.getAnnotation(KafkaListener.class).topicName());

            return proxyFactory.getProxy();
        }

        return bean;
    }

    private boolean isValidParameters(final Class<?>[] parameterTypes) {
        return parameterTypes[0].equals(ConsumerRecords.class);
    }
}

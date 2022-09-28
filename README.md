# Kafka for beginners course homework
![GitHub](https://img.shields.io/github/license/vlsidlyarevich/udemy-kafka-for-beginners)
[![Build artifact](https://github.com/vlsidlyarevich/udemy-kafka-for-beginners/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/vlsidlyarevich/udemy-kafka-for-beginners/actions/workflows/maven.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=vlsidlyarevich_udemy-kafka-for-beginners&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=vlsidlyarevich_udemy-kafka-for-beginners)

Udemy ["Apache Kafka Series - Learn Apache Kafka for Beginners v3"](https://www.udemy.com/course/apache-kafka/) course homework

<!-- PREREQUISITES -->
## Prerequisites

---
### Java
The project uses following version of java:

``` bash 
✗ java -version
openjdk version "17.0.4.1" 2022-08-12 LTS
OpenJDK Runtime Environment Zulu17.36+17-CA (build 17.0.4.1+1-LTS)
OpenJDK 64-Bit Server VM Zulu17.36+17-CA (build 17.0.4.1+1-LTS, mixed mode, sharing)
```

---
### Maven
Use maven wrapper or preinstalled version 3+.

<!-- HOW TO RUN -->

---
### Kafka cluster run

If you have docker installed, open your favourite terminal and run in project root:

``` bash
cd infra/kafka && docker-compose up
```

Otherwise setup your own kafka cluster or use existing one.



## Application run

---

### Run Spring Boot app

Setup kafka configs in [application.yml](https://github.com/vlsidlyarevich/udemy-kafka-for-beginners/blob/main/src/main/resources/application.yml):
```yaml

kafka:
  producer:
    enabled: true # Enable/disable the producer
    bootstrap-servers: '127.0.0.1:9092' # Your kafka cluster host
    key-serializer: org.apache.kafka.common.serialization.StringSerializer # Key serializer class
    value-serializer: org.apache.kafka.common.serialization.StringSerializer # Value serializer class
  consumer:
    enabled: true # Enable/disable the consumer
    poll-interval: 5000 # Interval between next poll task in MS
    timeout: 1000 # Kafka poll timeout, should be always less that poll-interval
    thread-pool-size: 1 # Current thread pool size, used in pooling task scheduling 
    bootstrap-servers: '127.0.0.1:9092' # Your kafka cluster host
    group-id: "test-application"
    key-serializer: org.apache.kafka.common.serialization.StringDeserializer # Key deserializer class
    value-serializer: org.apache.kafka.common.serialization.StringDeserializer # Value deserializer class

```

Then run [Application.java](https://github.com/vlsidlyarevich/udemy-kafka-for-beginners/blob/main/src/main/java/com/github/vlsidlyarevich/udemy_kafka_for_beginners/Application.java)
as usual Spring Boot app.

## Test

---
There is API exposed for testing purposes, run [request.http](https://github.com/vlsidlyarevich/udemy-kafka-for-beginners/blob/main/infra/api/publishToProducer.http)
or use your favourite request tool instead.

POST _http://localhost:8080/kafka/producer_
```json
{
  "key": "1",
  "topic": "your_topic_name",
  "body": "hello from producer!"
}
```

<!-- ADDITIONAL -->
---

## Authors

**Vladislav Sidlyarevich** - [Github profile](https://github.com/vlsidlyarevich)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

# Apache Camel with Spring Boot – Practical Guide

## Overview
This document is a practical, hands-on guide to **Apache Camel** using **Spring Boot** and **Microservices**.  
It covers real-world integration patterns using **Files, ActiveMQ, Kafka, REST APIs, JSON, XML, CSV**, and core **Enterprise Integration Patterns (EIP)**.

---

## 1. Microservice Setup

Create two Spring Boot microservices using **Spring Initializr**:

- **MS-A** → Producer
- **MS-B** → Consumer

### Common Dependencies
- Spring Web
- Apache Camel
- Camel Spring Boot Starter
- ActiveMQ / Kafka (as required)

---

## 2. Core Apache Camel Terminology

- **Camel Context** → Routes + Components
- **Route** → Endpoint + Processor + Transformer
- **Endpoint** → File, Queue, Topic, DB, HTTP
- **Component** → Kafka, JMS, JSON, XML, etc.
- **Message** → Body + Headers + Attachments

---

## 3. Transformation and Processing

### Transformation
```java
.transform().constant("Message changed")
```
Using Bean:
```java
.bean("myTransformer")
```

### Processing
Using Processor:
```java
.process(new SimpleProcessor())
```

---

## 4. File Routing
```java
from("file:file/input")
    .log("${body}")
    .to("file:file/output");
```

---

## 5. ActiveMQ Integration

### Producer (MS-A)
```java
from("timer:active-mq-timer?period=10000")
    .transform().constant("My message for ActiveMQ")
    .to("activemq:my-activemq-queue");
```

### Consumer (MS-B)
```java
from("activemq:my-activemq-queue")
    .to("log:received-message-from-active-mq");
```

---

## 6. JSON with ActiveMQ

### Producer
```java
from("file:file/json")
    .to("activemq:my-activemq-queue");
```

### Consumer
```java
from("activemq:my-activemq-queue")
    .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
    .to("log:json-received");
```

---

## 7. XML Processing

```java
.unmarshal().jacksonXml(CurrencyExchange.class)
```

---

## 8. Kafka Integration

### Producer
```java
from("timer:kafka-timer?period=5000")
    .to("kafka:my-topic?brokers=localhost:9092");
```

### Consumer
```java
from("kafka:my-topic?brokers=localhost:9092&groupId=my-group")
    .to("log:kafka-received");
```

---

## 9. REST API Integration

```java
restConfiguration().host("localhost").port(8000);

from("timer:rest-api-consumer?period=10000")
    .to("rest:get:/currency-exchange/from/{from}/to/{to}");
```

---

## 10. Choice Pattern

```java
.choice()
  .when(simple("${file:ext} == 'xml'"))
  .otherwise()
.end();
```

---

## 11. Direct Component

```java
.to("direct:log-file-values");
```

---

## 12. Pipeline vs Multicast

### Pipeline (Default)
Sequential processing.

### Multicast
```java
.multicast().to("log:a","log:b");
```

---

## 13. Split Pattern

```java
.split(body()).to("log:split-files");
```

---

## 14. Aggregate Pattern

```java
.aggregate(simple("${body.to}"), new ArrayListAggregationStrategy())
.completionSize(3);
```

---

## 15. Routing Slip

```java
.routingSlip(simple("direct:end1,direct:end2"));
```

---

## 16. Best Practices

- Enable tracing
- Externalize configs
- Avoid ObjectMessage in JMS
- Use deadLetterChannel
- Refer Apache Camel official docs

---

## Conclusion
Apache Camel simplifies complex integration problems using EIP patterns.  
This guide provides a strong foundation for real-world projects and interviews.

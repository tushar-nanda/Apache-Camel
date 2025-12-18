package com.in28min.microservice.camel_microservice_a.routes.c;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqSenderRouter extends RouteBuilder {

    @Override
    public void configure() {

//        from("timer:active-mq-timer?period=10000")
//                .transform().constant("My message for ActiveMQ")
//                .to("activemq:queue:my-activemq-queue");

        // doing unmarshalling type of thing
        from("file:file/json")
                .log("${body}")
                .to("activemq:queue:my-activemq-queue");


    }
}

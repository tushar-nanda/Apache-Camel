package com.in28min.microservice.camel_microservice_a.routes.a;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyFirstTimerRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception{
        //queue
        ///  transformation
        // database

//        for now we are using
//        timer , transformation , log
        // we also have something like tranformation and processing
        // tansformation means -> making changes in the body or meessage
        // processing means -> not changing the message or body just using that
        from("timer:first-timer")
                .transform().constant("Hi My MS-a" + LocalDateTime.now())
                .log("${body}")
                .to("log:first-timer-a");
    }
}

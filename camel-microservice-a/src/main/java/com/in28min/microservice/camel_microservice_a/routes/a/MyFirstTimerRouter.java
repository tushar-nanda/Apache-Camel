package com.in28min.microservice.camel_microservice_a.routes.a;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyFirstTimerRouter extends RouteBuilder {

    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;

    @Override
    public void configure() throws Exception{
        //queue
        ///  transformation
        // database

//        for now we are using
//        timer , transformation , log
        // we also have something like tranformation and processing
        // tansformation means -> making changes in the body or meessage ( chaneges the vody)
        // processing means -> not changing the message or body just using that ( does not changes the body)
//        from("timer:first-timer")
//            .transform().constant("Hi My MS-a" + LocalDateTime.now())
//                .bean("getCurrentTimeBean")
//                .log("${body}")
//                .to("log:first-timer-a");


        from("timer:first-timer")
                .log("${body}")
                .transform().constant("My Constant message")
                .log("${body}")
                .bean("getCurrentTimeBean")
                .log("${body}")
                .to("log:first-timer-a");

    }
}

@Component
class GetCurrentTimeBean
{
    public String getCurrentTime()
    {
        return "the time is " + LocalDateTime.now();
    }
}
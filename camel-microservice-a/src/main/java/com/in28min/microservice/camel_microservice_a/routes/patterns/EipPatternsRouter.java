package com.in28min.microservice.camel_microservice_a.routes.patterns;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class EipPatternsRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        //multicast pattern
//        from("timer:multicast?period=10000")
//                .multicast()
//                .to("log:something1" , "log:something2" , "log:something3");
//

        // how we can process the file line by line
        from("file:file/csv")
                .unmarshal().csv()
                .split(body())
                .log("data sending to the split-queue")
                .to("activemq:split-queue")
                .end();

    }
}

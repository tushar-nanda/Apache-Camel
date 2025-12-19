package com.in28min.microservice.camel_microservice_a.routes.patterns;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EipPatternsRouter extends RouteBuilder {

    @Autowired
    SplitterComponent splitterComponent;
    @Override
    public void configure() throws Exception {

        //multicast pattern
//        from("timer:multicast?period=10000")
//                .multicast()
//                .to("log:something1" , "log:something2" , "log:something3");
//

        // how we can process the file line by line
//        from("file:file/csv")
//                .unmarshal().csv()
//                .split(body())
//                .log("data sending to the split-queue")
//                .to("activemq:split-queue")
//                .end();

        //splting based on bean
        from("file:file/csv")
                .convertBodyTo(String.class)
                .split(method(splitterComponent))
                .to("activemq:split-queue");

    }
}

@Component
class SplitterComponent
{
    public List<String> splitInput(String body)
    {

        return List.of("AVC" , "AFE");
    }
}

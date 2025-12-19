package com.in28min.microservice.camel_microservice_a.routes.patterns;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
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
//        from("file:file/csv")
//                .convertBodyTo(String.class)
//                .split(method(splitterComponent))
//                .to("activemq:split-queue");

        //aggreae gate pattern
//        from("file:file/aggregate-json")
//                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
//                .aggregate(simple("${body.to}"), new ArrayListAggregationStrategy())
//                .completionSize(3)
//                //.completionTimeout(HIGHEST)
//                .to("log:aggregate-json");

        // routing slip pattern
        String routingSlip = "direct:endpoint1,direct:endpoint2";

        from("timer:routingSlip?period=10000")
                .transform().constant("My message is hardcoded")
                .routingSlip(simple(routingSlip));

        from("direct:endpoint1")
                .to("log:Hi directpoint1");
        from("direct:endpoint2")
                .to("log:Hi directpoint2");
        from("direct:endpoint3")
                .to("log:Hi directpoint3");
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

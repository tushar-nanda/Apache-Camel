package com.in28min.microservice.camel_microservice_a.routes.b;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
public class MyFileRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("file:file/input")
                .routeId("Files-Input-Route")
                .transform().body(String.class)
                .choice()
                    .when(simple("${file:ext} ends with 'xml'"))
                    .log("XML file")
                    .when(simple("${body} contains 'USD'"))
                    .log("not a xml but has USD")
                .otherwise()
                .log("not an xml file")
                .to("file:file/output");
    }
}

package com.in28min.microservice.camel_microservice_b.routes;

import com.in28min.microservice.camel_microservice_b.CurrencyExchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ActiveMqReceiverRouter extends RouteBuilder {

   @Autowired
    MyCurrencyExchangeProcessor myCurrencyExchangeProcessor;

   @Autowired
   MyCurrencyExchangeTransformation myCurrencyExchangeTransformation;

    @Override
    public void configure() throws Exception {

        from("activemq:my-activemq-queue")
                .unmarshal().json(JsonLibrary.Jackson , CurrencyExchange.class)
                .bean(myCurrencyExchangeProcessor)
                .bean(myCurrencyExchangeTransformation)
                .to("log:received-message-from-active-mq");


    }

}
@Component
class MyCurrencyExchangeProcessor {
    public Logger logger = LoggerFactory.getLogger(MyCurrencyExchangeProcessor.class);

    public void processMessage(CurrencyExchange currencyExchange) {

        logger.info("Do some processing wiht currencyExchange.getConversionMultiple() value which is {}",
                currencyExchange.getConversionMultiple());

    }
}

@Component
class MyCurrencyExchangeTransformation {
    public Logger logger = LoggerFactory.getLogger(MyCurrencyExchangeTransformation.class);

    public CurrencyExchange processMessage(CurrencyExchange currencyExchange)
    {
        logger.info("to some processinf tiwht current getConverionMultiple : {}" ,
        currencyExchange.getConversionMultiple());

        currencyExchange.setConversionMultiple(currencyExchange.getConversionMultiple().multiply(BigDecimal.TEN));
        logger.info("Do some processing wiht currencyExchange.getConversionMultiple() value which is {}",
                currencyExchange.getConversionMultiple());
        return currencyExchange;
    }


}

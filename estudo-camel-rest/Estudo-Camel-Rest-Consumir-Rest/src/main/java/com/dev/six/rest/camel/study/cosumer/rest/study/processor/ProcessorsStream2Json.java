package com.dev.six.rest.camel.study.cosumer.rest.study.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ProcessorsStream2Json implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String stringBody = exchange.getIn().getBody(String.class);
        exchange.getIn().setBody(
                mapper.readTree(
                        stringBody
                )
        );
    }
}
package com.dev.six.rest.camel.study.cosumer.rest.study.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PreProcessResponse implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map mapBody = (Map) exchange.getIn().getBody();
        String stringBody = mapper.writeValueAsString(mapBody);
        exchange.getIn().setBody(stringBody);
    }
}
package com.dev.six.rest.camel.study.cosumer.rest.study.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class GeneralExceptionProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        String failedEndpoint = exchange.getProperty(Exchange.FAILURE_ENDPOINT).toString();
        Exception e = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        String body = exchange.getIn().getBody(String.class);

        Map<String,Object> maps = new LinkedHashMap<>();
        maps.put("ExceptionMessage", e.getMessage());
        maps.put("ExceptionClass", e.getClass().toString());
        maps.put("EndpointWithFault", failedEndpoint);

        if (body != null && !body.isEmpty()) {
            maps.put("BodyMessage", mapper.readTree(body));
        }

        exchange.getIn().setBody(mapper.readTree(mapper.writeValueAsString(maps)));
//        exchange.getIn().getHeaders().put(Exchange.HTTP_RESPONSE_CODE, 400);
    }
}

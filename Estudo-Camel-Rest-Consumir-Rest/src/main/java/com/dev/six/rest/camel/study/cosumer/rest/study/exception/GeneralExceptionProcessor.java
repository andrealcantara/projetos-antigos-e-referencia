package com.dev.six.rest.camel.study.cosumer.rest.study.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class GeneralExceptionProcessor implements Processor {
    public static final String LABEL_EXCEPTION_MESSAGE = "ExceptionMessage";
    public static final String LABEL_EXCEPTION_CLASS = "ExceptionClass";
    public static final String LABEL_ENDPOINT_WITH_FAULT = "EndpointWithFault";
    public static final String LABEL_ENDPOINT_FROM_FAULT = "EndpointFromFault";
    public static final String LABEL_BODY_MESSAGE = "BodyMessage";

    @Override
    public void process(Exchange exchange) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String body;
        Map<String,Object> maps = new LinkedHashMap<>();

        String failedEndpoint = String.valueOf(exchange.getProperty(Exchange.FAILURE_ENDPOINT));
        String fromEndpoint = String.valueOf(exchange.getFromEndpoint().getEndpointUri());
        Exception e = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        try {
            body = mapper.writeValueAsString(exchange.getIn().getBody(LinkedHashMap.class));
        } catch (JsonProcessingException | ClassCastException ex) {
            body = exchange.getIn().getBody(String.class);
        }

        maps.put(LABEL_EXCEPTION_MESSAGE, e.getMessage());
        maps.put(LABEL_EXCEPTION_CLASS, e.getClass().toString());
        maps.put(LABEL_ENDPOINT_WITH_FAULT, failedEndpoint);
        maps.put(LABEL_ENDPOINT_FROM_FAULT, fromEndpoint);

        if (body != null && !body.isEmpty()) {
            try {
                maps.put(LABEL_BODY_MESSAGE, mapper.readTree(body));
            }catch (JsonParseException jsonParseException){
                int MAX_SIZE_OUTPUT_ERROR_BODY = 500;
                int VERIFY_MINOR_BUFF_STRING;
                VERIFY_MINOR_BUFF_STRING =
                        MAX_SIZE_OUTPUT_ERROR_BODY < body.length() ?
                                MAX_SIZE_OUTPUT_ERROR_BODY :
                                body.length();
                maps.put(LABEL_BODY_MESSAGE,
                        body.substring(0, VERIFY_MINOR_BUFF_STRING ));
            }
        }
        maps.put("stackTrace", e.getStackTrace());
        exchange.getIn().setBody(mapper.readTree(mapper.writeValueAsString(maps)));
    }
}

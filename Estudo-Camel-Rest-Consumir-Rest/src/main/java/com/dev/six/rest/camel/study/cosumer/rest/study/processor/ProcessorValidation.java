package com.dev.six.rest.camel.study.cosumer.rest.study.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProcessorValidation {

    public static final String[] fields = {"descricao","ativo","tipoTransacao","valorTransacao","dateTimeTransferencia","conta"};
    public void preValidationPutPost(Exchange exchange) throws Exception {
        int i = 0;
        ObjectMapper mapper = new ObjectMapper();
        Boolean hasAllFields = Boolean.TRUE;
        Map<String,?> map = exchange.getIn().getBody(LinkedHashMap.class);
        for ( i = 0; i < fields.length; i++) {
            if(!map.containsKey(fields[i])) {
                hasAllFields = Boolean.FALSE;
                break;
            }
        }

    }
}

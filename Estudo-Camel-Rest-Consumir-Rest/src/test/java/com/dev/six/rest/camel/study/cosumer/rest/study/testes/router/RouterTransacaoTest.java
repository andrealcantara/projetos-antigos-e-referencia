package com.dev.six.rest.camel.study.cosumer.rest.study.testes.router;

import com.dev.six.rest.camel.study.cosumer.rest.study.router.RestRouterTransacao;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

public class RouterTransacaoTest extends CamelTestSupport {

    @Autowired
    protected TestRestTemplate restTemplate;

    protected ObjectMapper objectMapper = new ObjectMapper();


    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RestRouterTransacao();
    }

    @Test
    public void routerRestGet() throws Exception{

        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/transacao", String.class);
        Object nodeActual = this.objectMapper.readTree(response.getBody());
        Object nodeExpected = getExampleGetId1();
        MatcherAssert.assertThat("Deu ruim", nodeExpected.equals(nodeActual));
    }


    private JsonNode getExampleGetId1() throws Exception {
        StringBuilder sb = new StringBuilder();

        sb.append("{\"data\":{");

        sb.append("\"id\":");
        sb.append("1");

        sb.append(",\"descricao\":");
        sb.append("\"Descricao Transação 43374914889\"");

        sb.append(",\"ativo\":");
        sb.append("true");

        sb.append(",\"tipoTransacao\":");
        sb.append("\"ACERTO\"");

        sb.append(",\"valorTransacao\":");
        sb.append("5702052");

        sb.append(",\"dateTimeTransferencia\":");
        sb.append("\"2019-06-20T09:52:32.957+0000\"");

        sb.append(",\"conta\":");
        sb.append("42161905848816");

        sb.append("},");

        sb.append("\"warns\":[],");
        sb.append("\"error\":null");

        sb.append("}");

        return objectMapper.readTree(sb.toString());
    }


    private ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }
}

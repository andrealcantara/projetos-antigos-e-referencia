package com.dev.six.rest.camel.study.cosumer.rest.study.testes.rest;

import com.dev.six.rest.camel.study.cosumer.rest.study.testes.AbstractRestCamelTest;
import com.fasterxml.jackson.databind.JsonNode;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;

public class CamelRestTransacaoTest extends AbstractRestCamelTest {
    private final static String node_não_esta_igual = "Node não esta igual";

    @Test
    public void testGet() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/transacao", String.class);
        Object nodeActual = this.objectMapper.readTree(response.getBody());
        Object nodeExpected = getExampleGetId1();
        MatcherAssert.assertThat(node_não_esta_igual, nodeExpected.equals(nodeActual));
    }

    private Object getExampleGetId1() {
        return "null";
    }

}

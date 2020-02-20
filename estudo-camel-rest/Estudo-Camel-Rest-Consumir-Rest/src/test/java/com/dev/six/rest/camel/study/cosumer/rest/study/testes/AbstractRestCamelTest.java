package com.dev.six.rest.camel.study.cosumer.rest.study.testes;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Produce;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractRestCamelTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    protected ObjectMapper objectMapper;

    @Produce
    private ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

}

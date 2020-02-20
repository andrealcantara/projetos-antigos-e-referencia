package com.dev.six.rest.camel.studyrestcamelconsumerdb.routers;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Component
public class RestRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {


        restConfiguration().component("restlet")
                .host("localhost")
                .port(9090)
                .enableCORS(true)
                .bindingMode(RestBindingMode.json);

        rest("/api/v1")
                .consumes("application/json")
                .produces("application/json")

                .get().id("getAllTransacoes")
                .route()
                .setHeader(Exchange.HTTP_METHOD,constant("DELETE"))
                .setHeader(Exchange.HTTP_URI,simple("http://localhost:7080/bancoRest/api/v1/transacao/1"))
                .to("http4:localhost:7080")
                .convertBodyTo(String.class)


//                .transform().simple("teste")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String input = (String) exchange.getIn().getBody();

                        JSONParser parser = new JSONParser();
                        Object obj = parser.parse(input);
                        exchange.getOut().setBody(obj);

//                        JSONArray jsonArray = (JSONArray) obj;
//
//                        jsonArray.stream().forEach(item -> {
//                            String id = ((JSONObject) item).get("id").toString();
//                            System.out.println(id);
//                        });

//                        exchange.getIn().setBody("OK", String.class);
                    }
                })
                .endRest()
        ;

//        from("direct:getBancoRest").id("getBancoRest-Router").
    }
}

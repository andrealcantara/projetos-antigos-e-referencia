package com.dev.six.rest.camel.study.cosumer.rest.study.router;

import com.dev.six.rest.camel.study.cosumer.rest.study.model.ResponseTransacao;
import com.dev.six.rest.camel.study.cosumer.rest.study.model.Transacao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Predicate;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestConfigurationDefinition;
import org.apache.camel.model.rest.RestDefinition;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RestRouterTransacao extends RouteBuilder {

    private static final String ROUTER_DIRECT_PRODUCTS = "direct:consumer-products";
    private static final String ROUTER_DIRECT_POST_PRODUCT = "direct:consumer-post-products";
    private static final String ROUTER_DIRECT_PUT_PRODUCT = "direct:consumer-put-product";
    private static final String ROUTER_DIRECT_DELETE_PRODUCT = "direct:consumer-delete-product";
    private static final String ID_SHOW_PRODUCTS = "id_all_product";
    private static final String ID_SHOW_PRODUCTS_BY_ID = "id_product_by_id";
    private static final String ID_POST_PRODUCT = "id_post_product";
    private static final String ID_PUT_PRODUCT = "id_put_product";
    private static final String ID_DELETE_PRODUCT = "id_delete_product";
    private static final String ID_ROUTER_DIRECT_GET_PRODUCT = "id_router_get_product";
    private static final String ID_ROUTER_DIRECT_POST_PRODUCT = "id_router_post_product";
    private static final String ID_ROUTER_DIRECT_PUT_PRODUCT = "id_router_put_product";
    private static final String ID_ROUTER_DIRECT_DELETE_PRODUCT = "id_router_delete_product";
    private static final String APPLICATION_JSON = "application/json; charset=UTF-8";
    private static final String URI_BASE = "localhost:7080/bancoRest/api/v1/transacao/";
    private static final String URL_DEFAULT = "http://" + URI_BASE;


    @Override
    public void configure() throws Exception {

//        onException(Exception.class)
//                .redeliveryDelay(5).maximumRedeliveries(5)
//                .log(LoggingLevel.ERROR,"Excecoes de mensagens")
//                .handled(true);


        restConfigurationInit();

        restInit("api/v1/list")
                .get()
                .id(ID_SHOW_PRODUCTS).description("Consome outro rest de banco de dados")
                .outType(Transacao[].class)
                .route()
                .to(ROUTER_DIRECT_PRODUCTS)
                .endRest()

                .get("/{id}")
                .id(ID_SHOW_PRODUCTS_BY_ID).description("Consome outro rest de banco de dados, pesquisando por id")
//                .outType(ResponseTransacao.class)
                .route()
                .to(ROUTER_DIRECT_PRODUCTS)
                .endRest()

                .post()
                .id(ID_POST_PRODUCT).description("Consome outro rest e passa para ele dados para inserir no banco")
                .route()
                .to(ROUTER_DIRECT_POST_PRODUCT)
                .endRest()

                .put()
                .id(ID_PUT_PRODUCT).description("Consome outro rest e passa para ele dados para ser alterado")
                .route()
                .to(ROUTER_DIRECT_PUT_PRODUCT)
                .endRest()

                .delete("/{id}")
                .id(ID_DELETE_PRODUCT).description("Consome outro rest e passa para ele dados para ser deletado")
                .route()
                .to(ROUTER_DIRECT_DELETE_PRODUCT)
                .endRest()


        ;

        from(ROUTER_DIRECT_PRODUCTS).id(ID_ROUTER_DIRECT_GET_PRODUCT)
                .log(LoggingLevel.INFO, "Entrou no- " + ID_ROUTER_DIRECT_GET_PRODUCT)
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader(Exchange.HTTP_URI, simple(URL_DEFAULT + "${header.id}"))
                .to("http4:" + URI_BASE)
                .process(new ProcessorsStream2Json())
                .end()
        ;

        from(ROUTER_DIRECT_POST_PRODUCT).id(ID_ROUTER_DIRECT_POST_PRODUCT)
                .log(LoggingLevel.INFO, "Entrou no " + ID_ROUTER_DIRECT_POST_PRODUCT)
                .process(new PreProcessResponse())
                .setHeader(Exchange.CONTENT_TYPE, constant(APPLICATION_JSON))
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.HTTP_URI, simple(URL_DEFAULT))
                .to("http4:" + URI_BASE)
                .process(new ProcessorsStream2Json())
                .end();

        from(ROUTER_DIRECT_PUT_PRODUCT).id(ID_ROUTER_DIRECT_PUT_PRODUCT)
                .log(LoggingLevel.INFO,"Entrou no " + ID_ROUTER_DIRECT_PUT_PRODUCT)
                .process(new PreProcessResponse())
                .setHeader(Exchange.HTTP_METHOD, constant("PUT"))
                .setHeader(Exchange.CONTENT_TYPE, constant(APPLICATION_JSON))
                .setHeader(Exchange.HTTP_URI, simple(URL_DEFAULT))
                .to("http4:" + URI_BASE)
                .process(new ProcessorsStream2Json())
                .end()
        ;

        from(ROUTER_DIRECT_DELETE_PRODUCT).id(ID_ROUTER_DIRECT_DELETE_PRODUCT)
                .log(LoggingLevel.INFO,"Entrou no " + ID_ROUTER_DIRECT_DELETE_PRODUCT)
                .setHeader(Exchange.HTTP_METHOD, constant("DELETE"))
                .setHeader(Exchange.CONTENT_TYPE, constant(APPLICATION_JSON))
                .setHeader(Exchange.HTTP_URI, simple(URL_DEFAULT + "${header.id}"))
                .to("http4:" + URI_BASE)
                .setBody(simple(""))
                .setHeader(Exchange.HTTP_RESPONSE_CODE,simple("200"))
                .convertBodyTo(String.class)
                .end()
        ;

    }

    private RestDefinition restInit() {
        return restInit(null);
    }

    private RestDefinition restInit(String paramPath) {
        RestDefinition restVariable = null;
        if (paramPath == null || paramPath.trim().isEmpty()) {
            restVariable = rest();
        } else {
            restVariable = rest(paramPath);
        }
        return restVariable
                .consumes(APPLICATION_JSON)
                .produces(APPLICATION_JSON)
                ;
    }

    private RestConfigurationDefinition restConfigurationInit() {
        return restConfiguration()
                .component("restlet")
                .port(9090)
                .host("localhost")
                .bindingMode(RestBindingMode.json)
                .apiContextPath("/api-doc")
                .apiProperty("api.title",
                        "Minha Coleção de Routers API")
                .dataFormatProperty("prettyPrint", "true")
                .enableCORS(true);
    }

    public static ProcessorDefinition<?> testChoice(ProcessorDefinition processor) {
        return processor.choice()
                .when(new Predicate() {
                    @Override
                    public boolean matches(Exchange exchange) {
                        return exchange.getIn().getHeaders()
                                .getOrDefault(Exchange.HTTP_RESPONSE_CODE, "").toString().equals("201");
                    }
                })
                .log(LoggingLevel.INFO, "Entrou no choice")
                .endChoice()
                .otherwise()
                .log(LoggingLevel.ERROR, "entrou mas nao passou no matches");
    }

    public void configureOlder() throws Exception {
        restConfigurationInit();

        restInit("/hellow")
                .to("direct:hello");

        from("direct:hello")
                .log(LoggingLevel.INFO, "HelloW")
                .transform().simple("HelloW");
    }

    public class ProcessorsStream2Json implements Processor {
        @Override
        public void process(Exchange exchange) throws Exception {
            ObjectMapper mapper = new ObjectMapper();
            String stringBody = exchange.getIn().getBody(String.class);
            exchange.getOut().setBody(
                    mapper.readTree(
                            stringBody
                    )
            );
            exchange.getOut().setHeaders(exchange.getIn().getHeaders());
        }
    }


    public class PreProcessResponse implements Processor {
        @Override
        public void process(Exchange exchange) throws Exception {
            ObjectMapper mapper = new ObjectMapper();
            Map mapBody = (Map) exchange.getIn().getBody();
            String stringBody = mapper.writeValueAsString(mapBody);
            exchange.getOut().setBody(stringBody);
            exchange.getOut().setHeaders(exchange.getIn().getHeaders());
        }
    }
}

package com.dev.six.rest.camel.study.cosumer.rest.study.router;

import com.dev.six.rest.camel.study.cosumer.rest.study.exception.GeneralExceptionProcessor;
import com.dev.six.rest.camel.study.cosumer.rest.study.processor.PreProcessResponse;
import com.dev.six.rest.camel.study.cosumer.rest.study.processor.ProcessorsStream2Json;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestConfigurationDefinition;
import org.apache.camel.model.rest.RestDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestRouterTransacao extends RouteBuilder {

    @Autowired
    private GeneralExceptionProcessor exceptionProcessor;
    @Autowired
    private ProcessorsStream2Json processorsStream2Json;
    @Autowired
    private PreProcessResponse preProcessResponse;

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

    private static final String APPLICATION_JSON_MORE_CHARSET_UTF8 = "application/json; charset=UTF-8";

    private static final String URI_BASE = "localhost:7080/bancoRest/api/v1/transacao/";

    private static final String URL_DEFAULT = "http://" + URI_BASE;


    @Override
    public void configure() throws Exception {

        onException(Throwable.class)
                .redeliveryDelay(5).maximumRedeliveries(5)
                .log(LoggingLevel.ERROR,"Excecoes de mensagens")
                .process(exceptionProcessor)
                .log(LoggingLevel.ERROR,"${body}")
                .handled(true);

        restConfigurationInit();

        startRestPaths();

        from(ROUTER_DIRECT_PRODUCTS).id(ID_ROUTER_DIRECT_GET_PRODUCT)
                .log(LoggingLevel.INFO, "Entrou no- " + ID_ROUTER_DIRECT_GET_PRODUCT)
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader(Exchange.HTTP_URI, simple(URL_DEFAULT + "${header.id}"))
                .to("http4:" + URI_BASE)

                .process(processorsStream2Json)
                .end()
        ;

        from(ROUTER_DIRECT_POST_PRODUCT).id(ID_ROUTER_DIRECT_POST_PRODUCT)
                .log(LoggingLevel.INFO, "Entrou no " + ID_ROUTER_DIRECT_POST_PRODUCT)
                .process(preProcessResponse)
                .setHeader(Exchange.CONTENT_TYPE, constant(APPLICATION_JSON_MORE_CHARSET_UTF8))
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.HTTP_URI, simple(URL_DEFAULT))
                .to("http4:" + URI_BASE)
                .process(processorsStream2Json)
                .end();

        from(ROUTER_DIRECT_PUT_PRODUCT).id(ID_ROUTER_DIRECT_PUT_PRODUCT)
                .log(LoggingLevel.INFO,"Entrou no " + ID_ROUTER_DIRECT_PUT_PRODUCT)
                .process(preProcessResponse)
                .setHeader(Exchange.HTTP_METHOD, constant("PUT"))
                .setHeader(Exchange.CONTENT_TYPE, constant(APPLICATION_JSON_MORE_CHARSET_UTF8))
                .setHeader(Exchange.HTTP_URI, simple(URL_DEFAULT))
                .to("http4:" + URI_BASE)
                .process(processorsStream2Json)
                .end()
        ;

        from(ROUTER_DIRECT_DELETE_PRODUCT).id(ID_ROUTER_DIRECT_DELETE_PRODUCT)
                .log(LoggingLevel.INFO,"Entrou no " + ID_ROUTER_DIRECT_DELETE_PRODUCT)
                .setHeader(Exchange.HTTP_METHOD, constant("DELETE"))
                .setHeader(Exchange.CONTENT_TYPE, constant(APPLICATION_JSON_MORE_CHARSET_UTF8))
                .setHeader(Exchange.HTTP_URI, simple(URL_DEFAULT + "${header.id}"))
                .setBody(simple(""))
                .toD("http4:" + URI_BASE + "${header.id}")
//                .setHeader(Exchange.HTTP_RESPONSE_CODE,simple("200"))
                .convertBodyTo(String.class)
                .end()
        ;
    }

    private void startRestPaths() {
        restInit("api/v1/list")
                .get()
                .id(ID_SHOW_PRODUCTS).description("Consome outro rest de banco de dados")
                .route()
//                .toD("")
                .to(ROUTER_DIRECT_PRODUCTS)
                .endRest()

                .get("/{id}")
                .id(ID_SHOW_PRODUCTS_BY_ID).description("Consome outro rest de banco de dados, pesquisando por id")
                .route()
                .to(ROUTER_DIRECT_PRODUCTS)
                .endRest()

                .post()
                .id(ID_POST_PRODUCT).description("Consome outro rest e passa para ele dados para inserir no banco")
                .route()
                .to("json-validator:" +
                        "com/dev/six/rest/camel/study/cosumer/rest/study/transacaoDTOValidation.json")
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
    }

    private RestDefinition restInit() {
        return restInit(null);
    }

    private RestDefinition restInit(String param) {
        RestDefinition restInit;
        if (param == null || param.trim().isEmpty()) {
            restInit = rest();
        } else {
            restInit = rest(param);
        }
        return restInit
                .consumes(APPLICATION_JSON_MORE_CHARSET_UTF8)
                .produces(APPLICATION_JSON_MORE_CHARSET_UTF8)
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
                .endpointProperty("throwExceptionOnFailure","false")
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
}

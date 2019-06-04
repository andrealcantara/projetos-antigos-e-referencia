package camel.consumer.api.banco.rest.demo.router;

import camel.consumer.api.banco.rest.demo.exception.ExceptionProcessor;
import camel.consumer.api.banco.rest.demo.model.Transacao;
import camel.consumer.api.banco.rest.demo.processor.OutputProcessor;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Routers extends RouteBuilder {

    @Autowired
    private ExceptionProcessor exceptionProcessor;

    public Routers() {
    }

    public Routers(CamelContext context) {
        super(context);
    }

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .redeliveryDelay(500L)
                .maximumRedeliveries(5)
                .log("Exceção ao consumir o rest do banco")
                .process(new ExceptionProcessor())
                .handled(true);

        restConfiguration().component("servlet")
                .bindingMode(RestBindingMode.json)
                .port(8091)
                .apiContextPath("/api-doc")
                .apiProperty("api.title",
                        "Minha Coleção de Transações API")
                .dataFormatProperty("prettyPrint", "true")
                ;

        rest("/api/v1")
                .description("Transacao API consumindo REST DB")
                .consumes("application/json")
                .produces("application/json").enableCORS(false)

                .get()
                .id("All Transacao")
                .description("Retorna todas as transacoes")
                .outType(Transacao[].class)
                .route()
                .setHeader(Exchange.HTTP_METHOD, simple("get"))
//                .setHeader(Exchange.HTTP_URI, simple("http://localhost:7080/bancoRest/api/v1/transacao/"))
                .to("http4:localhost:7080/bancoRest/api/v1/transacao/")
                .bean(OutputProcessor.class)
                .endRest()

                .get("/{id}").id("Find by ID transacao")
                .description("Retorna transação por id.") // <5> Operação que retorna todas as transações.
                .outType(Transacao.class)
                .route()
                .setHeader(Exchange.HTTP_METHOD, simple("get"))
//                .setHeader(Exchange.HTTP_URI, simple("http://localhost:7080/bancoRest/api/v1/transacao/"))
                .to("http4:localhost:7080/bancoRest/api/v1/transacao/:#id")
                .bean(OutputProcessor.class)
                .endRest();
    }
}

package camel.consumer.api.banco.rest.demo.exception;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ExceptionProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Exception e = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        System.out.println("Exception Message: " + e.getMessage());
        System.out.println("Exception Class" + e.getClass());
        String failure = exchange.getProperty(Exchange.FAILURE_ENDPOINT).toString();

        System.out.println("Falha no Endpoint - " + failure);
        exchange.getIn().setBody("Endpoint com problema.");
    }
}

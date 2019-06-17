package com.dev.six.rest.camel.study.cosumer.rest.study.processor;

import com.dev.six.rest.camel.study.cosumer.rest.study.exception.TransacaoInvalidException;
import com.dev.six.rest.camel.study.cosumer.rest.study.model.TipoTransacao;
import com.dev.six.rest.camel.study.cosumer.rest.study.model.Transacao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class TransacaoBodyValidationProcessor implements Processor {

    public static final String verifyID = "verifyID";


    @Override
    public void process(Exchange exchange) throws Exception {
        Boolean verifyID =
                 Boolean.valueOf((String) exchange.getIn().getHeaders()
                        .getOrDefault(TransacaoBodyValidationProcessor.verifyID,"false"));
        String body;
        Transacao transacao;
        Map maps;
        ObjectMapper mapper = new ObjectMapper();
        maps = exchange.getIn().getBody(LinkedHashMap.class);
        body = mapper.writeValueAsString(maps);
        transacao = mapper.readValue(body, Transacao.class);
        validate(transacao,verifyID);

    }

    public void validate(Transacao transacao, boolean verifyID) throws TransacaoInvalidException {
        if(transacao == null) {
            throw new TransacaoInvalidException("Error do JSON - Nao e possivel da parse para o objeto TRANSACAO");
        }

        List<String> sb = new ArrayList<String>();
        Long id = transacao.getId();
        String conta = transacao.getConta();
        String descricao = transacao.getDescricao();

        Boolean ativo = transacao.getAtivo();
        Float valorTransacao = transacao.getValorTransacao();

        Date dateTimeTransferencia = transacao.getDateTimeTransferencia();
        TipoTransacao tipoTransacao = transacao.getTipoTransacao();

        if(verifyID)
            verifyNullObject("ID", sb, id);

        verifyNullObject("Conta", sb, conta);
        verifyNullObject("descricao", sb, descricao);
        verifyNullObject("Ativo", sb, ativo);
        verifyNullObject("DateTimeTransferencia", sb, dateTimeTransferencia);
        verifyNullObject("TipoTransacao", sb, tipoTransacao);
        verifyNullObject("ValorTransacao", sb, valorTransacao);

        if(verifyID && id.intValue() < 0){
            sb.add("Id inválido");
        }

        if(conta != null && conta.isEmpty()){
            sb.add("conta não pode ser vazio");
        }

        if(descricao != null && descricao.isEmpty()) {
            sb.add("descricao não pode ser vazio");
        }

        if(valorTransacao.floatValue() < 0) {
            sb.add("ValorTransacao tem que ser maior que zero");
        }

        if(sb.size() > 0){
            String initmsg = "ERROS";
            sb.add(0,initmsg);
            throw new TransacaoInvalidException(sb.stream().collect(Collectors.joining(", ","[","]")));
        }
    }

    private void verifyNullObject(String field, List<String> sb,  Object object) {
        if(object == null){
            sb.add(field + " não pode ser null");
        }
    }
}

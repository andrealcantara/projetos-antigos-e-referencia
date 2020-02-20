package com.dev.six.rest.camel.study.cosumer.rest.study.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.jackson.JsonComponent;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@JsonComponent
@JsonIgnoreProperties
public class ResponseTransacao implements Serializable {


    private Transacao transacao;
    private List<String> warns;
    private Error error;


    public ResponseTransacao() {
    }

    public ResponseTransacao(Transacao transacao, List<String> warns, Error error) {
        this.transacao = transacao;
        this.warns = warns;
        this.error = error;
    }

    public Transacao getTransacao() {
        return transacao;
    }

    public void setTransacao(Transacao transacao) {
        this.transacao = transacao;
    }

    public List<String> getWarns() {
        return warns;
    }

    public void setWarns(List<String> warns) {
        this.warns = warns;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResponseTransacao)) return false;
        ResponseTransacao that = (ResponseTransacao) o;
        return transacao.equals(that.transacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transacao);
    }
}

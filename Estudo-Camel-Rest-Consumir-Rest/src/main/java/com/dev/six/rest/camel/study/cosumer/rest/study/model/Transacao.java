package com.dev.six.rest.camel.study.cosumer.rest.study.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;
@JsonComponent
@JsonIgnoreProperties
public class Transacao implements Serializable {

    private Long id;
    private String descricao;
    private Boolean ativo;
    private TipoTransacao tipoTransacao;
    private Float valorTransacao;
    private Date dateTimeTransferencia;
    private String conta;

    public Transacao() {
    }

    public Transacao(Long id, String descricao, Boolean ativo, TipoTransacao tipoTransacao,
                     Float valorTransacao, Date dateTimeTransferencia, String conta) {
        this.id = id;
        this.descricao = descricao;
        this.ativo = ativo;
        this.tipoTransacao = tipoTransacao;
        this.valorTransacao = valorTransacao;
        this.dateTimeTransferencia = dateTimeTransferencia;
        this.conta = conta;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Transacao.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("descricao='" + descricao + "'")
                .add("ativo=" + ativo)
                .add("tipoTransacao=" + tipoTransacao)
                .add("valorTransacao=" + valorTransacao)
                .add("dateTimeTransferencia=" + dateTimeTransferencia)
                .add("conta='" + conta + "'")
                .toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public Float getValorTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(Float valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

    public Date getDateTimeTransferencia() {
        return dateTimeTransferencia;
    }

    public void setDateTimeTransferencia(Date dateTimeTransferencia) {
        this.dateTimeTransferencia = dateTimeTransferencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transacao)) return false;
        Transacao transacao = (Transacao) o;
        return getId().equals(transacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

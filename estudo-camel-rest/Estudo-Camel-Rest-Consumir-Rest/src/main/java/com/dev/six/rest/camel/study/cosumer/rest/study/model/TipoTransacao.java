package com.dev.six.rest.camel.study.cosumer.rest.study.model;

public enum TipoTransacao {
    CREDITO(0),
    DEBITO(1),
    ACERTO(2),
    DESCONHECIDO(100);

    private int key;

    TipoTransacao(int key) {
        this.key = key;
    }

    public static TipoTransacao getByValor(int key) {
        TipoTransacao findObject = TipoTransacao.DESCONHECIDO;
        for (TipoTransacao tt : values()) {
            if (tt.getKey() == key) {
                findObject = tt;
                break;
            }
        }
        return findObject;
    }
    public int getKey() {
        return key;
    }

}

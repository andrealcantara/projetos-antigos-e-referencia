package camel.consumer.api.banco.rest.demo.model;

import java.util.Arrays;

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
        return Arrays.asList(TipoTransacao.values())
                .stream().filter(x -> x.getKey() == key)
                .findFirst().orElseGet( () -> TipoTransacao.DESCONHECIDO);
    }

    public int getKey() {
        return key;
    }

}

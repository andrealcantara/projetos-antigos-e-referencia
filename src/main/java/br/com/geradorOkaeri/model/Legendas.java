package br.com.geradorOkaeri.model;

import java.util.Arrays;

import br.com.geradorOkaeri.Util.Mensagem;
import br.com.geradorOkaeri.Util.Mensagem.MensagemEnum;

public enum Legendas {

	AO_POST(1,"Anexao ao Post"),
	EMBUTIDA(2,"Embutida"),
	NO_ARQUIVO(3,"No arquivo"),
	SEM_LEGENDA(4,"Sem legenda");
	
	private int id;
	private String name;
	
	private Legendas(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public static Legendas valueOf(int id) {
		return Arrays.asList(Legendas.values()).stream().filter(i -> i.getId() == id).findFirst()
				.orElseThrow(() -> new IllegalArgumentException(
						Mensagem.get(MensagemEnum.Mensagem_Error_Enum_Param, id, Legendas.class.getName())));
	}
	
}

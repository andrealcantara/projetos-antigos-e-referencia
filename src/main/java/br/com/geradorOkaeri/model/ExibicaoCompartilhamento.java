package br.com.geradorOkaeri.model;

import java.util.Arrays;

import br.com.geradorOkaeri.Util.Mensagem;
import br.com.geradorOkaeri.Util.Mensagem.MensagemEnum;

public enum ExibicaoCompartilhamento {
	PADRAO(1,"Padrão"),
	CODE(2,"Code"),
	SPOILER(3,"Spoiler"),
	SPOILER_CODE(4,"Spoiler/Code");
	
	private String name;
	private int id;
	
	private ExibicaoCompartilhamento(int id, String name) {
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
	
	public static ExibicaoCompartilhamento valueOf(int id) {
		return Arrays.asList(ExibicaoCompartilhamento.values()).stream().filter(i -> i.getId() == id).findFirst()
				.orElseThrow(() -> new IllegalArgumentException(
						Mensagem.get(MensagemEnum.Mensagem_Error_Enum_Param, id, ExibicaoCompartilhamento.class.getName())));
	}
}

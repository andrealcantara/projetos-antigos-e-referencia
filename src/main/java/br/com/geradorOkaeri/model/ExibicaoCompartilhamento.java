package br.com.geradorOkaeri.model;

import java.util.Arrays;

import br.com.geradorOkaeri.util.message.LocalMessage;

/**
 * Enum que representa o tipo de exibicao do compartilhamento do {@link Post}.
 * 
 * @author andre
 *
 */
public enum ExibicaoCompartilhamento implements EnumModelInterface {
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
	
	/**
	 * Busca um tipo de {@link ExibicaoCompartilhamento} atraves da sua <code>id</code>
	 * @param id - int id do {@link ExibicaoCompartilhamento}
	 * @return {@link ExibicaoCompartilhamento}
	 */
	public static ExibicaoCompartilhamento valueOf(int id) {
		return Arrays.asList(ExibicaoCompartilhamento.values()).stream().filter(i -> i.getId() == id).findFirst()
				.orElseThrow(() -> new IllegalArgumentException(
						LocalMessage.getDefaultInstance().get("mensagemErrorEnumParam", id, ExibicaoCompartilhamento.class.getName())));
	}
}

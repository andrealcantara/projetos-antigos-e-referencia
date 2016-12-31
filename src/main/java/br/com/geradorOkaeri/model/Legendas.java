package br.com.geradorOkaeri.model;

import java.util.Arrays;

import br.com.geradorOkaeri.util.message.LocalMessage;

/**
 * Enum que representa o tipo de legendas do {@link Post}.
 * @author andre
 *
 */
public enum Legendas implements EnumModelInterface {

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
	
	/**
	 * Busca um tipo de {@link Legendas} atraves da sua <code>id</code>
	 * @param id - int id do {@link Legendas}
	 * @return {@link Legendas}
	 */
	public static Legendas valueOf(int id) {
		return Arrays.asList(Legendas.values()).stream().filter(i -> i.getId() == id).findFirst()
				.orElseThrow(() -> new IllegalArgumentException(
						LocalMessage.getDefaultInstance().get("mensagemErrorEnumParam", id, Legendas.class.getName())));
	}
	
}

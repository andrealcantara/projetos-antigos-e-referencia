package br.com.geradorOkaeri.model;

import java.util.Arrays;

import br.com.geradorOkaeri.util.message.LocalMessage;

/**
 * Enum que representa o tipo de compartilhamento do {@link Post}
 * @author andre
 *
 */
public enum CompartilhamentoTipo implements EnumModelInterface {
	ED2K(1,"Ed2k"),
	HTTP(2,"Http"),
	TORRENT(3,"Torrent"),
	MAGNET(4,"Magnet");
	
	private String name;
	private int id;
	
	private CompartilhamentoTipo(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see br.com.geradorOkaeri.model.EnumModelInterface#getId()
	 */
	public int getId() {
		return id;
	}
	
	/* (non-Javadoc)
	 * @see br.com.geradorOkaeri.model.EnumModelInterface#getName()
	 */
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	/**
	 * Busca um tipo de {@link CompartilhamentoTipo} atraves da sua <code>id</code>
	 * @param id - int id do {@link CompartilhamentoTipo}
	 * @return {@link CompartilhamentoTipo}
	 */
	public static CompartilhamentoTipo valueOf(int id) {
		return Arrays.asList(CompartilhamentoTipo.values()).stream().filter(i -> i.getId() == id).findFirst()
				.orElseThrow(() -> new IllegalArgumentException(
						LocalMessage.getDefaultInstance().get("mensagemErrorEnumParam", id, CompartilhamentoTipo.class.getName())));
	}
}

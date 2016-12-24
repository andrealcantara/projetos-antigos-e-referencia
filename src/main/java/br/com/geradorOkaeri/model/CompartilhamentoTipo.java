package br.com.geradorOkaeri.model;

import java.util.Arrays;

import br.com.geradorOkaeri.Util.Mensagem;
import br.com.geradorOkaeri.Util.Mensagem.MensagemEnum;

public enum CompartilhamentoTipo {
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
	
	public static CompartilhamentoTipo valueOf(int id) {
		return Arrays.asList(CompartilhamentoTipo.values()).stream().filter(i -> i.getId() == id).findFirst()
				.orElseThrow(() -> new IllegalArgumentException(
						Mensagem.get(MensagemEnum.Mensagem_Error_Enum_Param, id, CompartilhamentoTipo.class.getName())));
	}
}

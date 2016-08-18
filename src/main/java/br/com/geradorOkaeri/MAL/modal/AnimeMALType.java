package br.com.geradorOkaeri.MAL.modal;

import java.util.stream.Stream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import br.com.geradorOkaeri.MAL.converter.AnimeMALTypeConverterXML;

@XStreamAlias("type")
@XStreamConverter(AnimeMALTypeConverterXML.class)
public enum AnimeMALType {
	TV(1, "TV", "Tv"),
	OVA(2, "OVA", "Ova"),
	MOVIE(3, "Movie", "Filme"),
	SPECIAL(4, "Special", "Especial"),
	ONA(5, "ONA", "Ona"),
	MUSIC(6, "Music", "Musica");

	private Integer idType;
	private String name;
	private String name_ptBr;

	private AnimeMALType(Integer idType, String name, String name_ptBR) {
		this.idType = idType;
		this.name = name;
		this.name_ptBr = name_ptBR;
	}

	public Integer getIdType() {
		return idType;
	}

	public void setIdType(Integer idType) {
		this.idType = idType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNomePortugues() {
		return name_ptBr;
	}

	public void setNomePortugues(String name_ptBr) {
		this.name_ptBr = name_ptBr;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public static AnimeMALType valueOfName(String name) {
		return Stream.of(AnimeMALType.values()).filter(e -> e.getName().equals(name)).findFirst()
				.orElseThrow(() -> new RuntimeException("Name=" + name + ", nao informado."));
	}
}
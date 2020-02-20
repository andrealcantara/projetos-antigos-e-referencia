package br.com.geradorOkaeri.Animes.AniDB.modal;

import java.util.stream.Stream;

public enum AnimeAniDBType {
	MOVIE("Movie","Filme"),
	MUSIC("Music Video", "Music Video"),
	OTHER("Other", "Outro"),
	OVA("Ova", "OVA"),
	TV_SPECIAL("TV Special", "Especial de TV"),
	TV_SERIE("TV Serie", "Serie"),
	UNKNOWM("unknowm",null),
	WEB("web", "Web");
	
	private String nameOficial;
	private String nome;
	
	private AnimeAniDBType(String name, String nome){
		this.nameOficial = name;
		this.nome = nome;
	}

	public String getNameOficial() {
		return nameOficial;
	}

	public void setNameOficial(String nameOficial) {
		this.nameOficial = nameOficial;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public static AnimeAniDBType valueOfName(String name) {
		return Stream.of(AnimeAniDBType.values()).filter(f -> f.getNameOficial().equals(name)).
				findFirst().orElseThrow(() -> new RuntimeException("Name="+name+", nao encontrado."));
	}
}

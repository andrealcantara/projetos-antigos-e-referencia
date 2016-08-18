package br.com.geradorOkaeri.MAL.modal;

import java.util.stream.Stream;

public enum AnimeMALStatus {
	
	FINALIZADO(2,"Finished Airing", "Finalizado exibicao"), 
	EXIBINDO(1,"Currently Airing", "Em exibicao"),
	NAO_EXIBIDO_AINDA(3,"Not yet aired", "Nao exibido ainda");
	
	private Integer id;
	private String name;
	private String nome;
	
	private AnimeMALStatus(Integer id, String name, String nome){
		this.id = id;
		this.name = name;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public static AnimeMALStatus valueOfName(String name){
		return Stream.of(AnimeMALStatus.values()).filter(f -> f.getName().equals(name)).
				findFirst().orElseThrow(() -> new RuntimeException("Name=" + name + " nao econtrado."));
	}
}

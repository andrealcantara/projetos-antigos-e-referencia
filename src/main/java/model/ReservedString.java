package model;

import util.Configuracao;

public enum ReservedString {
	TOLKEN(Configuracao.defaultTags),
	IF("if"), 
	THAN("than"), 
	NAME("name"), 
	NEEDED("needed"), 
	TYPE("type"),
	SIZE("size"),
	VALUE("value");
	
	private String name;
	
	private ReservedString(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}

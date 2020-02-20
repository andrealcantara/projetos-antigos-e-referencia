package br.com.fastPunchSub.punchSys.util;

import java.util.function.Predicate;

import br.com.fastPunchSub.messageLoader.MessageLoader;
import br.com.fastPunchSub.messageLoader.MessageLoader.MessageType;

public enum AnimeType implements EnumModelSearch{
	EPISODIO(0,"Episódio"),
	OVA(1,"Ova"),
	FILME(2,"Filme");
	
	private int id;
	private String name;
	
	private AnimeType(int id, String name){
		this.id = id;
		this.name = name;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public static AnimeType valueOf(int id) {
		Predicate<AnimeType> predic = x-> x.getId() == id;
		return SearchEnum.findInEnum(MessageLoader.getBundle(MessageType.ERROR).get("NO_ITEM_ENUM",
				AnimeType.class.getName(), "id", id), predic, AnimeType.values());
	}
	
	public static AnimeType valueByLowercase(String name) {
		if(name == null){
			throw new RuntimeException(MessageLoader.getBundle(MessageType.ERROR).get("NULL_PARAM_ENUM",
					AnimeType.class.getName(), "name"));
		}
		Predicate<AnimeType> pred = x -> x.getName().toLowerCase().equals(name.toLowerCase());
		return SearchEnum.findInEnum(MessageLoader.getBundle(MessageType.ERROR).get("NO_ITEM_ENUM",
				AnimeType.class.getName(), "name", name), pred, AnimeType.values());
	}
	

}

package br.com.fastPunchSub.punchSys.util;

import java.util.function.Predicate;

import br.com.fastPunchSub.messageLoader.MessageLoader;
import br.com.fastPunchSub.messageLoader.MessageLoader.MessageType;

public enum StatusAnimes {
	COMPLETO(0, "Completo"), INCOMPLETO(1, "Incompleto"), LANCANDO(2, "Lançando"), PAUSADO(3, "Pausado");

	private int id;
	private String name;

	private StatusAnimes(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public static StatusAnimes valueOf(int id) {
		Predicate<StatusAnimes> pred = x -> x.getId() == id;
		return SearchEnum.findInEnum(MessageLoader.getBundle(MessageType.ERROR).get("NO_ITEM_ENUM",
				StatusAnimes.class.getName(), "id", id), pred, StatusAnimes.values());
	}
	
	public static StatusAnimes valueByNameLowercase(String name) {
		if(name == null){
			throw new RuntimeException(MessageLoader.getBundle(MessageType.ERROR).get("NULL_PARAM_ENUM",
					StatusAnimes.class.getName(), "name"));
		}
		Predicate<StatusAnimes> pred = x -> x.getName().toLowerCase().equals(name);
		return SearchEnum.findInEnum(MessageLoader.getBundle(MessageType.ERROR).get("NO_ITEM_ENUM",
				StatusAnimes.class.getName(), "name", name), pred, StatusAnimes.values());
	}
}

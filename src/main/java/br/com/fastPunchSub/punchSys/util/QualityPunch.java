package br.com.fastPunchSub.punchSys.util;

import java.util.function.Predicate;

import br.com.fastPunchSub.messageLoader.MessageLoader;
import br.com.fastPunchSub.messageLoader.MessageLoader.MessageType;

public enum QualityPunch {

	MP4(0, "MP4"), HD(1, "HD"), FULL_HD(2, "FullHD"), SD(3, "SD");

	private int id;
	private String name;

	private QualityPunch(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static QualityPunch valueOf(int id) {
		Predicate<QualityPunch> pred = x -> x.getId() == id;
		return SearchEnum.findInEnum(MessageLoader.getBundle(MessageType.ERROR).get("NO_ITEM_ENUM",
				QualityPunch.class.getName(), "id", id), pred, QualityPunch.values());
	}
	
	public static QualityPunch valueByLowercase(String name) {
		if(name == null) {
			throw new RuntimeException(MessageLoader.getBundle(MessageType.ERROR).get("NULL_PARAM_ENUM",
					QualityPunch.class.getName(), "name"));
		}
		Predicate<QualityPunch> pred = x -> x.getName().toLowerCase().equals(name.toLowerCase());
		return SearchEnum.findInEnum(MessageLoader.getBundle(MessageType.ERROR).get("NO_ITEM_ENUM",
				QualityPunch.class.getName(), "name", name), pred, QualityPunch.values());
	}

}

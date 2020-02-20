package br.com.fastPunchSub.redecanais.models;

import java.util.stream.Stream;

import br.com.fastPunchSub.messageLoader.MessageLoader;
import br.com.fastPunchSub.messageLoader.MessageLoader.MessageType;

public enum TypeEpisode {
	DUBLADO("Dublado", "Dublado"), LEGENDADO("Legendado", "Legendado");

	private String labelPage;
	private String labelName;

	private TypeEpisode(String labelP, String labelN) {
		this.labelName = labelN;
		this.labelPage = labelP;
	}

	public String getLabelPage() {
		return labelPage;
	}

	public String getLabelName() {
		return labelName;
	}

	public String getLabelShort() {
		return labelName.substring(0, 3);
	}

	public static TypeEpisode valueByLabelPage(String labelPage) {
		return Stream.of(TypeEpisode.values()).filter(x -> x.getLabelPage().equals(labelPage)).findFirst()
				.orElseThrow(() -> new RuntimeException(MessageLoader.getBundle(MessageType.ERROR).get("NO_ITEM_ENUM",
						TypeEpisode.class.getName(), "LabelPage", labelPage)));
	}

}

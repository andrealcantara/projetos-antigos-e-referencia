package br.com.geradorOkaeri.util.message;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class LocalMessage implements Serializable {
	private static final long serialVersionUID = -1549157101237590081L;

	private static Map<MessageType, LocalMessage> mapa = new HashMap<>();
	
	private ResourceBundle rb;
	
	public static LocalMessage getDefaultInstance() {
		return getBundle(MessageType.SYSTEM);
	}

	private LocalMessage() {}
	
	private LocalMessage(ResourceBundle bundle) {
		rb = bundle;
	}

	public static LocalMessage getBundle(MessageType type) {
		if(!mapa.containsKey(type)){
			mapa.put(type, new LocalMessage(LocalMessageHolder.staticBundles.get(type)));
		}
		return mapa.get(type);
	}

	public String get(String key) {
		return rb.getString(key);
	}

	public String get(String key, Object... params) {
		return MessageFormat.format(rb.getString(key), params);
	}

	private static class LocalMessageHolder {
		private static Map<MessageType, ResourceBundle> staticBundles = getBundle();

		private static Map<MessageType, ResourceBundle> getBundle() {
			Map<MessageType, ResourceBundle> bundles = new HashMap<>();
			bundles.put(MessageType.SYSTEM, ResourceBundle.getBundle("properties.sys-messages"));
			bundles.put(MessageType.LABELS, ResourceBundle.getBundle("properties.labels"));
			bundles.put(MessageType.TOOLTIPS, ResourceBundle.getBundle("properties.tooltips"));
			bundles.put(MessageType.MENSAGEM, ResourceBundle.getBundle("properties.mensagens"));
			bundles.put(MessageType.VALIDATION, ResourceBundle.getBundle("properties.validation"));
			return bundles;
		}
	}

	public enum MessageType {
		SYSTEM, LABELS, TOOLTIPS, MENSAGEM, VALIDATION;
	}

}

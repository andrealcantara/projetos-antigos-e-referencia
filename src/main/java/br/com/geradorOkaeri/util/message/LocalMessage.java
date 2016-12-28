package br.com.geradorOkaeri.util.message;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LocalMessage implements Serializable {
	private static final long serialVersionUID = -1549157101237590081L;
	private Map<MessageType, ResourceBundle> mapBundle;
	private ResourceBundle rb ;
	
	public static LocalMessage getDefaultInstance(){
		return LocalMessageHolder.INSTANCE;
	}
	
	private LocalMessage(){
		mapBundle = new HashMap<>();
		mapBundle.put(MessageType.SYSTEM, ResourceBundle.getBundle("properties.sys-messages"));
		mapBundle.put(MessageType.LABELS, ResourceBundle.getBundle("properties.labels"));
		mapBundle.put(MessageType.TOOLTIPS, ResourceBundle.getBundle("properties.tooltips"));
		mapBundle.put(MessageType.MENSAGEM, ResourceBundle.getBundle("properties.mensagens"));
		mapBundle.put(MessageType.VALIDATION, ResourceBundle.getBundle("properties.validation"));
		
		changeResourceBundle(MessageType.SYSTEM);
	}
	
	public LocalMessage getDefault() {
		return getBundle(MessageType.SYSTEM);
	}
	
	public LocalMessage getBundle(MessageType type){
		this.changeResourceBundle(type);
		return this;
	}
	
	private void changeResourceBundle(MessageType type){
		rb = mapBundle.get(type);
	}
	
	public String get(String msg) {
		return rb.getString(msg);
	}

	public String get(String msg, Object... params) {
		return MessageFormat.format(rb.getString(msg), params);
	}
	
	private static class LocalMessageHolder {
		private static LocalMessage INSTANCE = new LocalMessage();
	}
	
	public enum MessageType {
		SYSTEM,
		LABELS,
		TOOLTIPS,
		MENSAGEM,
		VALIDATION;
	}
	
}

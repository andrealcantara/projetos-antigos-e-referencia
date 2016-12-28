package br.com.geradorOkaeri.beans;

import java.io.Serializable;

import javax.enterprise.inject.Model;

import br.com.geradorOkaeri.util.message.LocalMessage;
import br.com.geradorOkaeri.util.message.LocalMessage.MessageType;

@Model
public class MessageBean implements Serializable{
	private static final long serialVersionUID = 8086953403127279252L;
	
	public String bundleSystem(String key, Object... param) {
		return LocalMessage.getDefaultInstance().getDefault().get(key, param);
	}
	
	public String bundleTooltips(String key, Object... param) {
		return LocalMessage.getDefaultInstance().getBundle(MessageType.TOOLTIPS).get(key, param);
	}
	
	public String bundleLabels(String key, Object... param) {
		return LocalMessage.getDefaultInstance().getBundle(MessageType.LABELS).get(key, param);
	}
	
	public String bundleValidation(String key, Object... param) {
		return LocalMessage.getDefaultInstance().getBundle(MessageType.VALIDATION).get(key, param);
	}
	
	
}

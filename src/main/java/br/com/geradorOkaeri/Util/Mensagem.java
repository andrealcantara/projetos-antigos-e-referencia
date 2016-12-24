package br.com.geradorOkaeri.Util;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Mensagem implements Serializable {
	private static final long serialVersionUID = -1549157101237590081L;
	private static ResourceBundle rb = ResourceBundle.getBundle("properties.sys-messages");
	
	public static String get(MensagemEnum msg){
		return rb.getString(msg.getMensagemKey());
	}
	
	public static String get(MensagemEnum msg, Object...params){
		return MessageFormat.format(rb.getString(msg.getMensagemKey()), params);
	}
	
	public enum MensagemEnum{
		Mensagem_Error_Enum_Param("mensagemErrorEnumParam");
		
		private String resourceMensagem;
		
		private MensagemEnum(String resourceMensagem){
			this.resourceMensagem = resourceMensagem;
		}
		
		private String getMensagemKey(){
			return resourceMensagem;
		}
	}
}

package model;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class Mensagem {
	private static ResourceBundle rb = MensagemHolder.HOLDER;
	
	public static String get(MensagemEnum msg){
		return rb.getString(msg.getMensagemKey());
	}
	
	public static String get(MensagemEnum msg, Object...params){
		return MessageFormat.format(rb.getString(msg.getMensagemKey()), params);
	}
	
	private static class MensagemHolder{
		private static final ResourceBundle HOLDER = ResourceBundle.getBundle("uhuu");
	}
	
	public enum MensagemEnum{
		MENSAGEM_UM("mensagem_1"),
		MENSAGEM_DOIS("mensagem_1");
		private String resourceMensagem;
		private MensagemEnum(String resourceMensagem){
			this.resourceMensagem = resourceMensagem;
		}
		private String getMensagemKey(){
			return resourceMensagem;
		}
	}
}

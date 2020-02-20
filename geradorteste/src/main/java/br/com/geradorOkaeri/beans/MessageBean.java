package br.com.geradorOkaeri.beans;

import java.io.Serializable;

import javax.enterprise.inject.Model;

import br.com.geradorOkaeri.util.message.LocalMessage;
import br.com.geradorOkaeri.util.message.LocalMessage.MessageType;

/**
 * Classe que representa o {@link LocalMessage} para as telas de JSF.
 * 
 * @author andre
 *
 */
@Model
public class MessageBean implements Serializable {
	private static final long serialVersionUID = 8086953403127279252L;

	/**
	 * Metodo que retorna uma mensagem {@link LocalMessage} do tipo
	 * {@link MessageType#SYSTEM}
	 * 
	 * @param key
	 *            - {@link String} chave da mensagem.
	 * @param param
	 *            - array {@link Object} parametros da mensagem.
	 * @return {@link String} com a mensagem formatada com os parametros.
	 * @see {@link MessageBean#bundleLabels(String, Object...)},<br />
	 *      {@link MessageBean#bundleTooltips(String, Object...)},<br />
	 *      {@link MessageBean#bundleValidation(String, Object...)}
	 */
	public String bundleSystem(String key, Object... param) {
		return LocalMessage.getDefaultInstance().get(key, param);
	}

	/**
	 * Metodo que retorna uma mensagem {@link LocalMessage} do tipo
	 * {@link MessageType#TOOLTIPS}
	 * 
	 * @param key
	 *            - {@link String} chave da mensagem.
	 * @param param
	 *            - array {@link Object} parametros da mensagem.
	 * @return {@link String} com a mensagem formatada com os parametros.
	 * @see {@link MessageBean#bundleLabels(String, Object...)},<br />
	 *      {@link MessageBean#bundleSystem(String, Object...)},<br />
	 *      {@link MessageBean#bundleValidation(String, Object...)}
	 */
	public String bundleTooltips(String key, Object... param) {
		return LocalMessage.getBundle(MessageType.TOOLTIPS).get(key, param);
	}

	/**
	 * Metodo que retorna uma mensagem {@link LocalMessage} do tipo
	 * {@link MessageType#LABELS}
	 * 
	 * @param key
	 *            - {@link String} chave da mensagem.
	 * @param param
	 *            - array {@link Object} parametros da mensagem.
	 * @return {@link String} com a mensagem formatada com os parametros.
	 * @see {@link MessageBean#bundleSystem(String, Object...)},<br />
	 *      {@link MessageBean#bundleTooltips(String, Object...)},<br />
	 *      {@link MessageBean#bundleValidation(String, Object...)}
	 */
	public String bundleLabels(String key, Object... param) {
		return LocalMessage.getBundle(MessageType.LABELS).get(key, param);
	}

	/**
	 * Metodo que retorna uma mensagem {@link LocalMessage} do tipo
	 * {@link MessageType#VALIDATION}
	 * 
	 * @param key
	 *            - {@link String} chave da mensagem.
	 * @param param
	 *            - array {@link Object} parametros da mensagem.
	 * @return {@link String} com a mensagem formatada com os parametros.
	 * @see {@link MessageBean#bundleLabels(String, Object...)},<br />
	 *      {@link MessageBean#bundleTooltips(String, Object...)},<br />
	 *      {@link MessageBean#bundleSystem(String, Object...)}
	 */
	public String bundleValidation(String key, Object... param) {
		return LocalMessage.getBundle(MessageType.VALIDATION).get(key, param);
	}

}

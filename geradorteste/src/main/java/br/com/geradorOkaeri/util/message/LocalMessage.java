package br.com.geradorOkaeri.util.message;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Classe que representa o gerenciador de mensagens do gerador.
 * 
 * @author andre
 *
 */
public class LocalMessage implements Serializable {
	private static final long serialVersionUID = -1549157101237590081L;

	private static Map<MessageType, LocalMessage> mapa = new HashMap<>();

	private ResourceBundle rb;

	/**
	 * Metodo usado para buscar uma instancia de {@link LocalMessage} do tipo
	 * {@link MessageType#SYSTEM}
	 * 
	 * @return {@link LocalMessage} com as mensgaens referente do tipo
	 *         {@link MessageType#SYSTEM}
	 * @see {@link LocalMessage#getBundle(MessageType)}
	 */
	public static LocalMessage getDefaultInstance() {
		return getBundle(MessageType.SYSTEM);
	}

	private LocalMessage() {
	}

	private LocalMessage(ResourceBundle bundle) {
		rb = bundle;
	}

	/**
	 * Metodo usado para buscar e armazenar um {@link LocalMessage}.
	 * 
	 * @param type
	 *            - {@link MessageType} tipo de {@link LocalMessage} com as
	 *            mensagem do tipo indicado.
	 * @return {@link LocalMessage} com as mensagens referente ao
	 *         {@link MessageType} indicado.
	 */
	public static LocalMessage getBundle(MessageType type) {
		if (!mapa.containsKey(type)) {
			mapa.put(type, new LocalMessage(LocalMessageHolder.staticBundles.get(type)));
		}
		return mapa.get(type);
	}

	/**
	 * Metodo usado para recuperar as mensagens referente as <code>key</code>
	 * 
	 * @param key
	 *            - {@link String} chaves referente as mensagens.
	 * @return {@link String} mensagem referente as chave.
	 * @see {@link LocalMessage#get(String, Object...)}
	 */
	public String get(String key) {
		return rb.getString(key);
	}

	/**
	 * Metodo usado para adicionar parametros(<code>params</code>) as mensagens
	 * encontradas na chave(<code>key</code>).
	 * 
	 * @param key
	 *            - {@link String} chaves referente as mensagens.
	 * @param params
	 *            - arrays {@link Object} com os parametros das mensagens.
	 * @return {@link String} mensagem referente as chave.
	 * @see {@link LocalMessage#get(String)}
	 */
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

	/**
	 * Enuns que representam os tipos de {@link LocalMessage}.
	 * @author andre
	 *
	 */
	public enum MessageType {
		SYSTEM, LABELS, TOOLTIPS, MENSAGEM, VALIDATION;
	}

}

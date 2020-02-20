package br.com.fastPunchSub.messageLoader;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MessageLoader implements Serializable{
	private static final long serialVersionUID = 5401749858707737347L;

	private static Map<MessageType, MessageLoader> mapa = new HashMap<>();

	private ResourceBundle rb;

	/**
	 * Metodo usado para buscar uma instancia de {@link LocalMessage} do tipo
	 * {@link MessageType#SYSTEM}
	 * 
	 * @return {@link MessageLoader} com as mensgaens referente do tipo
	 *         {@link MessageType#SYSTEM}
	 * @see {@link LocalMessage#getBundle(MessageType)}
	 */
	public static MessageLoader getDefaultInstance() {
		return getBundle(MessageType.CONFIG);
	}

	private MessageLoader() {
	}

	private MessageLoader(ResourceBundle bundle) {
		rb = bundle;
	}

	/**
	 * Metodo usado para buscar e armazenar um {@link LocalMessage}.
	 * 
	 * @param type
	 *            - {@link MessageType} tipo de {@link LocalMessage} com as
	 *            mensagem do tipo indicado.
	 * @return {@link MessageLoader} com as mensagens referente ao
	 *         {@link MessageType} indicado.
	 */
	public static MessageLoader getBundle(MessageType type) {
		if (!mapa.containsKey(type)) {
			mapa.put(type, new MessageLoader(LocalMessageHolder.staticBundles.get(type)));
		}
		return mapa.get(type);
	}

	/**
	 * Metodo usado para recuperar as mensagens referente as <code>key</code>
	 * 
	 * @param key
	 *            - {@link String} chaves referente as mensagens.
	 * @return {@link String} mensagem referente as chave.
	 * @see {@link MessageLoader#get(String, Object...)}
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
	 * @see {@link MessageLoader#get(String)}
	 */
	public String get(String key, Object... params) {
		return MessageFormat.format(rb.getString(key), params);
	}

	private static class LocalMessageHolder {
		private static Map<MessageType, ResourceBundle> staticBundles = getBundle();

		private static Map<MessageType, ResourceBundle> getBundle() {
			Map<MessageType, ResourceBundle> bundles = new HashMap<>();
			bundles.put(MessageType.CONFIG, ResourceBundle.getBundle("properties.config"));
			bundles.put(MessageType.ERROR, ResourceBundle.getBundle("properties.error"));
			bundles.put(MessageType.PUNCH_CONFIG, ResourceBundle.getBundle("properties.punchconfig"));
			return bundles;
		}
	}

	/**
	 * Enuns que representam os tipos de {@link LocalMessage}.
	 * @author andre
	 *
	 */
	public enum MessageType {
		PUNCH_CONFIG, ERROR, CONFIG;
	}

	
}

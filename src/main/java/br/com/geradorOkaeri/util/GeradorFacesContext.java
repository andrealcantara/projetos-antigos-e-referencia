package br.com.geradorOkaeri.util;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public class GeradorFacesContext implements Serializable {
	private static final long serialVersionUID = 596779354954774961L;

	@Produces
	@RequestScoped
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	public static FacesContext getCurrentFacesContext() {
		return Produtor.staticClassCDI(FacesContext.class);
	}

	/**
	 * Redireciona para uma tela pelo proprio faces context.
	 * 
	 * @param url
	 *            da navegacao
	 * @throws IOException
	 *             Erro ao tentar navegar.
	 */
	public static void redirect(String url) throws IOException {
		GeradorFacesContext.getCurrentFacesContext().getExternalContext().redirect(url);
	}

	/**
	 * Metodo de adicionar mensagem ao faces context.
	 * 
	 * @param id
	 *            - id do UiComponent da tela.
	 * @param message
	 *            - {@link FacesMessage} mensagem do faces.
	 */
	public static void addMessage(String id, FacesMessage message) {
		GeradorFacesContext.addMessage(id, message, false);
	}

	/**
	 * Metodo de adicionar mensagem ao faces context.
	 * 
	 * @param id
	 *            - id do UiComponent da tela.
	 * @param message
	 *            - {@link FacesMessage} mensagem do faces.
	 * @param keepMessages
	 *            - indica se mantem as mensagem mesmo se trocar de bean ou se o
	 *            escopo destruir o bean e criar um novo.
	 */
	public static void addMessage(String id, FacesMessage message, boolean keepMessages) {
		GeradorFacesContext.getCurrentFacesContext().getExternalContext().getFlash().setKeepMessages(keepMessages);
		GeradorFacesContext.getCurrentFacesContext().addMessage(id, message);
	}

	/**
	 * Adiciona uma {@link FacesMessage} com um scopo de Information.
	 * 
	 * @param summary
	 *            - resumo da informação.
	 * @param details
	 *            - detalhes da informação.
	 * @return {@link FacesMessage}
	 */
	public static FacesMessage infoMessage(String summary, String details) {
		return new FacesMessage(FacesMessage.SEVERITY_INFO, summary, details);
	}

	/**
	 * Adiciona uma {@link FacesMessage} com um scopo de Error.
	 * 
	 * @param summary
	 *            - resumo do erro.
	 * @param details
	 *            - detalhes do erro.
	 * @return {@link FacesMessage}
	 */
	public static FacesMessage errorMessage(String summary, String details) {
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, details);
	}

	/**
	 * Adiciona uma {@link FacesMessage} com um scopo de Warning.
	 * 
	 * @param summary
	 *            - resumo da advertência.
	 * @param details
	 *            - detalhes da advertência.
	 * @return {@link FacesMessage}
	 */
	public static FacesMessage warningMessage(String summary, String details) {
		return new FacesMessage(FacesMessage.SEVERITY_WARN, summary, details);
	}

}

package br.com.geradorOkaeri.validation;

import com.google.common.base.Strings;

import br.com.geradorOkaeri.exception.GeradorValidationException;
import br.com.geradorOkaeri.model.Post;
import br.com.geradorOkaeri.util.message.LocalMessage;
import br.com.geradorOkaeri.util.message.LocalMessage.MessageType;

public class ValidationPost {

	private static String ERROR_CAMPO_OBRIGATORIO = "error_campo_obrigatorio";

	private LocalMessage labels;
	private LocalMessage validation;

	private ValidationPost() {
		labels = LocalMessage.getBundle(MessageType.LABELS);
		validation = LocalMessage.getBundle(MessageType.VALIDATION);
	}

	public void valid(Post post) throws GeradorValidationException {
		if (Strings.isNullOrEmpty(post.getTitulo())) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("titulo")));
		}

		if (Strings.isNullOrEmpty(post.getDuracao())) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("duracao")));
		}

		if (Strings.isNullOrEmpty(post.getFansuber())) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("fansuber")));
		}

		if (Strings.isNullOrEmpty(post.getImagemCapa())) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("imagemCapa")));
		}

		if (Strings.isNullOrEmpty(post.getSinopsePTBR())) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("sinopsePTBR")));
		}

		if (this.verifyArraysStringsIsNullOrEmpty(post.getScreenshot())) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("screenshot")));
		}

		if (post.getQualidade() == null) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("qualidade")));
		}

		if (Strings.isNullOrEmpty(post.getIdiomaAudio())) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("idiomaAudio")));
		}
		
		if (Strings.isNullOrEmpty(post.getTamanhoArquivo())) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("tamanhoArquivo")));
		}
		
		if (post.getResolucaoVideo() == null ) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("idiomaAudio")));
		}
		
		if (Strings.isNullOrEmpty(post.getIdiomaAudio())) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("idiomaAudio")));
		}
		
		if (Strings.isNullOrEmpty(post.getIdiomaAudio())) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("idiomaAudio")));
		}
		
		if (Strings.isNullOrEmpty(post.getIdiomaAudio())) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("idiomaAudio")));
		}
		
		if (Strings.isNullOrEmpty(post.getIdiomaAudio())) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("idiomaAudio")));
		}
	}

	public boolean verifyArraysStringsIsNullOrEmpty(String[] array) {
		boolean retorno = true;
		if (array.length < 2) {
			retorno = false;
		} else {
			int count = 0;
			for (String str : array) {
				++count;
				if (Strings.isNullOrEmpty(str) && count < 3) {
					retorno = false;
					break;
				}
			}
		}
		return retorno;
	}

	public static ValidationPost getInstance() {
		return ValidationPostHolder.INSTANCE;
	}

	private static class ValidationPostHolder {
		private static ValidationPost INSTANCE = new ValidationPost();
	}

}

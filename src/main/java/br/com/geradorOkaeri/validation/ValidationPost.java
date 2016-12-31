package br.com.geradorOkaeri.validation;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.annotation.Inherited;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import com.google.common.base.Strings;

import br.com.geradorOkaeri.exception.GeradorValidationException;
import br.com.geradorOkaeri.model.CompartilhamentoTipo;
import br.com.geradorOkaeri.model.Post;
import br.com.geradorOkaeri.model.Resolucao;
import br.com.geradorOkaeri.util.message.LocalMessage;
import br.com.geradorOkaeri.util.message.LocalMessage.MessageType;

public class ValidationPost {

	private static String ERROR_CAMPO_OBRIGATORIO = "error_campo_obrigatorio";
	private static String ERROR_SS_RES = "error_screenshot_resolucao";

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

		if (verifyResolucao(post.getResolucaoVideo())) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("resolucao")));
		}

		if (Strings.isNullOrEmpty(post.getFormatoTela())) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("formatoTela")));
		}

		if (post.getLegendas() == null) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("legendas")));
		}

		if (post.getCompartilhamentoTipo() == null || post.getCompartilhamentoTipo().length < 1) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("compartilhamento")));
		}

		if (verifyCompartilhamentoTipoTorrent(post.getCompartilhamentoTipo())
				&& Strings.isNullOrEmpty(post.getCompartilhamento())) {
			throw new GeradorValidationException(validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, "Urls"));
		}

		this.verifyResolucaoScreenShots(post.getResolucaoVideo(), post.getScreenshot());
	}

	/**
	 * Verifica se existe {@link CompartilhamentoTipo}, se for 1 item e for do
	 * tipo {@link CompartilhamentoTipo.Torrent}
	 * 
	 * @param tipo
	 *            - {@inheritDoc}
	 * @return true se existir e se tiver o tipo TORRENT + qualque outro, false
	 *         se nao existir ou se o unico existente for TORRENT.
	 */
	public boolean verifyCompartilhamentoTipoTorrent(CompartilhamentoTipo[] tipo) {
		boolean retorno = true;
		if (tipo == null || tipo.length < 1) {
			retorno = false;
		} else {
			List<CompartilhamentoTipo> tipos = Arrays.asList(tipo);
			if (tipos.size() < 1 || tipos.size() == 1 && tipos.get(0).equals(CompartilhamentoTipo.TORRENT)) {
				retorno = false;
			}
		}
		return retorno;
	}

	public void verifyResolucaoScreenShots(Resolucao resolucao, String[] screenshots)
			throws GeradorValidationException {
		BufferedImage bi = null;
		try {
			for (int i = 0; i < screenshots.length; i++) {
				String str = screenshots[i];
				bi = ImageIO.read(new URL(str));
				if (bi.getHeight() != resolucao.getHeight().intValue()
						|| bi.getWidth() != resolucao.getWidth().intValue()) {
					throw new GeradorValidationException(
							validation.get(ValidationPost.ERROR_SS_RES, i, resolucao.getWidth().intValue(),
									resolucao.getHeight().intValue(), bi.getWidth(), bi.getHeight()));
				}
			}
		} catch (IOException e) {
			throw new GeradorValidationException(e);
		}
	}

	private boolean verifyArraysStringsIsNullOrEmpty(String[] array) {
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

	private boolean verifyResolucao(Resolucao resolucao) {
		boolean retorno = true;
		if (resolucao == null || resolucao.getWidth() == null || resolucao.getHeight() == null
				|| resolucao.getWidth().intValue() < 1 || resolucao.getHeight().intValue() < 1) {
			retorno = false;
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

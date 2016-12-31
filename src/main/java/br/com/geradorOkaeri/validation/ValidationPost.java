package br.com.geradorOkaeri.validation;

import java.awt.image.BufferedImage;
import java.io.IOException;
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
		if (this.verificaString(post.getTitulo())) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("titulo")));
		}

		if (this.verificaString(post.getDuracao())) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("duracao")));
		}

		if (this.verificaString(post.getFansuber())) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("fansuber")));
		}

		if (this.verificaString(post.getImagemCapa())) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("imagemCapa")));
		}

		if (this.verificaString(post.getSinopsePTBR())) {
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

		if (this.verificaString(post.getIdiomaAudio())) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("idiomaAudio")));
		}

		if (this.verificaString(post.getTamanhoArquivo())) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("tamanhoArquivo")));
		}

		if (verifyResolucao(post.getResolucaoVideo())) {
			throw new GeradorValidationException(
					validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, labels.get("resolucao")));
		}

		if (this.verificaString(post.getFormatoTela())) {
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
				&& this.verificaString(post.getCompartilhamento())) {
			throw new GeradorValidationException(validation.get(ValidationPost.ERROR_CAMPO_OBRIGATORIO, "Urls"));
		}

		this.verifyResolucaoScreenShots(post.getResolucaoVideo(), post.getScreenshot());
	}

	private boolean verificaString(String str) {
		return Strings.isNullOrEmpty(str) || str.trim().isEmpty();
	}

	/**
	 * Verifica se existe {@link CompartilhamentoTipo}, se for 1 item e for do
	 * tipo {@link CompartilhamentoTipo.Torrent}
	 * 
	 * @param tipo
	 *            - arrays {@link CompartilhamentoTipo}
	 * @return <b><code>true</code></b> se existir e se tiver o tipo TORRENT +
	 *         qualque outro,<br />
	 *         <b><code>false</code></b> se nao existir ou se o unico existente
	 *         for TORRENT.
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

	/**
	 * Metodo que verifica se a {@link Resolucao} confere com o informado nas
	 * <code>screenshots</code>
	 * 
	 * @param resolucao
	 *            - {@link Resolucao}
	 * @param screenshots
	 *            - Arrays {@link String} com url das imagens
	 * @throws GeradorValidationException
	 *             Erro ao encontrar uma imagem com resolucao que diverge da
	 *             {@link Resolucao} informada.
	 */
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

	/**
	 * Metodo que verifica o arrays de {@link String} é null ou vazia e se é
	 * maior que 2.
	 * 
	 * @param array
	 *            - arrays de {@link String}.
	 * @return <b><code>TRUE</code></b> se todas os arrays possuem valor
	 *         diferente de vazio ou null<br />
	 *         <b><code>FALSE</code></b> se alguma {@link String} é null ou
	 *         vazio.
	 */
	private boolean verifyArraysStringsIsNullOrEmpty(String[] array) {
		boolean retorno = true;
		if (array.length < 2) {
			retorno = false;
		} else {
			int count = 0;
			for (String str : array) {
				++count;
				if (this.verificaString(str) && count < 3) {
					retorno = false;
					break;
				}
			}
		}
		return retorno;
	}

	/**
	 * Metodo que verifica se existe valores em {@link Resolucao}.
	 * 
	 * @param resolucao
	 *            - {@link Resolucao} que vai ser verificado
	 * @return <b><code>TRUE</code></b> se existe é diferente de
	 *         <code>null</code> e se os valores interno são maiores que
	 *         1.<br />
	 *         <b><code>FALSE</code></b> se o valor é null, se algum dos valores
	 *         internos é null e se alguns dos valores interno é menor que 1.
	 */
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

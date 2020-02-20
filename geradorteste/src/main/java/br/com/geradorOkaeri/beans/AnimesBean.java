package br.com.geradorOkaeri.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.geradorOkaeri.model.CompartilhamentoTipo;
import br.com.geradorOkaeri.model.ExibicaoCompartilhamento;
import br.com.geradorOkaeri.model.Legendas;
import br.com.geradorOkaeri.model.Post;
import br.com.geradorOkaeri.model.Qualidade;
import br.com.geradorOkaeri.validation.ValidationPost;

@Named
@ViewScoped
public class AnimesBean implements Serializable {
	private static final long serialVersionUID = -475922267196560694L;

	private Post post;
	private int countScreenShot;
	private List<SelectItem> qualidadeItens;
	private List<SelectItem> midiaItens;
	private List<SelectItem> legendasItens;
	private List<SelectItem> compartilhamentoTipoItens;
	private List<SelectItem> compartilhamentoExibicaoItens;
	private boolean renderCompartilhamentoTexto;

	@Inject
	private CountSessionBean count;

	@PostConstruct
	public void init() {
		qualidadeItens = loadQualidade();
		midiaItens = loadMidia();
		legendasItens = loadLegendas();
		compartilhamentoTipoItens = loadCompartilhamentoTipo();
		compartilhamentoExibicaoItens = loadCompartilhamentoExibicao();
		this.resetPost();

	}

	public void resetPost() {
		post = this.count.getPost();
		countScreenShot = 2;
		renderCompartilhamentoTexto = false;
	}

	private List<SelectItem> loadLegendas() {
		Function<Object, SelectItem> func = p -> {
			Legendas t = (Legendas) p;
			return new SelectItem(t, t.getName());
		};
		return loadCommon(Arrays.asList(Legendas.values()).stream(), func, "Selecione a legenda...");
	}

	private List<SelectItem> loadMidia() {
		Function<Object, SelectItem> func = p -> {
			Integer t = (Integer) p;
			return new SelectItem(t, t.toString());
		};
		return loadCommon(IntStream.rangeClosed(1, 4).boxed(), func, "Selecione a mídia...");
	}

	private List<SelectItem> loadQualidade() {
		Function<Object, SelectItem> func = p -> {
			Qualidade t = (Qualidade) p;
			return new SelectItem(t, t.getName());
		};
		return loadCommon(Arrays.asList(Qualidade.values()).stream(), func, "Selecione qualidade...");
	}

	private List<SelectItem> loadCompartilhamentoTipo() {
		Function<Object, SelectItem> func = p -> {
			CompartilhamentoTipo t = (CompartilhamentoTipo) p;
			return new SelectItem(t, t.getName());
		};
		return loadCommon(Arrays.asList(CompartilhamentoTipo.values()).stream(), func, null);
	}

	private List<SelectItem> loadCompartilhamentoExibicao() {
		Function<Object, SelectItem> func = p -> {
			ExibicaoCompartilhamento t = (ExibicaoCompartilhamento) p;
			return new SelectItem(t, t.getName());
		};
		return loadCommon(Arrays.asList(ExibicaoCompartilhamento.values()).stream(), func, null);
	}

	private List<SelectItem> loadCommon(Stream<?> strean, Function<Object, SelectItem> func, String firstSelect) {
		List<SelectItem> obj = new ArrayList<>();
		if (firstSelect != null && !firstSelect.isEmpty()) {
			obj.add(new SelectItem(null, firstSelect));
		}
		obj.addAll(strean.map(func).collect(Collectors.toList()));
		return obj;
	}

	private void ajustScreenshot(Post post, int countSS) {
		countSS = countSS > 1 ? countSS : 2;
		String[] atual = post.getScreenshot();
		String[] novo = null;
		if (atual != null && atual[0] != null && atual[0].trim().length() > 18) {
			novo = Arrays.copyOf(atual, countSS);
		} else {
			novo = new String[countSS];
		}
		post.setScreenshot(novo);
	}

	public void gerarPost() {

	}

	public void compartilhamentoRendered() {
		renderCompartilhamentoTexto = ValidationPost.getInstance()
				.verifyCompartilhamentoTipoTorrent(post.getCompartilhamentoTipo());
	}

	public int getCountScreenShot() {
		return countScreenShot;
	}

	public void setCountScreenShot(int countScreenShot) {
		this.countScreenShot = countScreenShot;
		this.ajustScreenshot(post, this.countScreenShot);
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<SelectItem> getQualidadeItens() {
		return qualidadeItens;
	}

	public void setQualidadeItens(List<SelectItem> qualidadeItens) {
		this.qualidadeItens = qualidadeItens;
	}

	public List<SelectItem> getMidiaItens() {
		return midiaItens;
	}

	public void setMidiaItens(List<SelectItem> midiaItens) {
		this.midiaItens = midiaItens;
	}

	public List<SelectItem> getLegendasItens() {
		return legendasItens;
	}

	public void setLegendasItens(List<SelectItem> legendasItens) {
		this.legendasItens = legendasItens;
	}

	public List<SelectItem> getCompartilhamentoTipoItens() {
		return compartilhamentoTipoItens;
	}

	public void setCompartilhamentoTipoItens(List<SelectItem> compartilhamentoTipoItens) {
		this.compartilhamentoTipoItens = compartilhamentoTipoItens;
	}

	public boolean isRenderCompartilhamentoTexto() {
		return renderCompartilhamentoTexto;
	}

	public void setRenderCompartilhamentoTexto(boolean renderCompartilhamentoTexto) {
		this.renderCompartilhamentoTexto = renderCompartilhamentoTexto;
	}

	public List<SelectItem> getCompartilhamentoExibicaoItens() {
		return compartilhamentoExibicaoItens;
	}

	public void setCompartilhamentoExibicaoItens(List<SelectItem> compartilhamentoExibicaoItens) {
		this.compartilhamentoExibicaoItens = compartilhamentoExibicaoItens;
	}
}

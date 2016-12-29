package br.com.geradorOkaeri.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.geradorOkaeri.model.Legendas;
import br.com.geradorOkaeri.model.Post;
import br.com.geradorOkaeri.model.Qualidade;

@Named
@ViewScoped
//@SessionScoped
public class AnimesBean implements Serializable {
	private static final long serialVersionUID = -475922267196560694L;

	private Post post;
	private int countScreenShot;
	private List<SelectItem> qualidadeItens;
	private List<SelectItem> midiaItens;
	private List<SelectItem> legendasItens;
	
	
	

	@PostConstruct
	public void init() {
		qualidadeItens = loadQualidade();
		midiaItens = loadMidia();
		legendasItens = loadLegendas();
		post = new Post();
		countScreenShot = 2;
	}
	
	private List<SelectItem> loadLegendas() {
		List<SelectItem> obj = new ArrayList<>();
		obj.add(new SelectItem(null, "Selecione a legenda..."));
		obj.addAll(Arrays.asList(Legendas.values()).stream().map(t -> new SelectItem(t, t.getName()))
				.collect(Collectors.toList()));
		return obj;
	}

	private List<SelectItem> loadMidia() {
		List<SelectItem> obj = new ArrayList<>();
		obj.add(new SelectItem(null, "Selecione mídia..."));
		obj.addAll(IntStream.rangeClosed(1, 4).boxed().map(i -> new SelectItem(i, i.toString()))
				.collect(Collectors.toList()));
		return obj;
	}

	private List<SelectItem> loadQualidade() {
		List<SelectItem> obj = new ArrayList<>();
		obj.add(new SelectItem(null, "Selecione qualidade..."));
		obj.addAll(Arrays.asList(Qualidade.values()).stream().map(t -> new SelectItem(t, t.getName()))
				.collect(Collectors.toList()));
		return obj;
	}

	private void ajustScreenshot(Post post, int countSS) {
		String[] atual = post.getScreenshot();
		String[] novo = null;
		if (atual != null && atual[0] != null && atual[0].trim().length() > 18) {
			novo = Arrays.copyOf(atual, countSS);
		} else {
			novo = new String[countSS];
		}
		post.setScreenshot(novo);
	}

	public int getCountScreenShot() {
		return countScreenShot;
	}

	public void setCountScreenShot(int countScreenShot) {
		this.countScreenShot = countScreenShot > 1 ? countScreenShot : 2;
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
}

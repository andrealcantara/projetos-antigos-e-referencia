package br.com.geradorOkaeri.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.geradorOkaeri.model.Post;
import br.com.geradorOkaeri.model.Qualidade;

@Named
@ViewScoped
public class AnimesBean implements Serializable {
	private static final long serialVersionUID = -475922267196560694L;

	private Post post;
	private int countScreenShot;
	private List<SelectItem> qualidadeItens;

	@PostConstruct
	public void init() {
		qualidadeItens = loadQualidade();
		post = new Post();
		countScreenShot = 2;
		this.ajustScreenshot(post, countScreenShot);
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
		this.countScreenShot = countScreenShot;
		this.ajustScreenshot(post, countScreenShot);
	}

	private List<SelectItem> loadQualidade() {
		List<SelectItem> obj = new ArrayList<>();
		obj.add(new SelectItem(null, "Selecione qualidade..."));
		obj.addAll(Arrays.asList(Qualidade.values()).stream().map(t -> new SelectItem(t, t.getName()))
				.collect(Collectors.toList()));
		return obj;
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
}

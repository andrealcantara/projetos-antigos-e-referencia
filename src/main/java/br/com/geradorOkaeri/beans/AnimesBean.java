package br.com.geradorOkaeri.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.geradorOkaeri.model.Post;

@Named
@ViewScoped
public class AnimesBean implements Serializable {
	private static final long serialVersionUID = -475922267196560694L;
	
	private Post post;
	private int countScrenShot;
	private List<SelectItem> itens;
	
	@PostConstruct
	public void init(){
		post = new Post();
		countScrenShot = 4;
		post.setScreenshot(new String[countScrenShot]);
	}
	
	public int getCountScrenShot() {
		return countScrenShot;
	}

	public void setCountScrenShot(int countScrenShot) {
		this.countScrenShot = countScrenShot;
	}

	public Post getPost() {
		return post;
	}
	
	public void setPost(Post post) {
		this.post = post;
	}
	
	public List<SelectItem> getItens() {
		return itens;
	}
	
	public void setItens(List<SelectItem> itens) {
		this.itens = itens;
	}
	
	
	
}

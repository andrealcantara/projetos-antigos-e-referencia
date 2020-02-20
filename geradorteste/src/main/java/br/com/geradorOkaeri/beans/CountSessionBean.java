package br.com.geradorOkaeri.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.geradorOkaeri.model.Post;
import br.com.geradorOkaeri.util.GeradorFacesContext;

@Named
@SessionScoped
public class CountSessionBean implements Serializable {
	private static final long serialVersionUID = -4624310991195490856L;
	public static final long FINAL_TIMER =
			// 30000; // 30 segundos
			60000L; // 1 minuto
	// 420000L; // 7 minutos

	private static final long COUNT_DOWN_TIMER = 30000;

	private Post post;

	@PostConstruct
	public void init() {
		this.post = new Post();
	}

	public void endSession() {
		Post other = new Post();
		this.post = other;
		try {
			GeradorFacesContext.redirect("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Long getTimeSessaoIdleMonitor() {
		return CountSessionBean.FINAL_TIMER - CountSessionBean.COUNT_DOWN_TIMER;
	}

	public Long getCountDownTimer() {
		return CountSessionBean.COUNT_DOWN_TIMER;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}

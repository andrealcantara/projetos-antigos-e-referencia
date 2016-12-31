package br.com.geradorOkaeri.beans;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.geradorOkaeri.model.Post;
import br.com.geradorOkaeri.util.GeradorFacesContext;

@Named
@SessionScoped
public class CountSessionBean implements Serializable {
	private static final long serialVersionUID = -4624310991195490856L;
	private static final long FINAL_TIMER = 30000; // 30 segundos
//			60000L;  // 1 minuto
//			420000L; // 7 minutos

	private long secondTimeExpire;
	private long timeExpire;
	private Post post;

	@PostConstruct
	public void init() {
		this.post = new Post();
		this.timeExpire = 1L;
		this.resetTime();
		this.calculateTimeLeft();
	}
	
	public void activity(){
		this.calculateTimeLeft();
		this.resetTime();
		this.calculateTimeLeft();
	}

	public void calculateTimeLeft() {
		long timeleft = this.timeExpire - System.currentTimeMillis();
		if (timeleft < 500L) {
			secondTimeExpire = 0L;
			try {
				GeradorFacesContext.endSession("animes.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("fim sessao");
		} else  {
			secondTimeExpire = timeleft / 1000L;
		}
	}
	
	public void resetTime() {
		System.out.println(this);
		long timeleft = this.timeExpire - System.currentTimeMillis();
		if(timeleft > 500L || this.timeExpire == 1L){
			timeExpire = System.currentTimeMillis() + CountSessionBean.FINAL_TIMER;
		}
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public long getSecondTimeExpire() {
		return secondTimeExpire;
	}

	public void setSecondTimeExpire(long secondTimeExpire) {
		this.secondTimeExpire = secondTimeExpire;
	}
}

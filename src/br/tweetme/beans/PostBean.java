package br.tweetme.beans;

import java.util.Date;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.tweetme.controllers.PostController;
import br.tweetme.controllers.UserController;
import br.tweetme.entities.Post;
import br.tweetme.entities.User;

public class PostBean {

	private String post;

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String share() {

		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String id = params.get("id");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);
		
		PostController pc = new PostController();
		User u = (User) session.getAttribute("user");

		try {
			Post post = pc.retrieve(Double.parseDouble(id));
			post.setOwner(u);
			post.setDate(new Date());
			pc.newPost(post);
			UserController uc = new UserController();
			User user = uc.retrieve(u.getLogin());
			session.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "share";
	}

	public String reply() {

		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String login = params.get("login");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);

		UserController uc = new UserController();

		User u = null;
		try {
			u = uc.retrieve(login);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		session.setAttribute("userProfile", u);

		post = "@" + login;

		return "post";
	}

	public String toPost() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);
		User user = (User) session.getAttribute("user");

		if (user == null)
			return null;

		System.out.println(getPost());

		Date date = new Date();

		Post p = new Post();
		p.setDate(date);
		p.setText(getPost());
		p.setOwner(user);
		p.setAuthor(user);

		PostController pc = new PostController();

		try {
			pc.newPost(p);

			UserController uc = new UserController();
			user = uc.retrieve(user.getLogin());
			session.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		setPost("");

		return "continue";
	}
	
}

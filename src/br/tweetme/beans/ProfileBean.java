package br.tweetme.beans;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.tweetme.controllers.UserController;
import br.tweetme.entities.User;

public class ProfileBean {
	
	public String follow() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);

		UserController uc = new UserController();

		User uFollower = (User) session.getAttribute("user");
		User uFollowed = (User) session.getAttribute("userProfile");

		uc.follow(uFollower, uFollowed);

		System.out.println("Following");
		
		User uf = null;
		try {
			uf = uc.retrieve(uFollowed.getLogin());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User u = null;
		try {
			u = uc.retrieve(uFollower.getLogin());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		session.setAttribute("userProfile", uf);
		session.setAttribute("user", u);
		
		return "profile";
	}

	public String unfollow() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);

		UserController uc = new UserController();

		User uFollower = (User) session.getAttribute("user");
		User uFollowed = (User) session.getAttribute("userProfile");

		uc.unFollow(uFollower, uFollowed);

		System.out.println("Unfollowing");

		User uf = null;
		try {
			uf = uc.retrieve(uFollowed.getLogin());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User u = null;
		try {
			u = uc.retrieve(uFollower.getLogin());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		session.setAttribute("userProfile", uf);
		session.setAttribute("user", u);

		return "profile";
	}

	public boolean isFollowing(User follower, User followed) {

		UserController uc = new UserController();

		return uc.isFollowing(follower, followed);
	}

	public String goToProfile() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String login = params.get("login");

		return goToProfile(login);
	}

	public String goToProfile(String login) {
		UserController uc = new UserController();
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);

		try {
			User u = uc.retrieve(login);

			System.out.println("UserProfile =======v " + login + "\n" + u);

			session.setAttribute("userProfile", u);
			session.setAttribute("found", null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "profile";
	}

	public String goToHome() {
		UserController uc = new UserController();
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);
		User current = (User) session.getAttribute("user");

		try {
			User u = uc.retrieve(current.getLogin());

			session.setAttribute("user", u);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return "home";
	}

	public String goToSettings() {
		UserController uc = new UserController();
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);
		User current = (User) session.getAttribute("user");

		try {
			User u = uc.retrieve(current.getLogin());

			session.setAttribute("user", u);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "settings";
	}

	public String goToFollowers() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String login = params.get("login");

		return goToFollowers(login);
	}

	public String goToFollowers(String login) {
		UserController uc = new UserController();
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);

		try {
			User u = uc.retrieve(login);

			session.setAttribute("userProfile", u);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "followers";
	}

	public String goToFollowing() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String login = params.get("login");

		return goToFollowing(login);
	}

	public String goToFollowing(String login) {
		UserController uc = new UserController();
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);

		try {
			User u = uc.retrieve(login);

			session.setAttribute("userProfile", u);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "following";
	}

	public String goToSearch() {
		UserController uc = new UserController();
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);
		User current = (User) session.getAttribute("user");

		try {
			User u = uc.retrieve(current.getLogin());

			session.setAttribute("user", u);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "search";
	}
}

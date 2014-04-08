package br.tweetme.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.tweetme.controllers.UserController;
import br.tweetme.entities.User;


@ManagedBean (name="loginBean")
@SessionScoped
public class LoginBean {

	protected String username;
	protected String senha;
	@ManagedProperty(value="#{userBean}")
	protected UserBean userBean; 

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public LoginBean() {
	
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String signin() {
		String outcome = "";
		UserController uc = new UserController();
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		
		boolean found = uc.checkLogin(getUsername());
		
		if (found) {
			try {
				User u = uc.signin(getUsername(), getSenha());
				if (u != null) {
					session.setAttribute("user", u);
					userBean.setCurrentUser(u);
					outcome = "continue";	
				} else {
					FacesMessage facesMsg = new FacesMessage("msg.email_error");
					FacesContext.getCurrentInstance().addMessage(null, facesMsg);
					outcome = null;	
				}
			} catch (Exception e) {
				System.err.println("Erro signin: " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			FacesMessage facesMsg = new FacesMessage("msg.email_error");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			outcome = null;
		}
		
		
		return outcome;
	}
	
	public String logout(){
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.invalidate();
		
		return "outcome";
	}
}

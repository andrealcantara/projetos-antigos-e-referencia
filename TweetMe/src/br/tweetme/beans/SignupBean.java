package br.tweetme.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.tweetme.controllers.UserController;
import br.tweetme.entities.User;

@ManagedBean(name = "signupBean")
@SessionScoped
public class SignupBean {

	protected String email;
	protected String name;
	protected String password;
	protected String login;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String emailValidate() {
		
		UserController uc = new UserController();
		String outcome = "";
		
		boolean found = uc.checkEmail(getEmail());
		
		if (!found) {
			outcome = "continue";
		} else {
			FacesMessage facesMsg = new FacesMessage("msg.email_error");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			outcome = null;
		}
		
		System.out.println(outcome);
		
		return outcome;
	}
	
	public String signup() {
		UserController uc = new UserController();
		String outcome = "";
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		
		boolean found = uc.checkLogin(getLogin());
		
		if (!found) {
			System.out.println("Cadastrando usuario");
			User user = new User();
			user.setEmail(getEmail());
			user.setName(getName());
			user.setLogin(getLogin());
			user.setPassword(getPassword());
			
			try {
				uc.signup(user);
				user = uc.retrieve(user.getLogin());
				session.setAttribute("user", user);
				outcome = "continue";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			FacesMessage facesMsg = new FacesMessage("msg.email_error");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			outcome = null;
		}
		
		return outcome;
	}
	
}

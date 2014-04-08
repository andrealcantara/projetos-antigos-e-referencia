package br.tweetme.beans;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.tweetme.controllers.SearchController;
import br.tweetme.entities.User;

public class SearchBean {

	protected String parameter;
	
	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String search() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		
		SearchController sc = new SearchController();
				
		List<User> list = sc.searchUsersByName(parameter);
		
		session.setAttribute("found", list);
		
		setParameter("");
		
		return "search";
	}
	
}

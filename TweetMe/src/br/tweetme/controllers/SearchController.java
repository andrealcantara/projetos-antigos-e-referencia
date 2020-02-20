package br.tweetme.controllers;

import java.util.ArrayList;
import java.util.List;

import br.tweetme.entities.User;
import br.tweetme.factories.FactoryDAOJdbc;
import br.tweetme.persistenceDAO.FactoryDAO;
import br.tweetme.persistenceDAO.UserDAO;

public class SearchController {

	protected FactoryDAO factory = null;
	
	public SearchController() {
		this.factory = FactoryDAOJdbc.getInstance();
	}
	
	
	public List<User> searchUsersByName(String name) {
		UserDAO ud = factory.createUserPersistence();

		List<User> found = new ArrayList<User>();
		List<User> users = null;

		try {
			users = ud.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (User user : users) {
			if (user.getName().contains(name)) {
				found.add(user);
			}
		}

		return found;
	}

}

package br.tweetme.factories;

import br.tweetme.persistenceDAO.FactoryDAO;
import br.tweetme.persistenceDAO.PostDAO;
import br.tweetme.persistenceDAO.UserDAO;
import br.tweetme.persistenceDAOJdbc.PostDAOJdbc;
import br.tweetme.persistenceDAOJdbc.UserDAOJdbc;

public class FactoryDAOJdbc implements FactoryDAO {

	private static FactoryDAOJdbc instance = null;
	
	public static synchronized FactoryDAOJdbc getInstance(){
		if(instance == null){
			instance = new FactoryDAOJdbc();
		}
		return instance;
	}
		
	@Override
	public UserDAO createUserPersistence() {
		return new UserDAOJdbc();
	}

	@Override
	public PostDAO createPostPersistence() {
		return new PostDAOJdbc();
	}

}

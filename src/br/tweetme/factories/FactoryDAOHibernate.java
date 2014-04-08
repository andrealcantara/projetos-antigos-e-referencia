package br.tweetme.factories;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.tweetme.persistenceDAO.FactoryDAO;
import br.tweetme.persistenceDAO.PostDAO;
import br.tweetme.persistenceDAO.UserDAO;
import br.tweetme.persistenceDAOHibernate.PostDAOHibernate;
import br.tweetme.persistenceDAOHibernate.UserDAOHibernate;

public class FactoryDAOHibernate implements FactoryDAO {

	private static final String PERSISTENCE_UNIT_NAME = "TweetmeJPA";
	private EntityManagerFactory factory;
	private static FactoryDAOHibernate instance = null;
	
	public FactoryDAOHibernate(){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	
	public EntityManagerFactory getFactory(){
		return factory;
	}
	
	public static synchronized FactoryDAOHibernate getInstance(){
		if(instance == null){
			instance = new FactoryDAOHibernate();
		}
		return instance;
	}
	
	private void destroyFactory(){
		factory.close();		
	}
	
	public static void destroyInstance(){
		if(instance != null){
			instance.destroyFactory();
			instance = null;
		}
	}
	
	@Override
	public UserDAO createUserPersistence() {
		return new UserDAOHibernate();
	}

	@Override
	public PostDAO createPostPersistence() {
		return new PostDAOHibernate();
	}
	
}

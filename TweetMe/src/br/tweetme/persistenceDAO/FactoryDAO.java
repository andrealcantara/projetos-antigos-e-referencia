package br.tweetme.persistenceDAO;

public interface FactoryDAO {

	public UserDAO createUserPersistence();
	
	public PostDAO createPostPersistence();
	
}

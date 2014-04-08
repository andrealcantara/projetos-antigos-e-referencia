package tests;

import java.util.ArrayList;
import java.util.List;

import br.tweetme.entities.User;
import br.tweetme.factories.FactoryDAOHibernate;
import br.tweetme.factories.FactoryDAOJdbc;
import br.tweetme.persistenceDAO.FactoryDAO;
import br.tweetme.persistenceDAO.UserDAO;

public class Test {

	private static FactoryDAO factory = FactoryDAOJdbc.getInstance();

	public static void main(String[] args) {
		// insertUser();
//		 retrieveUser();
		// deleteUser();
		
		testePesquisa();

		FactoryDAOHibernate.destroyInstance();
	}

	
	public static void testePesquisa() {
		String palavra = "er";
		
		UserDAO ud = factory.createUserPersistence();
		
		List<User> encontrados = new ArrayList<User>();
		List<User> users = null;
		
		try {
			users = ud.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (User user : users) {
			if (user.getName().contains(palavra)) {
				encontrados.add(user);
			}
		}
		
		System.out.println("\n\n============================================\n\n");
		
		for (User user : encontrados) {
			System.out.println(user);
		}
		
	}
	
	
	public static void insertUser() {
		UserDAO ud = factory.createUserPersistence();

		User user = new User();

		user.setLogin("asjajs");
		user.setPassword("123456");
		user.setName("ASJ AJS");
		user.setDescription("Falcão");
		user.setEmail("asj_ajs@outlook.com");

		try {
			ud.insert(user);
			System.out.println("User inserido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao inserir user - " + e.getMessage());
		}

	}

	public static User retrieveUser() {
		UserDAO ud = factory.createUserPersistence();

		User user = null;

		try {
			user = ud.retrieve("fraudlucas");
			System.out.println("User " + user.getLogin() + " "
					+ user.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	@SuppressWarnings("unused")
	private static void deleteUser() {
		UserDAO ud = factory.createUserPersistence();

		User user = retrieveUser();

		try {
			ud.delete(user);
			System.out.println("User deletado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

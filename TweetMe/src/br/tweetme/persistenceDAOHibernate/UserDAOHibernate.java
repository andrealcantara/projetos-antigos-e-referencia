package br.tweetme.persistenceDAOHibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.Hibernate;

import br.tweetme.entities.User;
import br.tweetme.factories.FactoryDAOHibernate;
import br.tweetme.persistenceDAO.UserDAO;

public class UserDAOHibernate implements UserDAO {

	public UserDAOHibernate() {
	}

	@Override
	public void insert(User user) throws Exception {
		EntityManager em = FactoryDAOHibernate.getInstance().getFactory()
				.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(user);
		et.commit();
		em.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public User retrieve(String login) throws Exception {
		EntityManager em = FactoryDAOHibernate.getInstance().getFactory()
				.createEntityManager();

		User user = null;

		String jqpl = "select u from User u where u.login = ?";
		Query query = em.createQuery(jqpl);
		query.setParameter(1, login);

		List<User> users = query.getResultList();

		for (int i = 0; i < users.size(); i++) {
			user = users.get(i);
		}

		Hibernate.initialize(user.getFollowers());
		Hibernate.initialize(user.getFollowing());

		em.close();
		return user;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public User retrieveByEmail(String email) throws Exception {
		EntityManager em = FactoryDAOHibernate.getInstance().getFactory()
				.createEntityManager();

		User user = null;

		String jqpl = "select u from User u where u.email = ?";
		Query query = em.createQuery(jqpl);
		query.setParameter(1, email);

		List<User> users = query.getResultList();

		for (int i = 0; i < users.size(); i++) {
			user = users.get(i);
			System.out.println(user.getName());
		}

		Hibernate.initialize(user.getFollowers());
		Hibernate.initialize(user.getFollowing());

		em.close();
		return user;
	}

	@Override
	public void update(User user) throws Exception {
		EntityManager em = FactoryDAOHibernate.getInstance().getFactory()
				.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		user = em.merge(user);
		et.commit();
		em.close();
	}

	@Override
	public void delete(User user) throws Exception {
		EntityManager em = FactoryDAOHibernate.getInstance().getFactory()
				.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		User u = em.find(User.class, user.getId());
		em.remove(u);
		et.commit();
		em.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> list() throws Exception {

		EntityManager em = FactoryDAOHibernate.getInstance().getFactory()
				.createEntityManager();
		List<User> users = em.createQuery("select user from User user")
				.getResultList();
		em.close();

		return users;
	}

	@Override
	public void follow(User follower, User followed) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unfollow(User follower, User followed) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User retrieve(double id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}

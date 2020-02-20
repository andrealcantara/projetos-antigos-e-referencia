package br.tweetme.persistenceDAOHibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Hibernate;

import br.tweetme.entities.Post;
import br.tweetme.factories.FactoryDAOHibernate;
import br.tweetme.persistenceDAO.PostDAO;

public class PostDAOHibernate implements PostDAO {

	public PostDAOHibernate() {
	}
	
	@Override
	public void insert(Post post) throws Exception {
		EntityManager em = FactoryDAOHibernate.getInstance().getFactory()
				.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(post);
		et.commit();
		em.close();		
	}

	@Override
	public Post retrieve(double id) throws Exception {
		EntityManager em = FactoryDAOHibernate.getInstance().getFactory()
				.createEntityManager();
		Post post = em.find(Post.class, id);

		 Hibernate.initialize(post.getOwner());

		em.close();
		return post;
	}

	@Override
	public void update(Post post) throws Exception {
		EntityManager em = FactoryDAOHibernate.getInstance().getFactory()
				.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		post = em.merge(post);
		et.commit();
		em.close();		
	}

	@Override
	public void delete(Post post) throws Exception {
		EntityManager em = FactoryDAOHibernate.getInstance().getFactory()
				.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Post p = em.find(Post.class, post.getId());
		em.remove(p);
		et.commit();
		em.close();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> list() throws Exception {
		EntityManager em = FactoryDAOHibernate.getInstance().getFactory()
				.createEntityManager();
		List<Post> posts = em.createQuery("select post from Post post")
				.getResultList();
		em.close();

		return posts;
	}

}

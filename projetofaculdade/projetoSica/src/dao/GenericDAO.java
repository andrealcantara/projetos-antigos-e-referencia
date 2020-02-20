package dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericDAO<T> implements Serializable,IGenericDAO<T> {
	private static final long serialVersionUID = 1L;
	private static EntityManagerFactory fabrica;
	private static EntityManager manager;
	private Class<?> domain;
	
	public static EntityManager getManager() {
		if(GenericDAO.manager == null ){
			GenericDAO.fabrica = Persistence.createEntityManagerFactory("projetoSica");
			GenericDAO.manager = fabrica.createEntityManager();
		}
		return manager;
	}
	
	public static void closeEntityManager(){
		if(GenericDAO.manager != null){
			GenericDAO.manager.clear();
			GenericDAO.manager.close();
			GenericDAO.manager = null;
			GenericDAO.fabrica.getCache().evictAll();
			GenericDAO.getManager();
		}
	}


	private Class<?> getDomainClass() {
		if(this.domain == null){
			this.domain = getGenericTypeArgument(this.getClass(),0);
		}
		return domain;
	}

	@SuppressWarnings("unchecked")
	private Class<?> getGenericTypeArgument(final Class<?>class1,
			final int idx) {
		final Type type = class1.getGenericSuperclass();
		ParameterizedType paramType;
		
		try{
			paramType = (ParameterizedType) type;
		}catch(ClassCastException c){
			paramType = (ParameterizedType) ((Class<T>) type).getGenericSuperclass();
		}
		
		return (Class<T>) paramType.getActualTypeArguments()[idx];
	}

	public void setDomain(Class<?> domain) {
		this.domain = domain;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return getManager().createQuery("Select this from " + getDomainClass().getSimpleName()
										+"this").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(Object id) {
		return (T) getManager().find(getDomainClass(), id);
	}

	@Override
	public void insert(T classe) {
		EntityManager em = getManager();
		try{
			em.getTransaction().begin();
			em.persist(classe);
			em.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void update(T classe) {
		EntityManager em = getManager();
		
		try{
			em.getTransaction().begin();
			em.merge(classe);
			em.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(T classe) {
		EntityManager em = getManager();
		
		try{
			em.getTransaction().begin();
			em.remove(em.merge(classe));
			em.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}

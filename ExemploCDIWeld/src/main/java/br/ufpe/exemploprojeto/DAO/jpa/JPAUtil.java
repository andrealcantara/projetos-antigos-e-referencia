package br.ufpe.exemploprojeto.DAO.jpa;

import java.io.IOException;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	public static String FACTORY_DB = "bancoNormal";
	
	@Produces @ApplicationScoped
	public EntityManagerFactory getEntityManagerFactory(){
		Properties props = new Properties();
		try {
			props.load(JPAUtil.class.getClassLoader().getResourceAsStream("application.properties"));
			return Persistence.createEntityManagerFactory(FACTORY_DB, props);
		}catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	@Produces @RequestScoped
	public EntityManager getEntityManager(EntityManagerFactory factory){
		return factory.createEntityManager();
	}
	
	public void destroyerEntityManager(@Disposes EntityManager em){
		em.close();
	}
}

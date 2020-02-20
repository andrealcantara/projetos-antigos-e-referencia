package br.ufpe.exemploprojeto.DAO.jpa;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.ufpe.exemploprojeto.DAO.GenericDAO;

@ApplicationScoped
@SuppressWarnings("unchecked")
public abstract class JPAGenericDAO<Entidade> implements GenericDAO<Entidade> {
	private static final long serialVersionUID = 7457619441441316503L;
	
	@Inject
	private EntityManager em;
	

	@Override
	public Entidade inserir(Entidade entity){
		em.persist(entity);
		return entity;
	}
	

	@Override
	public Entidade alterar(Entidade entity){
		em.merge(entity);
		return entity;
	}
	

	@Override
	public void remover(Entidade entity){
		em.remove(entity);
	}
	

	@Override
	public Entidade buscaPorId(Long id){
		return (Entidade) em.find(getTypeClass(), id);
	}
	

	@Override
	public List<Entidade> listaTodos(){
		return em.createQuery(("FROM "+getTypeClass().getName())).getResultList();
	}
	
	private Class<Entidade> getTypeClass() {
        Class<Entidade> clazz = (Class<Entidade>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        return clazz;
    }	
}

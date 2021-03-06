package br.ufpe.exemploprojeto.controlador;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.RollbackException;

import br.ufpe.exemploprojeto.DAO.GenericDAO;
import br.ufpe.exemploprojeto.DAO.exception.BadRequestDaoException;

@ApplicationScoped
@SuppressWarnings("unchecked")
public abstract class Controlador<Entidade> implements Serializable {
	private static final long serialVersionUID = 2487134818109452029L;

	private GenericDAO<Entidade> genericDAO;
	
	@Inject
	private Factory fabrica;
	
	@PostConstruct
	public void init(){
		genericDAO = fabrica.factoryDao(getTypeClass());
	}

	public void inserir(Entidade user) throws BadRequestDaoException {
		try {
			genericDAO.inserir(user);
		} catch (RollbackException e) {
			throw new BadRequestDaoException();
		}
	}

	public Entidade alterar(Entidade user) throws BadRequestDaoException {
		Entidade retorno = null;
		try {
			retorno = genericDAO.alterar(user);
		} catch (RollbackException e) {
			throw new BadRequestDaoException();
		}
		return retorno;
	}

	public Entidade buscarPorId(long id) throws BadRequestDaoException {
		Entidade retorno = null;
		try {
			retorno = genericDAO.buscaPorId(id);
		} catch (NoResultException e) {
		}
		return retorno;
	}

	public void remover(Entidade user) throws BadRequestDaoException {
		try {
			genericDAO.remover(user);
		} catch (RollbackException e) {
			throw new BadRequestDaoException();
		}
	}
	
	private Class<Entidade> getTypeClass() {
        Class<Entidade> clazz = (Class<Entidade>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        return clazz;
    }
}

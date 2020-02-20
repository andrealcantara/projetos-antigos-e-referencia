package dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author Diego
 *	Interface genérica a todos os DAOS,
 *	basicamente possui os métodos de listagem
 *	e buscas mais básicos para uma determinada classe <T>.
 *
 * @param <T>
 */
public interface IGenericDAO<T> extends Serializable {
	
	public List<T> findAll();
	
	public T findById(Object id);
	
	public void insert(T classe);
	
	public void update(T classe);
	
	public void delete(T classe);

}

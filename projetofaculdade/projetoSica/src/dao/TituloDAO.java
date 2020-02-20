package dao;

import java.io.Serializable;
import java.util.List;

import model.Titulo;

public class TituloDAO extends GenericDAO<Titulo> implements Serializable,ITituloDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void inserirListaDeTitulos(List<Titulo> lista) {
		int i = 0;
		
		if(lista != null){
			for(i = 0;i < lista.size();i++){
				this.insert(lista.get(i));
			}
		}
		
	}

}

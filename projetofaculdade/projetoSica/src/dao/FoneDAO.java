package dao;

import java.io.Serializable;
import java.util.List;

import model.Fone;

public class FoneDAO extends GenericDAO<Fone> implements Serializable,IFoneDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void inserirTelefones(List<Fone> lista) {
		int i;
		
		if(lista != null){
			
			for(i=0; i< lista.size();i++){
				this.insert(lista.get(i));
			}
			
		}
		
	}

}

package dao;

import java.io.Serializable;
import java.util.List;

import model.Turma;

public class TurmaDAO extends GenericDAO<Turma> implements Serializable,ITurmaDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void inserirListaDeTurmas(List<Turma> lista) {
		int i;
		
		if(lista != null){
			for(i = 0; i< lista.size();i++){
				this.insert(lista.get(i));
			}
		}
		
	}
	
	
}

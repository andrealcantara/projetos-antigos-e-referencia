package dao;

import java.io.Serializable;
import java.util.List;

import model.Professor;

public class ProfessorDAO extends GenericDAO<Professor> implements Serializable,IProfessorDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void inserirListaDeProfessor(List<Professor> lista) {
		int i = 0;
		
		if(lista != null){
			for(i = 0;i < lista.size();i++){
				this.insert(lista.get(i));
			}
		}
		
	}

}

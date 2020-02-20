package dao;

import java.io.Serializable;
import java.util.List;

import model.Disciplina;

public class DisciplinaDAO extends GenericDAO<Disciplina> implements
		Serializable ,IDisciplinaDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void inserirListaDisciplina(List<Disciplina> lista) {
		int i = 0;
		
		if(lista != null){
			for(i = 0;i < lista.size();i++){
				this.insert(lista.get(i));
			}
		}
		
	}

}

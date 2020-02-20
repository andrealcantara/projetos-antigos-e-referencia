package dao;

import java.io.Serializable;
import java.util.List;

import model.Aluno;

public class AlunoDAO extends GenericDAO<Aluno> implements Serializable,IAlunoDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void inserirListaDeAlunos(List<Aluno> lista) {
		int i;
		
		if(lista != null){
			for(i = 0;i < lista.size();i++){
				this.insert(lista.get(i));
			}
		}
		
	}

}

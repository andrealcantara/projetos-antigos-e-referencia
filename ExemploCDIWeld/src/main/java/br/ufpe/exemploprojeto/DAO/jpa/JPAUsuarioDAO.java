package br.ufpe.exemploprojeto.DAO.jpa;

import br.ufpe.exemploprojeto.DAO.UsuarioDAO;
import br.ufpe.exemploprojeto.annotation.DAO;
import br.ufpe.exemploprojeto.model.Usuario;

@DAO(value=Usuario.class)
public class JPAUsuarioDAO extends JPAGenericDAO<Usuario> implements UsuarioDAO  {
	private static final long serialVersionUID = -5993488566195264489L;
}

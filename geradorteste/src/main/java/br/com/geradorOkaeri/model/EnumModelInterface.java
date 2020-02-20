package br.com.geradorOkaeri.model;

/**
 * Interface que representa os Enuns usados em {@link Post}.
 * @author andre
 *
 */
public interface EnumModelInterface {
	
	/**
	 * Retorna o id do enum.
	 * @return int id.
	 */
	public int getId();
	
	/**
	 * Retorna o <code>name</code> do enum. 
	 * @return {@link String} name.
	 */
	public String getName();
}

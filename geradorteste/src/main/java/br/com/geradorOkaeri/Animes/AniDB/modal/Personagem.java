package br.com.geradorOkaeri.Animes.AniDB.modal;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("character")
public class Personagem implements Serializable {
	private static final long serialVersionUID = -3382201826640282025L;

	@XStreamAsAttribute
	private Integer id;
	
	@XStreamAlias("type")
	@XStreamAsAttribute
	private String tipo;
	
	@XStreamAlias("name")
	private String nome;
	
	@XStreamAlias("picture")
	private String urlImagem;
	
	@XStreamAlias("seiyuu")
	private String dublador;
	
	
//	private S
	
	
	
}

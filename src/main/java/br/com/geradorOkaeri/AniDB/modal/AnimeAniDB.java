package br.com.geradorOkaeri.AniDB.modal;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;

import br.com.geradorOkaeri.AniDB.converter.AnimeAniDBTypeConverterXML;
import br.com.geradorOkaeri.Util.converters.LocalDateConverterXML;

@XStreamAlias("anime")
public class AnimeAniDB implements Serializable{
	private static final long serialVersionUID = 8054205825225198045L;
	
	private static final String pathImg = "";

//	@XStreamConverter
//	private String nome;
	
	@XStreamAlias("episodecount")
	private Integer qntEpisodios;
	
	@XStreamAlias("type")
	@XStreamConverter(AnimeAniDBTypeConverterXML.class)
	private AnimeAniDBType tipo;
	
	@XStreamAlias("startdate")
	@XStreamConverter(LocalDateConverterXML.class)
	private LocalDate data_inicio;
	
	@XStreamAlias("enddate")
	@XStreamConverter(LocalDateConverterXML.class)
	private LocalDate data_fim;
	
	@XStreamAlias("titles")
	@XStreamConverter(CollectionConverter.class)
	private List<TituloAniDB> titulos;
	
	@XStreamAlias("url")
	private String urlOficial;
	
	@XStreamAlias("creators")
	@XStreamConverter(CollectionConverter.class)
	private List<Staff> criadores;
	
	@XStreamAlias("description")
	private String descricao;
	
	@XStreamAlias("picture")
	private String urlImg;
	
	@XStreamAlias("characters")
	@XStreamConverter(CollectionConverter.class)
	private List<Personagem> personagens;
	
	private List<Episodio> episodios;
	
	
	
	
	
	
	
	
	
}

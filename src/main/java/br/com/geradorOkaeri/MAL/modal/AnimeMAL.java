package br.com.geradorOkaeri.MAL.modal;

import java.io.Serializable;
import java.time.LocalDate;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import br.com.geradorOkaeri.MAL.converter.AnimeMALStatusConverterXML;
import br.com.geradorOkaeri.MAL.converter.LocalDateConverterXML;

@XStreamAlias("entry")
public class AnimeMAL implements Serializable{
	/**
	 * 
	 */
	@XStreamOmitField
	private static final long serialVersionUID = -3007788978167572481L;
	
	private Integer id;
	@XStreamAlias("title")
	private String titulo;

	@XStreamAlias("english")
	private String tituloIngles;

	@XStreamAlias("synonyms")
	private String sinonimos;

	@XStreamAlias("episodes")
	private Integer episodios;
	

	@XStreamAlias("score")
	private Double nota;
	
	@XStreamAlias("type")
	private AnimeMALType tipo;
	
	@XStreamConverter(AnimeMALStatusConverterXML.class)
	private AnimeMALStatus status;
	
	@XStreamAlias("start_date")
	@XStreamConverter(LocalDateConverterXML.class)
	private LocalDate date_inicio;
	
	@XStreamAlias("end_date")
	@XStreamConverter(LocalDateConverterXML.class)
	private LocalDate date_fim;
	
	
	@XStreamAlias("synopsis")
	private String sinopse;
	
	@XStreamAlias("image")
	private String urlImagePost;
	
	public static AnimeMAL of(String id, String titulo, String tituloIngles, String sinonimos, String episodios, String nota,
			String tipo, String status, String date_inicio, String date_fim, String sinopse,
			String urlImagePost) {
		AnimeMAL retorno = new AnimeMAL();
		retorno.id = Integer.parseInt(id);
		retorno.titulo = titulo;
		retorno.tituloIngles = tituloIngles;
		retorno.sinonimos = sinonimos;
		retorno.episodios = Integer.parseInt(episodios);
		retorno.nota = Double.parseDouble(nota);
		retorno.tipo = AnimeMALType.valueOfName(tipo);
		retorno.status = AnimeMALStatus.valueOfName(status);
		retorno.date_inicio = LocalDate.parse(date_inicio);
		retorno.date_fim = LocalDate.parse(date_fim);
		retorno.sinopse = sinopse;
		retorno.urlImagePost = urlImagePost;
		return retorno;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTituloIngles() {
		return tituloIngles;
	}
	public void setTituloIngles(String tituloIngles) {
		this.tituloIngles = tituloIngles;
	}
	public String getSinonimos() {
		return sinonimos;
	}
	public void setSinonimos(String sinonimos) {
		this.sinonimos = sinonimos;
	}
	public Integer getEpisodios() {
		return episodios;
	}
	public void setEpisodios(Integer episodios) {
		this.episodios = episodios;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}
	public AnimeMALType getTipo() {
		return tipo;
	}
	public void setTipo(AnimeMALType tipo) {
		this.tipo = tipo;
	}
	public AnimeMALStatus getStatus() {
		return status;
	}
	public void setStatus(AnimeMALStatus status) {
		this.status = status;
	}
	public LocalDate getDate_inicio() {
		return date_inicio;
	}
	public void setDate_inicio(LocalDate date_inicio) {
		this.date_inicio = date_inicio;
	}
	public LocalDate getDate_fim() {
		return date_fim;
	}
	public void setDate_fim(LocalDate date_fim) {
		this.date_fim = date_fim;
	}
	public String getSinopse() {
		return sinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	public String getUrlImagePost() {
		return urlImagePost;
	}
	public void setUrlImagePost(String urlImagePost) {
		this.urlImagePost = urlImagePost;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnimeMAL other = (AnimeMAL) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	@Override
	public String toString() {
		return "AnimeMAL [id=" + id + ", titulo=" + titulo + ", tituloIngles=" + tituloIngles + ", sinonimos="
				+ sinonimos + ", episodios=" + episodios + ", nota=" + nota + ", tipo=" + tipo + ", status=" + status
				+ ", date_inicio=" + date_inicio + ", date_fim=" + date_fim + ", sinopse=" + sinopse + ", urlImagePost="
				+ urlImagePost + "]";
	}
}

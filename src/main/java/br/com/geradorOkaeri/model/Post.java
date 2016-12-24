package br.com.geradorOkaeri.model;

import java.io.Serializable;
import java.util.Arrays;

public class Post implements Serializable {
	private static final long serialVersionUID = -4061495122851363504L;
	
	private String titulo;
	private String tituloOriginal;
	private String tituloEpisodio;
	private String qntEpisodio;
	private String temporada;
	private String duracao;
	private String fansuber;
	private String upadorpor;
	private String ripadopor;
	private String siteOficial;
	private String imagemCapa;
	private String sinopsePTBR;
	private String[] screenshot;
	private Qualidade qualidade;
	private Integer midia;
	private String idiomaAudio;
	private String tamanhoArquivo;
	private String trailer;
	private String genero;
	private String videoBitrate;
	private String videoCodec;
	private String audioBitrate;
	private String audioCodec;
	private String frameRate;
	private String resolucaoVideo;
	private Resolucao formatoTela;
	private Legendas legendas;
	private String outrasInfo;
	private ExibicaoCompartilhamento exibicaoCompartilhamento;
	private CompartilhamentoTipo compartilhamentoTipo;
	private String compartilhamento;
	private boolean temSenha;
	private String senha;
	private String borda;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compartilhamento == null) ? 0 : compartilhamento.hashCode());
		result = prime * result + ((compartilhamentoTipo == null) ? 0 : compartilhamentoTipo.hashCode());
		result = prime * result + ((duracao == null) ? 0 : duracao.hashCode());
		result = prime * result + ((fansuber == null) ? 0 : fansuber.hashCode());
		result = prime * result + ((formatoTela == null) ? 0 : formatoTela.hashCode());
		result = prime * result + ((idiomaAudio == null) ? 0 : idiomaAudio.hashCode());
		result = prime * result + ((imagemCapa == null) ? 0 : imagemCapa.hashCode());
		result = prime * result + ((legendas == null) ? 0 : legendas.hashCode());
		result = prime * result + ((qualidade == null) ? 0 : qualidade.hashCode());
		result = prime * result + ((resolucaoVideo == null) ? 0 : resolucaoVideo.hashCode());
		result = prime * result + Arrays.hashCode(screenshot);
		result = prime * result + ((sinopsePTBR == null) ? 0 : sinopsePTBR.hashCode());
		result = prime * result + ((tamanhoArquivo == null) ? 0 : tamanhoArquivo.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Post)) {
			return false;
		}
		Post other = (Post) obj;
		if (compartilhamento == null) {
			if (other.compartilhamento != null) {
				return false;
			}
		} else if (!compartilhamento.equals(other.compartilhamento)) {
			return false;
		}
		if (compartilhamentoTipo != other.compartilhamentoTipo) {
			return false;
		}
		if (duracao == null) {
			if (other.duracao != null) {
				return false;
			}
		} else if (!duracao.equals(other.duracao)) {
			return false;
		}
		if (fansuber == null) {
			if (other.fansuber != null) {
				return false;
			}
		} else if (!fansuber.equals(other.fansuber)) {
			return false;
		}
		if (formatoTela == null) {
			if (other.formatoTela != null) {
				return false;
			}
		} else if (!formatoTela.equals(other.formatoTela)) {
			return false;
		}
		if (idiomaAudio == null) {
			if (other.idiomaAudio != null) {
				return false;
			}
		} else if (!idiomaAudio.equals(other.idiomaAudio)) {
			return false;
		}
		if (imagemCapa == null) {
			if (other.imagemCapa != null) {
				return false;
			}
		} else if (!imagemCapa.equals(other.imagemCapa)) {
			return false;
		}
		if (legendas != other.legendas) {
			return false;
		}
		if (qualidade != other.qualidade) {
			return false;
		}
		if (resolucaoVideo == null) {
			if (other.resolucaoVideo != null) {
				return false;
			}
		} else if (!resolucaoVideo.equals(other.resolucaoVideo)) {
			return false;
		}
		if (!Arrays.equals(screenshot, other.screenshot)) {
			return false;
		}
		if (sinopsePTBR == null) {
			if (other.sinopsePTBR != null) {
				return false;
			}
		} else if (!sinopsePTBR.equals(other.sinopsePTBR)) {
			return false;
		}
		if (tamanhoArquivo == null) {
			if (other.tamanhoArquivo != null) {
				return false;
			}
		} else if (!tamanhoArquivo.equals(other.tamanhoArquivo)) {
			return false;
		}
		if (titulo == null) {
			if (other.titulo != null) {
				return false;
			}
		} else if (!titulo.equals(other.titulo)) {
			return false;
		}
		return true;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTituloOriginal() {
		return tituloOriginal;
	}
	public void setTituloOriginal(String tituloOriginal) {
		this.tituloOriginal = tituloOriginal;
	}
	public String getTituloEpisodio() {
		return tituloEpisodio;
	}
	public void setTituloEpisodio(String tituloEpisodio) {
		this.tituloEpisodio = tituloEpisodio;
	}
	public String getQntEpisodio() {
		return qntEpisodio;
	}
	public void setQntEpisodio(String qntEpisodio) {
		this.qntEpisodio = qntEpisodio;
	}
	public String getTemporada() {
		return temporada;
	}
	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}
	public String getDuracao() {
		return duracao;
	}
	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}
	public String getFansuber() {
		return fansuber;
	}
	public void setFansuber(String fansuber) {
		this.fansuber = fansuber;
	}
	public String getUpadorpor() {
		return upadorpor;
	}
	public void setUpadorpor(String upadorpor) {
		this.upadorpor = upadorpor;
	}
	public String getRipadopor() {
		return ripadopor;
	}
	public void setRipadopor(String ripadopor) {
		this.ripadopor = ripadopor;
	}
	public String getSiteOficial() {
		return siteOficial;
	}
	public void setSiteOficial(String siteOficial) {
		this.siteOficial = siteOficial;
	}
	public String getImagemCapa() {
		return imagemCapa;
	}
	public void setImagemCapa(String imagemCapa) {
		this.imagemCapa = imagemCapa;
	}
	public String getSinopsePTBR() {
		return sinopsePTBR;
	}
	public void setSinopsePTBR(String sinopsePTBR) {
		this.sinopsePTBR = sinopsePTBR;
	}
	public String[] getScreenshot() {
		return screenshot;
	}
	public void setScreenshot(String[] screenshot) {
		this.screenshot = screenshot;
	}
	public Qualidade getQualidade() {
		return qualidade;
	}
	public void setQualidade(Qualidade qualidade) {
		this.qualidade = qualidade;
	}
	public Integer getMidia() {
		return midia;
	}
	public void setMidia(Integer midia) {
		this.midia = midia;
	}
	public String getIdiomaAudio() {
		return idiomaAudio;
	}
	public void setIdiomaAudio(String idiomaAudio) {
		this.idiomaAudio = idiomaAudio;
	}
	public String getTamanhoArquivo() {
		return tamanhoArquivo;
	}
	public void setTamanhoArquivo(String tamanhoArquivo) {
		this.tamanhoArquivo = tamanhoArquivo;
	}
	public String getTrailer() {
		return trailer;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getVideoBitrate() {
		return videoBitrate;
	}
	public void setVideoBitrate(String videoBitrate) {
		this.videoBitrate = videoBitrate;
	}
	public String getVideoCodec() {
		return videoCodec;
	}
	public void setVideoCodec(String videoCodec) {
		this.videoCodec = videoCodec;
	}
	public String getAudioBitrate() {
		return audioBitrate;
	}
	public void setAudioBitrate(String audioBitrate) {
		this.audioBitrate = audioBitrate;
	}
	public String getAudioCodec() {
		return audioCodec;
	}
	public void setAudioCodec(String audioCodec) {
		this.audioCodec = audioCodec;
	}
	public String getFrameRate() {
		return frameRate;
	}
	public void setFrameRate(String frameRate) {
		this.frameRate = frameRate;
	}
	public String getResolucaoVideo() {
		return resolucaoVideo;
	}
	public void setResolucaoVideo(String resolucaoVideo) {
		this.resolucaoVideo = resolucaoVideo;
	}
	public Resolucao getFormatoTela() {
		return formatoTela;
	}
	public void setFormatoTela(Resolucao formatoTela) {
		this.formatoTela = formatoTela;
	}
	public Legendas getLegendas() {
		return legendas;
	}
	public void setLegendas(Legendas legendas) {
		this.legendas = legendas;
	}
	public String getOutrasInfo() {
		return outrasInfo;
	}
	public void setOutrasInfo(String outrasInfo) {
		this.outrasInfo = outrasInfo;
	}
	public ExibicaoCompartilhamento getExibicaoCompartilhamento() {
		return exibicaoCompartilhamento;
	}
	public void setExibicaoCompartilhamento(ExibicaoCompartilhamento exibicaoCompartilhamento) {
		this.exibicaoCompartilhamento = exibicaoCompartilhamento;
	}
	public CompartilhamentoTipo getCompartilhamentoTipo() {
		return compartilhamentoTipo;
	}
	public void setCompartilhamentoTipo(CompartilhamentoTipo compartilhamentoTipo) {
		this.compartilhamentoTipo = compartilhamentoTipo;
	}
	public String getCompartilhamento() {
		return compartilhamento;
	}
	public void setCompartilhamento(String compartilhamento) {
		this.compartilhamento = compartilhamento;
	}
	public boolean isTemSenha() {
		return temSenha;
	}
	public void setTemSenha(boolean temSenha) {
		this.temSenha = temSenha;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getBorda() {
		return borda;
	}
	public void setBorda(String borda) {
		this.borda = borda;
	}
}

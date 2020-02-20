package br.com.geradorOkaeri.model;

import java.io.Serializable;

/**
 * Classe que representa a resolução do vídeo no {@link Post}
 * @author andre
 *
 */
public class Resolucao implements Serializable {
	private static final long serialVersionUID = 6803389739336506673L;
	
	private Integer width;
	private Integer height;
	
	
	/**
	 * Metodo usado para criar uma {@link Resolucao};
	 * @param width - {@link Integer} largura do video.
	 * @param height - {@link Integer} altura do video.
	 * @return {@link Resolucao}
	 */
	public static Resolucao of(Integer width, Integer height) {
		Resolucao re = new Resolucao();
		re.width = width;
		re.height = height;
		return re;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result + ((width == null) ? 0 : width.hashCode());
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
		if (!(obj instanceof Resolucao)) {
			return false;
		}
		Resolucao other = (Resolucao) obj;
		if (height == null) {
			if (other.height != null) {
				return false;
			}
		} else if (!height.equals(other.height)) {
			return false;
		}
		if (width == null) {
			if (other.width != null) {
				return false;
			}
		} else if (!width.equals(other.width)) {
			return false;
		}
		return true;
	}



	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Resolucao [width=" + width.intValue() + ", height=" + height.intValue() + "]";
	}
	
	/**
	 * <p>Metodo que retorna a resolucano no formato <code>largura X altura</code></p>
	 * @return - {@link String} no formato <code>largura X altura</code>
	 */
	public String formated() {
		return width.intValue() + " X " + height.intValue();
	}	
}

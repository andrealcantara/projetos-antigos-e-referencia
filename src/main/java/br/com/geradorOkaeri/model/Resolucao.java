package br.com.geradorOkaeri.model;

import java.io.Serializable;

public class Resolucao implements Serializable {
	private static final long serialVersionUID = 6803389739336506673L;
	
	private int width;
	private int height;
	
	
	public static Resolucao of(int width, int height) {
		Resolucao re = new Resolucao();
		re.width = width;
		re.height = height;
		return re;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + width;
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
		if (height != other.height) {
			return false;
		}
		if (width != other.width) {
			return false;
		}
		return true;
	}

	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Resolucao [width=" + width + ", height=" + height + "]";
	}
	
	public String formated() {
		return width+" X "+height;
	}	
}

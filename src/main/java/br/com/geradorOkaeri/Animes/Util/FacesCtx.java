package br.com.geradorOkaeri.Animes.Util;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class FacesCtx implements Serializable{
	private static final long serialVersionUID = -4646872984720507421L;
	
	@Produces
	public FacesContext getFacesContext(){
		return FacesContext.getCurrentInstance();
	}
}

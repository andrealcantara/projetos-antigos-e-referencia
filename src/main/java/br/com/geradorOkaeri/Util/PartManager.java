package br.com.geradorOkaeri.Util;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class PartManager implements Serializable {
	private static final long serialVersionUID = -2039884079555879531L;
	
	public static Collection<ImagePart> getAllParts(Part part) throws Exception {
	    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    return request.getParts().stream().filter(p -> part.getName().equals(p.getName())).map(ImagePart::of).collect(Collectors.toList());
	}
	
	
}

package br.com.geradorOkaeri.uploadImg;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.cloudinary.Cloudinary;

import br.com.geradorOkaeri.Util.ImagePart;
import br.com.geradorOkaeri.Util.PartManager;

@Named
@RequestScoped
public class UploadLocal implements Serializable {
	private static final long serialVersionUID = -1710669536236901403L;
	
	private Part fileh;
	
	@Inject
	private Cloudinary cloudImg;

	public void save() throws Exception{
		for(ImagePart osso : PartManager.getAllParts(fileh)){
			String fileName = osso.getName();
			String contentType = osso.getContentType();
			long size = osso.getSize();
			System.out.println(fileName + " cntType:" + contentType + " size:" + size + ", obj:" + osso.getInput());
			osso.finallize();
		}
	}
	
	public Part getFileh() {
		return fileh;
	}

	public void setFileh(Part fileh) throws Exception {
		this.fileh = fileh;
		save();
	}
	
	
	public static Collection<Part> getAllParts(Part part) throws Exception {
	    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    return request.getParts().stream().filter(p -> part.getName().equals(p.getName())).collect(Collectors.toList());
	}
	
}

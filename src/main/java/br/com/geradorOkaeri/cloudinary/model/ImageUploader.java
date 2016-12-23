package br.com.geradorOkaeri.cloudinary.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.cloudinary.Cloudinary;
import com.cloudinary.StoredFile;
import com.cloudinary.Transformation;

import br.com.geradorOkaeri.Util.Produtor;

public class ImageUploader extends StoredFile implements Serializable {
	private static final long serialVersionUID = -2039884079555879531L;
		
	private String title;
	
	private Image image;

	private Cloudinary cloud;
	
	public ImageUploader(){}
	
	private static ImageUploader ofImage(Image image){
		ImageUploader iu = new ImageUploader();
		iu.image = image;
		return iu;
	}
	
    public String getUrl() {
        if (version != null && format != null && publicId != null) {
            return cloud.url()
                    .resourceType(resourceType)
                    .type(type)
                    .format(format)
                    .version(version)
                    .generate(publicId);
        }
        return null;
    }

    public String getThumbnailUrl() {
        if (version != null && format != null && publicId != null) {
            return cloud.url().format(format)
                    .resourceType(resourceType)
                    .type(type)
                    .version(version).transformation(new Transformation().width(150).height(150).crop("fit"))
                    .generate(publicId);
        }
        return null;
    }

    public Cloudinary getCloud() {
		return cloud;
	}

	public void setCloud(Cloudinary cloud) {
		this.cloud = cloud;
	}

	public String getComputedSignature() {
        return getComputedSignature(cloud);
    }

    public boolean validSignature() {
        return getComputedSignature().equals(signature);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public static List<ImageUploader> getAllParts(Part part) {
		List<ImageUploader> retorno = null;
		try{
			HttpServletRequest request = (HttpServletRequest) Produtor.staticClassCDI(FacesContext.class).getExternalContext().getRequest();
		    retorno = request.getParts().stream().filter(p -> part.getName().equals(p.getName())).map(Image::of).map(ImageUploader::ofImage).collect(Collectors.toList());
		}catch(IOException | ServletException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return retorno;
	}
}

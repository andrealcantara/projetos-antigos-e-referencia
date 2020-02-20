package br.com.geradorOkaeri.cloudinary.model;

import java.io.Serializable;

import com.cloudinary.Cloudinary;
import com.cloudinary.StoredFile;
import com.cloudinary.Transformation;

public class ImageUploader extends StoredFile implements Serializable {
	private static final long serialVersionUID = -2039884079555879531L;
		
	private String title;
	
	private Image image;

	private Cloudinary cloud;
	
	public ImageUploader(){}
	
	public static ImageUploader ofImage(Image image){
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
}

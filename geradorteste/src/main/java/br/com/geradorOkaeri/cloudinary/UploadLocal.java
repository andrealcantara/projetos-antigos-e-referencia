package br.com.geradorOkaeri.cloudinary;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.geradorOkaeri.cloudinary.control.ImageUploadControl;
import br.com.geradorOkaeri.cloudinary.model.Image;
import br.com.geradorOkaeri.cloudinary.model.ImageUploader;

@Named
@ViewScoped
public class UploadLocal implements Serializable {
	private static final long serialVersionUID = -1710669536236901403L;

	@Inject
	private ImageUploadControl iuc;
	
	@PostConstruct
	public void init(){
	}

	public void uploader(FileUploadEvent upload) throws InterruptedException {
		UploadedFile up = upload.getFile();
		this.save(ImageUploader.ofImage(Image.ofUploadedFile(up)));
	}

	public void saves(List<ImageUploader> images) throws InterruptedException {
		try {
			iuc.saves(images);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void save(ImageUploader images) throws InterruptedException {
		try {
			iuc.save(images);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}

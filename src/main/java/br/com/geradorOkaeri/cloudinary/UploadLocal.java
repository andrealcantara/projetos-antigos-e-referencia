package br.com.geradorOkaeri.cloudinary;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.geradorOkaeri.cloudinary.control.ImageUploadControl;
import br.com.geradorOkaeri.cloudinary.model.ImageUploader;

@Named
@ViewScoped
public class UploadLocal implements Serializable {
	private static final long serialVersionUID = -1710669536236901403L;

	// private Part part;
	private String treta;

	int count = 0;

	@Inject
	private ImageUploadControl iuc;

	public void uploader(FileUploadEvent upload) {
		UploadedFile up = upload.getFile();
		System.out.println(up +" -> " + this.toString());
	}

	public void save(List<ImageUploader> images) throws InterruptedException {
		try {
			iuc.save(images);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		System.out.println(++count + " -> " + this.toString() + " -> " + "terminou carai 2");
		treta = String.join(", ", images.stream().map(i -> i.getUrl()).collect(Collectors.toList()));
	}

	public String getTreta() {
		return treta;
	}

	public void setTreta(String treta) {
		this.treta = treta;
	}

}

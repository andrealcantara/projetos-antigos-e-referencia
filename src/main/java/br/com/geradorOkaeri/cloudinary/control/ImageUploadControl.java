package br.com.geradorOkaeri.cloudinary.control;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import br.com.geradorOkaeri.cloudinary.model.Image;
import br.com.geradorOkaeri.cloudinary.model.ImageUploader;

public class ImageUploadControl implements Serializable {
	private static final long serialVersionUID = -7042798667363520989L;

	private StringBuffer sb = new StringBuffer();

	@Inject
	private Cloudinary cloudImg;
	
	public void save(List<ImageUploader> imageUpload) throws Exception {
		imageUpload.stream().forEach(this::processImage);
		System.out.println(sb.toString());
	}

	private void processImage(ImageUploader imageUploader) {
		try {
			Image image = imageUploader.getImage();
			testeImage(image);
			Map<?,?> uploadResult = null;
			if (image.getInput() != null && image.getInput().length > 0) {
				uploadResult = cloudImg.uploader().upload(image.getInput(),
						ObjectUtils.asMap("async", "true", "resource_type", "auto", "tags", "imagemAleatoria, teste2"));
				sb.append(uploadResult+"\n");
				loadImageUploader(imageUploader, uploadResult);
			}
			image.setName(imageUploader.getTitle());
			sb.append(image+"\n");
			sb.append(imageUploader+"\n");
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private void loadImageUploader(ImageUploader imageUploader, Map<?,?> uploadResult) {
		imageUploader.setPublicId((String) uploadResult.get("public_id"));
		Object version = uploadResult.get("version");
		if (version instanceof Integer) {
			imageUploader.setVersion(new Long((Integer) version));
		} else {
			imageUploader.setVersion((Long) version);
		}
		imageUploader.setSignature((String) uploadResult.get("signature"));
		imageUploader.setFormat((String) uploadResult.get("format"));
		imageUploader.setResourceType((String) uploadResult.get("resource_type"));
		imageUploader.setCloud(cloudImg);
	}

	private void testeImage(Image image) {
		String fileName = image.getName();
		String contentType = image.getContentType();
		long size = image.getSize();
		sb.append(fileName + " cntType:" + contentType + " size:" + size + ", obj:" + image.getInput()+"\n");
	}

}

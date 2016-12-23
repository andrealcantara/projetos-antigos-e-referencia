package br.com.geradorOkaeri.cloudinary.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.servlet.http.Part;

public class Image implements Serializable {
	private static final long serialVersionUID = 7936393587890466147L;

	private String name;
	private String contentType;
	private long size;
	private byte[] input;

	public static Image of(Part parte) {
		Image image = null;
		try {
			image = new Image();
			image.name = parte.getSubmittedFileName();
			image.contentType = parte.getContentType();
			image.size = parte.getSize();
			image.input = image.loadImage(parte.getInputStream());
		} catch (IOException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
	
	public byte[] getInput() {
		return input;
	}
	
	public void setInput(byte[] input) {
		this.input = input;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contentType == null) ? 0 : contentType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (size ^ (size >>> 32));
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
		if (!(obj instanceof Image)) {
			return false;
		}
		Image other = (Image) obj;
		if (contentType == null) {
			if (other.contentType != null) {
				return false;
			}
		} else if (!contentType.equals(other.contentType)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (size != other.size) {
			return false;
		}
		return true;
	}

	private byte[] loadImage(InputStream input) throws IOException  {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[2048];
		while ((nRead = input.read(data, 0, data.length)) != -1) {
			baos.write(data, 0, nRead);
		}
		baos.flush();
		return baos.toByteArray();
	}

}

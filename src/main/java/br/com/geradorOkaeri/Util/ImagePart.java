package br.com.geradorOkaeri.Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.servlet.http.Part;

public class ImagePart implements Serializable {
	private static final long serialVersionUID = 7936393587890466147L;

	private String name;
	private String contentType;
	private long size;
	private File input;

	public static ImagePart of(Part parte) {
		ImagePart image = null;
		try {
			image = new ImagePart();
			image.name = parte.getSubmittedFileName();
			image.contentType = parte.getContentType();
			image.size = parte.getSize();
			image.input = new File(image.getTempName());
			image.loadImage(parte.getInputStream());
		} catch (IOException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return image;
	}

	public void finallize() {
		if (this.input.exists() && this.input.canWrite()) {
			this.input.delete();
		}
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
	
	public File getInput() {
		return input;
	}
	
	public void setInput(File input) {
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImagePart other = (ImagePart) obj;
		if (contentType == null) {
			if (other.contentType != null)
				return false;
		} else if (!contentType.equals(other.contentType))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (size != other.size)
			return false;
		return true;
	}

	private String getTempName() {
		return "[" + this.size + "]" + this.name;
	}

	private void loadImage(InputStream input) {
		OutputStream output = null;
		try {
			output = new FileOutputStream(this.input);
			int read = 0;
			byte[] bytes = new byte[2048];
			while ((read = input.read(bytes)) != -1) {
				output.write(bytes, 0, read);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

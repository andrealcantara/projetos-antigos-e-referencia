package br.com.geradorOkaeri.Util;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.CDI;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import br.com.geradorOkaeri.annotation.LocalProperties;

public class Produtor implements Serializable {
	private static final long serialVersionUID = -7175160835224995774L;

	@Produces
	@LocalProperties("MALUser")
	@ApplicationScoped
	public Properties generateMALProperties() throws IOException {
		Properties prop = new Properties();
		prop.load(Produtor.class.getClassLoader().getResourceAsStream("configs/MAL.properties"));
		return prop;
	}

	@Produces
	@LocalProperties("AniDBUser")
	@ApplicationScoped
	public Properties generateAniDBProperties() throws IOException {
		Properties prop = new Properties();
		prop.load(Produtor.class.getClassLoader().getResourceAsStream("configs/AniDBNet.properties"));
		return prop;
	}

	@Produces
	@ApplicationScoped
	public Cloudinary generateCloudinaryProperties() throws IOException {
		Properties prop = new Properties();
		prop.load(Produtor.class.getClassLoader().getResourceAsStream("configs/cloudinary.properties"));
		Cloudinary cloud = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", prop.getProperty("cloud_name"), 
				"api_key", prop.getProperty("cloud_key"),
				"api_secret", prop.getProperty("cloud_secret")));
		return cloud;
	}
	

	public static <T> T staticClassCDI(Class<T> clazz){
		 Instance<T> instance = CDI.current().select(clazz);
		    return instance.get();
	}
}

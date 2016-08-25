package br.com.geradorOkaeri.Animes.AniDB.modal;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;


@XStreamAlias("title")
@XStreamConverter(value=ToAttributedValueConverter.class, strings={"value"})
public class TituloAniDB implements Serializable {
	private static final long serialVersionUID = -8399036590654828600L;

	@XStreamAsAttribute
	@XStreamAlias("xml:lang")
	private String lang;
	
	@XStreamAsAttribute
	private String type;
	
	private String value;

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public TituloAniDB(String lang, String type, String value) {
		super();
		this.lang = lang;
		this.type = type;
		this.value = value;
	}
}

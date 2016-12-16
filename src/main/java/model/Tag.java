package model;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Preconditions;

import model.util.GeneratorIdTag;
import model.util.IGenerator;
import model.util.IModel;
import util.Configuracao;
import util.RegexManipulation;

public class Tag implements IModel<Long>{
	
	private static final long serialVersionUID = 4837117269637110338L;
	private static IGenerator<Tag> genId = GeneratorIdTag.getInstance();
	private static final String REGEX_PATTERN = "^(\\{){2}("+Configuracao.defaultTags+"){1}.*(\\}){2}$";
	private static final String REGEX_PROPERTIES = "(name|type|size|needed)(=){1}(\"[a-zA-Z_-]+\"|\\d+|true|false){1}";
	private Long id;
	private List<TagProperties> properties;

	public static Tag of(List<TagProperties> properties) {
		Tag tag = new Tag();
		tag.properties = properties;
		genId.generateId(tag);
		return tag;
	}
	
	public static Tag of(String pattern) {
		Preconditions.checkArgument(pattern.matches(REGEX_PATTERN));
		List<String> properStr = RegexManipulation.searchAll(REGEX_PROPERTIES, pattern);
		List<TagProperties> properties = properStr.stream().map(TagProperties::of).collect(Collectors.toList());
		return Tag.of(properties);
	}
	
	public List<TagProperties> getProperties() {
		return properties;
	}
	public void setProperties(List<TagProperties> properties) {
		this.properties = properties;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((properties == null) ? 0 : properties.hashCode());
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
		Tag other = (Tag) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (properties == null) {
			if (other.properties != null)
				return false;
		} else if (!properties.equals(other.properties))
			return false;
		return true;
	}
	
}

package model;

import com.google.common.base.Preconditions;

public class TagProperties {
	private String name;
	private Object value;
	private TagPropertiesType type;

	public TagProperties() {
		
	}

	public static TagProperties of(String name, Object value, TagPropertiesType type) {
		TagProperties properTag = new TagProperties();
		properTag.name = name;
		properTag.value = value;
		properTag.type = type;
		return properTag;
	}

	// TODO: refatorar.
	// procurar um padrao de projeto que resolva isso
	// chainOfResponsability pode ser uma opção.
//	public static TagProperties of(String pattern) {
//		Preconditions.checkArgument(pattern.matches(".*=.*"));
//		String[] part = pattern.split("=");
//		String tagName = part[0];
//		TagPropertiesType propertiesType = null;
//		Object value = part[1];
//		switch (tagName) {
//		case "name":
//			propertiesType = TagPropertiesType.String;
//			value = ((String) value).replaceAll("\"", "");
//			break;
//		case "type":
//			propertiesType = TagPropertiesType.String;
//			value = ((String) value).replaceAll("\"", "");
//			break;
//		case "value":
//			propertiesType = TagPropertiesType.String;
//			value = ((String) value).replaceAll("\"", "");
//			break;
//		case "size":
//			propertiesType = TagPropertiesType.Number;
//			value = new Integer(value.toString());
//			break;
//		case "needed":
//			propertiesType = TagPropertiesType.Boolean;
//			value = new Boolean(value.toString());
//			break;
//		default:
//			throw new IllegalArgumentException("Property [" + tagName + "] not supported yet");
//		}
//		return TagProperties.of(tagName, value, propertiesType);
//	}
	
	public static TagProperties of(String pattern) {
		Preconditions.checkArgument(pattern.matches(".*=.*"));
		String[] part = pattern.split("=");
		String tagName = part[0];
		String value = part[1];
		return TagPropertiesCheck.getInstance().check(tagName, value);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public TagPropertiesType getType() {
		return type;
	}

	public void setType(TagPropertiesType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		if (getClass() != obj.getClass()) {
			return false;
		}
		TagProperties other = (TagProperties) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "PropertiesTag [name=" + name + ", value=" + value + ", type=" + type + "]";
	}
}

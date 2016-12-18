package model;

import java.util.function.Function;

public class TagPropertiesCheck{
	
	private Function<String,?> retorno;
	private String name;
	private TagPropertiesType type;
	private TagPropertiesCheck next;
	
	public TagPropertiesCheck(String name, TagPropertiesType type,Function<String, ?> retorno){
		this.name = name;
		this.type = type;
		this.retorno = retorno;
	}
	
	public void addNext(TagPropertiesCheck next){
		if(this.next == null) {
			this.next = next;
		} else {
			this.next.addNext(next);
		}
	}
	
	public TagProperties check(String name, String value) {
		if(this.name.equals(name)){
			return TagProperties.of(this.name, retorno.apply(value), type);
		} else {
			if(this.next == null){
				throw new IllegalArgumentException("Property [" + name + "] not supported yet");
			}
			return this.next.check(name, value);
		}
	}
	
	public static TagPropertiesCheck getInstance(){
		return TagPropertiesCheckHolder.INSTANCE;
	}
	
	
	public Function<String, ?> getRetorno() {
		return retorno;
	}
	public void setRetorno(Function<String, ?> retorno) {
		this.retorno = retorno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TagPropertiesCheck getNext() {
		return next;
	}
	public void setNext(TagPropertiesCheck next) {
		this.next = next;
	}
	
	private static class TagPropertiesCheckHolder{
		public static TagPropertiesCheck INSTANCE;
		static{
			INSTANCE = new TagPropertiesCheck("name", TagPropertiesType.String, s -> s.replaceAll("\"", ""));
			INSTANCE.addNext(new TagPropertiesCheck("type", TagPropertiesType.String, s -> s.replaceAll("\"", "")));
			INSTANCE.addNext(new TagPropertiesCheck("value", TagPropertiesType.String, s -> s.replaceAll("\"", "")));
			INSTANCE.addNext(new TagPropertiesCheck("size", TagPropertiesType.Number, s -> new Integer(s)));
			INSTANCE.addNext(new TagPropertiesCheck("needed", TagPropertiesType.Boolean, s -> new Boolean(s)));
		}
	}
	
}

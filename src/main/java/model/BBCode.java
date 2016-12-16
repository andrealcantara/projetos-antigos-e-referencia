package model;

import com.google.common.base.Preconditions;

import model.util.GeneratorIdBBCode;
import model.util.IGenerator;
import model.util.IModel;
import util.RegexManipulation;

public class BBCode implements IModel<Long>{
	private static final long serialVersionUID = -6913474109726201370L;
	
	private static final String bbcodeIsClose = "^(\\[\\/).*";
	private static IGenerator<BBCode> genId = GeneratorIdBBCode.getInstance();
	
	private Long id;
	
	private String tag;
	private String innerValue; 

	private boolean close;

	public static BBCode of(String source) {
		Preconditions.checkArgument(!source.isEmpty());
		boolean close = source.matches(bbcodeIsClose);
		String tag = RegexManipulation.search("[a-zA-Z2-6]+", source);
		String innerValue = "";
		if(source.contains("=")) {
			innerValue = source.split("=")[1].replace("]", "");
		}
		return BBCode.of(tag, close, innerValue);
	}
	
	public static BBCode of(String tag, boolean close, String innerValue) {
		BBCode retorno = new BBCode();
		retorno.tag = tag;
		retorno.close = close;
		retorno.innerValue = innerValue;
		genId.generateId(retorno);
		return retorno;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		BBCode other = (BBCode) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public boolean isClose() {
		return close;
	}
	
	public void setClose(boolean close) {
		this.close = close;
	}
	
	public String getInnerValue() {
		return innerValue;
	}
	
	public void setInnerValue(String innerValue) {
		this.innerValue = innerValue;
	}
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
}

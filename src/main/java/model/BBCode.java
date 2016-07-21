package model;

import java.io.Serializable;

import com.google.common.base.Preconditions;

public class BBCode implements Serializable{
	private static final long serialVersionUID = -6913474109726201370L;
	
	private String tag;
	private String innerValue;

	private boolean close;
	
	public static BBCode of(String source){
		Preconditions.checkArgument(!source.isEmpty());
		boolean cls = source.matches("");
		
		
		return null;
	}
	public static BBCode of(String tag, boolean close, String innerValue) {
		BBCode retorno = new BBCode();
		retorno.tag = tag;
		retorno.close = close;
		retorno.innerValue = innerValue;
		return retorno;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (close ? 1231 : 1237);
		result = prime * result + ((innerValue == null) ? 0 : innerValue.hashCode());
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
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
		if (close != other.close)
			return false;
		if (innerValue == null) {
			if (other.innerValue != null)
				return false;
		} else if (!innerValue.equals(other.innerValue))
			return false;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
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
	
	

}

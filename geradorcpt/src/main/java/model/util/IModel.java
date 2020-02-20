package model.util;

import java.io.Serializable;

public interface IModel<ID> extends Serializable {
	public ID getId();
	public void setId(ID id);
}

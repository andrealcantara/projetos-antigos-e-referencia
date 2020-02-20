package model.util;

import java.io.Serializable;

public interface IGenerator<D extends IModel<?>> extends Serializable {

	public static IGenerator<?> getInstance() {
		throw new RuntimeException("Illegal implementation. You need Override this methods.");
	}
	public void generateId(D model);
	public Long getCurrentId();
}

package br.com.fastPunchSub.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProdutorSingleton implements Serializable {
	private static final long serialVersionUID = -4765847284744259747L;

	private Map<String, Logger> map;

	private ProdutorSingleton() {
		map = new HashMap<String, Logger>();
	}

	public Logger logger(Class<?> clazz) {
			if(this.map.containsKey(clazz.getName())){
				this.map.put(clazz.getName(), LoggerFactory.getLogger(clazz));
			}
			return this.map.get(clazz.getName());
	}

	public ProdutorSingleton getInstance() {
		return ProdutorSingletonHandler.INSTANCE;
	}

	private static class ProdutorSingletonHandler {
		private static final ProdutorSingleton INSTANCE = new ProdutorSingleton();
	}
}

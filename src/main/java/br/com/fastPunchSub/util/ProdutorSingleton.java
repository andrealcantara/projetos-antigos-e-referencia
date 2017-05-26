package br.com.fastPunchSub.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProdutorSingleton implements Serializable {
	private static final long serialVersionUID = -4765847284744259747L;

	private Map<TypeLoggers, Map<String, Class<?>>> map;

	private ProdutorSingleton() {
		map = new HashMap<TypeLoggers, Map<String, Class<?>>>();
	}

	public Logger logger(TypeLoggers tipos, Class<?> clazz) {
			if(this.map.containsKey(tipos)){
				if(this.map.getOrDefault(tipos, new HashMap<String, Class<?>>()))
			}
	}

	public ProdutorSingleton getInstance() {
		return ProdutorSingletonHandler.INSTANCE;
	}

	private static class ProdutorSingletonHandler {
		private static final ProdutorSingleton INSTANCE = new ProdutorSingleton();
	}

	public enum TypeLoggers {
		INFO, WARN, TRACE, ERROR;
	}

}

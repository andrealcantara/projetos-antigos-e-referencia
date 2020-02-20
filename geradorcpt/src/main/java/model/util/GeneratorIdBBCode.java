package model.util;

import model.BBCode;

public class GeneratorIdBBCode implements IGenerator<BBCode> {
	private static final long serialVersionUID = -3940671244883717367L;

	private Long id = 0L;
	
	public static IGenerator<BBCode> getInstance() {
		return GeneratorIdBBCodeHolder.INSTANCE;
	}

	@Override
	public void generateId(BBCode model) {
		id = id + 1l;
		model.setId(id);
	}

	@Override
	public Long getCurrentId() {
		return id;
	}
	
	private static class GeneratorIdBBCodeHolder{
		public final static GeneratorIdBBCode INSTANCE = new GeneratorIdBBCode();
	}
}

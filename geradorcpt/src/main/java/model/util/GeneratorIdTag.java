package model.util;

import model.Tag;

public class GeneratorIdTag implements IGenerator<Tag> {
	private static final long serialVersionUID = 1599050572649464247L;
	private Long id = 0l;
	
	public static IGenerator<Tag> getInstance() {
		return GeneratorIdTagHolder.INSTANCE;
	}

	@Override
	public void generateId(Tag model) {
		id = id + 1l;
		model.setId(id);
	}

	@Override
	public Long getCurrentId() {
		if(id == 0l) {
			System.out.println("No have anyone id generated");//TODO refactory
			return null;
		}
		return id;
		
	}
	
	private static class GeneratorIdTagHolder{
		public final static GeneratorIdTag INSTANCE = new GeneratorIdTag();
	}
}

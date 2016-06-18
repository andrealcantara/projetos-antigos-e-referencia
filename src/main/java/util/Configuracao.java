package util;

public class Configuracao {
	public static final String groupTAG = "okaeri";
	public static final String tolken = "@";
	public static final String defaultTags = String.format("%s%s", groupTAG,tolken);
	
	private long id;
	
	private Configuracao(){
		this.id = 0;
	}
	
	public static Configuracao getInstnace(){
		return ConfiguracaoHolder.INSTANCE;
	}
	
	public long getId(){
		return id;
	}
	
	public long nextId(){
		return ++id;
	}
	
	public void zerarId(){
		this.id = 0;
	}
	
	private static class ConfiguracaoHolder{
		private static Configuracao INSTANCE = new Configuracao();
	}
}

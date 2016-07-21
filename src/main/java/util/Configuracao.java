package util;

public class Configuracao {
	public static final String groupTAG = "okaeri";
	public static final String tolken = "@";
	public static final String defaultTags = String.format("%s%s", groupTAG,tolken);
	
	private long idTag;
	private long idBBCode;
	
	private Configuracao(){
		this.idTag = 0;
		this.idBBCode = 0;
	}
	
	public static Configuracao getInstnace(){
		return ConfiguracaoHolder.INSTANCE;
	}
	
	public long getIdTag(){
		return idTag;
	}
	
	public long nextIdTag(){
		return ++idTag;
	}
	
	public void zerarIdTag(){
		this.idTag = 0;
	}
	
	public long getIdBBCode(){
		return idBBCode;
	}
	
	public long nextIdBBCode(){
		return ++idBBCode;
	}
	
	public void zerarIdBBCode(){
		this.idBBCode = 0;
	}
	
	
	private static class ConfiguracaoHolder{
		private static Configuracao INSTANCE = new Configuracao();
	}
}

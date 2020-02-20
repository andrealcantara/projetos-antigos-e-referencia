package util;

public class Configuracao {
	public static final String groupTAG = "okaeri";
	public static final String tolken = "@";
	public static final String defaultTags = String.format("%s%s", groupTAG,tolken);
	
	public static Configuracao getInstnace(){
		return ConfiguracaoHolder.INSTANCE;
	}
	
	private static class ConfiguracaoHolder{
		private static Configuracao INSTANCE = new Configuracao();
	}
}

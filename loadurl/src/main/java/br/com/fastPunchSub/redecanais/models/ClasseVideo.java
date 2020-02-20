package br.com.fastPunchSub.redecanais.models;

public enum ClasseVideo {

	SERIADO("http://www.redecanais.com/player3/server{0}rcgdrive.php?vid="),
	ANIME("http://www.redecanais.com/player3/server{0}rcgdrive.php?vid="),
	CANAL("http://www.redecanais.com/player3/servercanal.php?canal="),
	FILME("");
	
	private URLServerList URL;

	private ClasseVideo(String url) {
		this.URL = new URLServerList(url);
	}

	public String getURL() {
		return this.URL.getURL();
	}
	
	public String getNextURL() {
		return this.URL.nextServerURL();
	}
	
	public int getURLServer() {
		return this.URL.getIdServer();
	}
}

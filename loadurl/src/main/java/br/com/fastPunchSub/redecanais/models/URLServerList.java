package br.com.fastPunchSub.redecanais.models;

import java.io.Serializable;
import java.text.MessageFormat;

public class URLServerList implements Serializable{
	private static final long serialVersionUID = -8760638713743283506L;

	private static final int numServer = 9;
	
	private String URL;
	private int idServer;
	
	public URLServerList(String url){
		this.URL = url;
		idServer = 1;
	}
	
	public String getURL(){
		return getURLServer(idServer);
	}
	
	public int getIdServer() {
		return idServer;
	}
	
	public String nextServerURL(){
		++idServer;
		idServer = (idServer % URLServerList.numServer) + 1;
		return getURL();
	}
	
	public String getURLServer(int id){
		return MessageFormat.format(URL, id);
	}
	
	public static void main(String[] args) {
		for(int i = 0, j = 0; i < 18 ; i++, j++ ){
			System.out.println(MessageFormat.format("J[{0}]-> {1}",Integer.toString(j),Integer.toString((j % 5) + 1)));
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((URL == null) ? 0 : URL.hashCode());
		result = prime * result + idServer;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof URLServerList)) {
			return false;
		}
		URLServerList other = (URLServerList) obj;
		if (URL == null) {
			if (other.URL != null) {
				return false;
			}
		} else if (!URL.equals(other.URL)) {
			return false;
		}
		if (idServer != other.idServer) {
			return false;
		}
		return true;
	}


	
	
}

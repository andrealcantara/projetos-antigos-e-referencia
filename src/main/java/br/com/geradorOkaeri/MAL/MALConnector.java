package br.com.geradorOkaeri.MAL;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;

import org.apache.commons.codec.binary.Base64;

import com.thoughtworks.xstream.XStream;

import br.com.geradorOkaeri.MAL.modal.AnimeMAL;
import br.com.geradorOkaeri.Util.RESTAcess;
import br.com.geradorOkaeri.annotation.LocalProperties;
 
@ApplicationScoped
public class MALConnector implements Serializable, RESTAcess{
	private static final long serialVersionUID = -4860690404329496751L;
	private final String baseUrl;
    private final String username;
    private final String password;
 
    public MALConnector(String username, String password) {
        this.baseUrl = "http://myanimelist.net/api";
        this.username = username;
        this.password = password;
    }
    
    public MALConnector(@LocalProperties("MALUser") Properties prop) {
        this.baseUrl = "http://myanimelist.net/api";
        this.username = prop.getProperty("username");
        this.password = prop.getProperty("password");
    }
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<AnimeMAL> getAnimesMAL(Integer tipoPesqusia, String animeQuery){
    	XStream xstream = new XStream();
		xstream.autodetectAnnotations(true);
		xstream.alias("anime", ArrayList.class);
		xstream.alias("entry", AnimeMAL.class);
		String xml = this.getRESTResponse(1,"fullmetal alchemist").toString();
		List<AnimeMAL> lista = null;
		if(xml.isEmpty()){
			lista = new ArrayList<>();
		} else {
			lista = (ArrayList)xstream.fromXML(xml);
		}
		return lista;
    }
 
    public StringBuilder getRESTResponse(Integer tipoPesquisa, String animeQuery){
    	String path = tipoPesquisa.intValue() == 1? "anime":"manga";
    	StringBuilder sb = new StringBuilder();
    	sb.append(baseUrl);
    	sb.append("/");
    	sb.append(path);
    	sb.append("/search.xml?q=");
    	try {
			sb.append(URLEncoder.encode(animeQuery,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
    	return getDataFromServer(sb.toString(), this::setUsernamePassword, null);
    }

    private URLConnection setUsernamePassword(URLConnection urlConnection) {
        String authString = username + ":" + password;
        String authStringEnc = new String(Base64.encodeBase64(authString.getBytes()));
        urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
        return urlConnection;
    }
}
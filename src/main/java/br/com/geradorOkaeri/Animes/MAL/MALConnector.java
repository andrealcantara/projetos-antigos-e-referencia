package br.com.geradorOkaeri.Animes.MAL;
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

import br.com.geradorOkaeri.Animes.MAL.modal.AnimeMAL;
import br.com.geradorOkaeri.Animes.Util.RESTAcess;
import br.com.geradorOkaeri.annotation.LocalProperties;
 
/**
 * Classe que representa o Conector com o My Anime List.
 * @author andre
 *
 */
@ApplicationScoped
public class MALConnector implements Serializable, RESTAcess{
	private static final long serialVersionUID = -4860690404329496751L;
	private final String baseUrl;
    private final String username;
    private final String password;
 
    /**
     * Construtor de {@link MALConnector}
     * @param username - {@link String} username do login
     * @param password - {@link String} password do login
     */
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
    
    
    /**
     * Metodo que consulta o anime
     * @param tipoPesqusia - {@link String} tipo de pesquisa.
     * @param animeQuery - {@link String} nome do anime.
     * @return Retorna uma {@link List} de {@link AnimeMAL} com os resultados da pesquisa.
     */
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
 
    /**
     * Metodo que faz a requisição a url e retorna o xml da resposta.
     * @param tipoPesquisa - Integer sobre o tipo de pesquisa.
     * @param animeQuery - o nome do anime pesquisado.
     * @return {@link StringBuilder} com o xml da resposta.
     */
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

    /**
     * Metodo que encripta o login e senha.
     * @param urlConnection - {@link URLConnection} para encripta e adicionar no pacote.
     * @return {@link URLConnection} com o <code>username</code> e <code>password</code> como propriedade.
     */
    private URLConnection setUsernamePassword(URLConnection urlConnection) {
        String authString = username + ":" + password;
        String authStringEnc = new String(Base64.encodeBase64(authString.getBytes()));
        urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
        return urlConnection;
    }
}
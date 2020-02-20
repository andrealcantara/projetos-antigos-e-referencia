package br.com.geradorOkaeri.Animes.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Interface que representa classes que fazem Rest Acess.
 * @author andre
 *
 */
public interface RESTAcess {
	
	
	/**
	 * Metodo que abre uma conexao e ler as informacoes do endpoint.
	 * <br />
	 * Igual ao {@link #getDataFromServer(String, Consumer)} com o {@link Consumer} null;
	 * @param path - Caminho da url.
	 * @return {@link StringBuilder} com o xml/JSON de resposta.
	 */
	default StringBuilder getDataFromServer(String path) {
		return this.getDataFromServer(path,null, null);
	}
	
	/**
	 * Metodo que abre uma conexao e ler as informacoes do endpoint.
	 * @param path - Caminho da url.
	 * @param connection - Caso necessite passar informações por post ou null para nao fazer nada.
	 * @param convertStream - Caso necessite converter as informacoes contida no fluxo.
	 * @return {@link StringBuilder} com o xml/JSON de resposta.
	 */
	default StringBuilder getDataFromServer(String path, Consumer<URLConnection> connection, Function<InputStream, InputStream> convertStream){
		StringBuilder sb = new StringBuilder();
		
        try {
            URL url = new URL(path);
            URLConnection urlConnection = url.openConnection();
            if(connection != null) {
            	connection.accept(urlConnection);
            }
            InputStream urlIS = urlConnection.getInputStream();
            if(convertStream != null){
            	urlIS = convertStream.apply(urlIS);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlIS));
            String line;
            while ((line = reader.readLine()) != null) {
            	String aux = new String(line.getBytes("UTF-8"));
                sb.append(aux + "\n");
            }
            reader.close();
            
            return sb;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}
}
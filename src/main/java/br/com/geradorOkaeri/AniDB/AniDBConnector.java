package br.com.geradorOkaeri.AniDB;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.zip.GZIPInputStream;

import br.com.geradorOkaeri.Util.RESTAcess;

public class AniDBConnector implements Serializable, RESTAcess {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3339389641189390226L;
	private static final String BASE_URL = "http://api.anidb.net:9001/httpapi?protover=1&request=anime";
	private static final String CLIENT = "andrealcantara";
	private static final String CLIENTE_VER = "1";
	
	
	public static void main(String[] args) throws FileNotFoundException {
		AniDBConnector teste = new AniDBConnector();
		StringBuilder sb = teste.getAnimeInformation("239");
		System.out.println(sb.toString());
		PrintWriter pw = new PrintWriter(new File("/home/andrealcantara/Downloads/xmlTexte.xml"));
		pw.write(sb.toString());
		pw.flush();
		pw.close();
		
	}
	
	public StringBuilder getAnimeInformation(String id){
		StringBuilder path = new StringBuilder();
		path.append(BASE_URL);
		path.append("&client=");
		path.append(CLIENT);
		path.append("&clientver=");
		path.append(CLIENTE_VER);
		path.append("&aid=");
	  	try {
			path.append(URLEncoder.encode(id,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return this.getDataFromServer(path.toString(),null,this::decompressStream);
	}
	
	private InputStream decompressStream(InputStream streamGZIP){
		InputStream retorno = null;
		try {
            GZIPInputStream gzipInput = new GZIPInputStream(streamGZIP);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bytes_read = 0;
            byte[] dataBuf = new byte[4096];
            while ((bytes_read = gzipInput.read(dataBuf)) != -1) {
                baos.write(dataBuf, 0, bytes_read);
            }
            retorno = new ByteArrayInputStream(baos.toByteArray());
            baos.close();            
        }catch(IOException e){
        	retorno = null;
        }
		return retorno;
	}
	
	
	
	
	
}

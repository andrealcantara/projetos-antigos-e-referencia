package br.com.fastPunchSub.loadUrl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.event.Level;

import com.google.common.base.Preconditions;

import br.com.fastPunchSub.util.LevelLogger;

public class HttpUrlSearch {

	private List<String> cookies;

	private final String USER_AGENT = "Mozilla/5.0";

	public void sendPost(String url, String postParams) throws IOException {
		sendPost(url, postParams, null);
	}

	public StringBuffer sendPost(String url, String postParams, Map<String, String> maps) throws IOException {

		URL obj = new URL(url);
		HttpsURLConnection http = (HttpsURLConnection) obj.openConnection();
		http.setRequestMethod("POST");
		this.addHeadersRequest(http, postParams.length(), true);
		this.addHeadersCookies(http, this.cookies);
		this.addMapRequestPropery(http, maps);

		DataOutputStream wr = new DataOutputStream(http.getOutputStream());
		wr.writeBytes(postParams);
		wr.flush();
		wr.close();

		int responseCode = http.getResponseCode();
		LevelLogger.log(Level.INFO, "\nSending 'POST' request to URL : " + url);
		LevelLogger.log(Level.INFO, "Post parameters : " + postParams);
		LevelLogger.log(Level.INFO, "Response Code : " + responseCode);
		setCookies(http.getHeaderFields().get("Set-Cookie"));

		BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response;
	}

	private void addHeadersCookies(URLConnection conn, List<String> cookies) {
		Preconditions.checkNotNull(cookies);
		Preconditions.checkArgument(!cookies.isEmpty());
		cookies.forEach(x -> conn.addRequestProperty("Cookie", x.split(";", 1)[0]));
	}

	private void addHeadersRequest(URLConnection conn) {
		this.addHeadersRequest(conn, 0, false);
	}

	private void addHeadersRequest(URLConnection conn, int paramSize, boolean isPost) {
		conn.setUseCaches(false);
		conn.setRequestProperty("User-Agent", USER_AGENT);
		conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		if (isPost) {
			conn.setRequestProperty("Connection", "keep-alive");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", Integer.toString(paramSize));
			conn.setDoOutput(true);
			conn.setDoInput(true);
		}
	}

	private void addMapRequestPropery(URLConnection conn, Map<String, String> maps) {
		maps.forEach(conn::setRequestProperty);
	}

	public StringBuffer getPageContent(String url) throws IOException {

		URL obj = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
		this.addHeadersRequest(conn);
		conn.setRequestMethod("GET");
		
		int responseCode = conn.getResponseCode();
		LevelLogger.log(Level.INFO, "\nSending 'GET' request to URL : " + url);
		LevelLogger.log(Level.INFO, "Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		setCookies(conn.getHeaderFields().get("Set-Cookie"));
		return response;

	}

	public int getResponseCodeByURL(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
		this.addHeadersRequest(conn);
		conn.setRequestMethod("GET");
		return conn.getResponseCode();
	}

	/*
	 * Ajustar metodo para ficar generico.
	 */
	@SuppressWarnings("unused")
	private String getFormParams(String html, String username, String password) throws UnsupportedEncodingException {

		System.out.println("Extracting form's data...");

		Document doc = Jsoup.parse(html);

		Element loginform = doc.getElementById("gaia_loginform");
		Elements inputElements = loginform.getElementsByTag("input");
		List<String> paramList = new ArrayList<String>();
		for (Element inputElement : inputElements) {
			String key = inputElement.attr("name");
			String value = inputElement.attr("value");

			if (key.equals("Email"))
				value = username;
			else if (key.equals("Passwd"))
				value = password;
			paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
		}

		// build parameters list
		StringBuilder result = new StringBuilder();
		for (String param : paramList) {
			if (result.length() == 0) {
				result.append(param);
			} else {
				result.append("&" + param);
			}
		}
		return result.toString();
	}
	
	
	public static ScriptEngine getEngineNashor(){
		return new ScriptEngineManager().getEngineByName("nashorn");
	}

	public List<String> getCookies() {
		return cookies;
	}

	public void setCookies(List<String> cookies) {
		this.cookies = cookies;
	}
}
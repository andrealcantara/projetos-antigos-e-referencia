package br.com.fastPunchSub.loadUrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.fastPunchSub.jsonRead.JSONArray;
import br.com.fastPunchSub.jsonRead.JSONFile;
import br.com.fastPunchSub.jsonRead.JSONObject;
import br.com.fastPunchSub.messageLoader.MessageLoader;
import br.com.fastPunchSub.messageLoader.MessageLoader.MessageType;

public class Loader {
	private static final String lineseparator = System.lineSeparator();
	private static final String urlPunch = "https://punchsub.zlx.com.br/";
	private static final String urlPunchScreenShot = urlPunch + "imagens/projetos/screens/%s_%s.jpg";
	private static final String urlPunchDownloadStream = urlPunch + "download-stream/";
	private static final String urlImagePost = "";
	private static final String categoria = "";

	// https://punchsub.zlx.com.br/listar/8/episodios/mp4
	// https://punchsub.zlx.com.br/listar/694/episodios/mp4
	public void main() throws Exception {
		JSONObject mapsRoot = JSONFile.parseFromString(
				this.getSourceCodePage("https://punchsub.zlx.com.br/listar/694/episodios/mp4").toString());
		JSONArray downs = mapsRoot.getArray("e");
		Map<String, JSONArray> maps = downs.stream().collect(
				Collectors.toMap(x -> new JSONArray((Object[]) x).get(0).toString(), x -> new JSONArray((Object[]) x)));
		maps.forEach((x, y) -> {
			System.out.println("Key -> " + x + ". Value -> " + y);
		});

	}

	/*
	 * Ajustar ainda
	 */
	private String getDirectUrlPunchSub(String end) {
		String lineSearch = "";
		StringBuilder allPage = getSourceCodePage(end);
		lineSearch = Stream.of(allPage.toString().split(Loader.lineseparator)).filter(x -> x.contains("var torrentId =")).findFirst()
				.orElseThrow(() -> new RuntimeException(MessageLoader.getBundle(MessageType.ERROR).get("NOT_ITEM")));
		lineSearch = lineSearch.split("\\?")[1].split("\\&")[7].trim();
		lineSearch = lineSearch.substring(3, lineSearch.length());

		return lineSearch;
	}

	private StringBuilder getSourceCodePage(String end) {
		StringBuilder allPage = null;
		try {
			URL urlPage = new URL(end);
			URLConnection urlConn = urlPage.openConnection();
			urlConn.addRequestProperty(MessageLoader.getDefaultInstance().get("useragent.name"),
					MessageLoader.getDefaultInstance().get("useragent.value"));
			urlConn.setConnectTimeout(100000);
			BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(),
					MessageLoader.getDefaultInstance().get("contenttype")));
			String inputLine;
			allPage = new StringBuilder();
			while ((inputLine = br.readLine()) != null)
				allPage.append(inputLine + Loader.lineseparator);
			br.close();
		} catch (SocketTimeoutException ex) {
			throw new RuntimeException(MessageLoader.getBundle(MessageType.ERROR).get("TIME_OUT_ERROR"), ex);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		return allPage;
	}
	
}

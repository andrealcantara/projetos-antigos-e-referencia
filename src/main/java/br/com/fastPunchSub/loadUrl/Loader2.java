package br.com.fastPunchSub.loadUrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import br.com.fastPunchSub.jsonRead.JSONArray;
import br.com.fastPunchSub.jsonRead.JSONFile;
import br.com.fastPunchSub.jsonRead.JSONObject;
import br.com.fastPunchSub.messageLoader.MessageLoader;
import br.com.fastPunchSub.messageLoader.MessageLoader.MessageType;

public class Loader2 {
	private static final String lineseparator = System.lineSeparator();
	private static final String finalArchiveVideoPunch = ".mp4";
	private static final String initArchiveVideoPunch = "ws=";
	private static final String urlPunch = "https://punchsub.zlx.com.br/";
	private static final String urlPunchScreenShot = urlPunch + "imagens/projetos/screens/%s_%s.jpg";
	private static final String urlPunchDownloadStream = urlPunch + "download-stream/";
	private static final String urlImagePost = "";
	private static final String categoria = "";

	// https://punchsub.zlx.com.br/listar/8/episodios/mp4
	// https://punchsub.zlx.com.br/listar/694/episodios/mp4
	public static void main(String[] args) throws Exception {
		HttpUrlSearch http = new HttpUrlSearch();
		JSONObject mapsRoot = JSONFile.parseFromString(
				http.getPageContent("https://punchsub.zlx.com.br/listar/694/episodios/mp4").toString());
		JSONArray downs = mapsRoot.getArray("e");
		Map<String, JSONArray> maps = downs.stream().collect(
				Collectors.toMap(x -> new JSONArray((Object[]) x).get(0).toString(), x -> new JSONArray((Object[]) x)));
		maps.forEach((x, y) -> {
			System.out.println("Key -> " + x + ". Value -> " + y);
		});
	}

	/*
	 * Agora ta melhor
	 */
	private String getDirectUrlPunchSub(String end) throws IOException, ScriptException {
		String lineSearch = "";
		StringBuffer sb = null;
		HttpUrlSearch http = new HttpUrlSearch();
		sb = http.getPageContent(end);
		Document doc = Jsoup.parse(sb.toString());
		Element scpt = doc.select("body > script:nth-child(8)").first();
		ScriptEngine nashor = HttpUrlSearch.getEngineNashor();
		nashor.eval(scpt.data());
		String varTorrentId = (String) nashor.get("torrentId");
		lineSearch = Stream.of(varTorrentId.split("\\&")).filter(x -> x.startsWith(initArchiveVideoPunch)).map(this::ajustURLPunch)
				.filter(url -> this.verifyReponse200URL(http, url)).findFirst()
				.orElseThrow(() -> new RuntimeException("Todos os links estão quebrados. Favor reportar."));
		return lineSearch;
	}

	private boolean verifyReponse200URL(HttpUrlSearch http, String url) {
		try {
			return http.getResponseCodeByURL(url) == 200;
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	private String ajustURLPunch(String url) {
		String path = url.substring(initArchiveVideoPunch.length());
		if (!path.endsWith(finalArchiveVideoPunch)) {
			int idxOf = path.lastIndexOf(finalArchiveVideoPunch);
			path = path.substring(0, idxOf + finalArchiveVideoPunch.length());
		}
		return path;
	}
}

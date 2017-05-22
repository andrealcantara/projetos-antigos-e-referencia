 package loadUrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jsonRead.JSONArray;
import jsonRead.JSONFile;
import jsonRead.JSONObject;

public class Main {
	private static final String urlPunch = "https://punchsub.zlx.com.br/";
	private static final String urlPunchScreenShot = urlPunch + "imagens/projetos/screens/%s_%s.jpg";
	private static final String urlPunchDownloadStream = urlPunch + "download-stream/";
	private static final String urlImagePost = "";
	private static final String categoria = "";

	// https://punchsub.zlx.com.br/listar/8/episodios/mp4
	// https://punchsub.zlx.com.br/listar/694/episodios/mp4
	public static void main(String[] args) throws Exception {
		JSONObject mapsRoot = JSONFile.parseFromString(
				Main.getSourceCodePage("https://punchsub.zlx.com.br/listar/694/episodios/mp4").toString());
		JSONArray downs = mapsRoot.getArray("e");
		Map<String, JSONArray> maps = downs.stream().collect(
				Collectors.toMap(x -> new JSONArray((Object[]) x).get(0).toString(), x -> new JSONArray((Object[]) x)));
		maps.forEach((x,y)->{
			System.out.println("Key -> " + x + ". Value -> " + y);
		});

	}

	/*
	 * Ajustar ainda
	 */
	private static String getDirectUrlPunchSub(String end) {
		String lineSearch = "";
		StringBuilder allPage = getSourceCodePage(end);
		lineSearch = Stream.of(allPage.toString().split("\\n")).filter(x -> x.contains("var torrentId =")).findFirst()
				.orElseThrow(() -> new RuntimeException("nenhum Item encontrado"));
		lineSearch = lineSearch.split("\\?")[1].split("\\&")[7].trim();
		lineSearch = lineSearch.substring(3, lineSearch.length());

		return lineSearch;
	}

	private static StringBuilder getSourceCodePage(String end) {
		StringBuilder allPage = null;
		try {
			URL urlPage = new URL(end);
			URLConnection urlConn = urlPage.openConnection();
			urlConn.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
			urlConn.setConnectTimeout(100000);
			BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
			String inputLine;
			allPage = new StringBuilder();
			while ((inputLine = br.readLine()) != null)
				allPage.append(inputLine + "\n");
			br.close();
		} catch (SocketTimeoutException ex) {
			throw new RuntimeException("Tempo de conexao esgotado, verifique sua internet ou o servidor destino", ex);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		return allPage;
	}
	/*
	 * @SuppressWarnings({ "unused", "rawtypes" }) private static String
	 * getTesteJsonWithoutDependences() throws ScriptException {
	 * ScriptEngineManager manEngine = new ScriptEngineManager(); ScriptEngine
	 * engine = manEngine.getEngineByName("javascript"); StringBuilder sb =
	 * Main.getSourceCodePage(
	 * "https://punchsub.zlx.com.br/listar/694/episodios/mp4");
	 * Main.encapsuleStringJavaJson(sb); Object result =
	 * engine.eval(sb.toString()); Map content = (Map) result; StringBuilder sb2
	 * = new StringBuilder();
	 * sb2.append(content.get("e").toString().replaceAll("704x400",
	 * "\"704x400\"").replaceAll("[object Object]", "\"animateca download\","));
	 * Main.encapsuleStringJavaJson(sb2); result = engine.eval(sb2.toString());
	 * Stream.of(result).forEach(System.out::println);
	 * 
	 * return null; }
	 * 
	 * private static void encapsuleStringJavaJson(StringBuilder sb) {
	 * sb.insert(0, "Java.asJSONCompatible("); sb.insert(sb.length(), ")"); }
	 */
}

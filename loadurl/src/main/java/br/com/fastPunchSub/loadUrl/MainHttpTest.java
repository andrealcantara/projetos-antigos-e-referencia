package br.com.fastPunchSub.loadUrl;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.List;
import java.util.stream.Collectors;

import br.com.fastPunchSub.redecanais.RedeCanaisFinder;
import br.com.fastPunchSub.redecanais.models.ClasseVideo;
import br.com.fastPunchSub.redecanais.models.TypeEpisode;

public class MainHttpTest {
	public static void main(String[] args) throws Exception {
		String url = "http://www.redecanais.com/the-flash-dublado-lista-completa-de-episodios_8ab0b4d6f.html";
		url = "http://www.redecanais.com/browse-c-themoneyofsoulandpossibilitycontrol-videos-1-date.html";
		HttpUrlSearch http = new HttpUrlSearch();

		// make sure cookies is turn on
		CookieHandler.setDefault(new CookieManager());

		// 1. Send a "GET" request, so that you can extract the form's data.
		String page = http.getPageContent(url).toString();

		String select = null;
		select = "#primary > p:nth-child(4) > a:contains(" + TypeEpisode.DUBLADO.getLabelPage() + ")";
		select = "#primary > div.pm-browse-desc > p > strong > a";
		List<String> links = RedeCanaisFinder.findSeriadosLinks(page, select);
		System.out.println(links);
		links = links.stream().map(x -> {
			String aux = x;
			try {
				aux = http.getPageContent(aux).toString();
				return RedeCanaisFinder.findVID(aux);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}).map(x -> {
			String aux = null;
			int stopServer = ClasseVideo.SERIADO.getURLServer();
			try {
				do {
					aux = x;
					aux = ClasseVideo.SERIADO.getNextURL() + aux;
					aux = http.getPageContent(aux).toString();
					aux = RedeCanaisFinder.findDirectLink(aux);
				} while (http.getResponseCodeByURL(aux) == 200 || stopServer == ClasseVideo.SERIADO.getURLServer());
				if (stopServer == ClasseVideo.SERIADO.getURLServer()) {
					aux = null;
				}
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
			return aux;

		}).limit(20).collect(Collectors.toList());
		System.out.println(links);

	}
}

package br.com.fastPunchSub.redecanais;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.fastPunchSub.redecanais.models.TypeEpisode;

public class RedeCanaisFinder {
	
	private static final String redeCanaisInit = "http://www.redecanais.com";
	private static final String stringSearchDirectLinkInit = "file: \"";
	private static final String stringSearchDirectLinkEnd = "\"";
	
	public static List<String> findSeriadosLinks(String html, String select) {
		Document doc = Jsoup.parse(html);
		Elements tagsA = doc.select(select);
		return tagsA.stream().map(x -> { 
		String cont = x.attr("href");
		if(!cont.contains("http")){
			cont = redeCanaisInit + cont;			
		}
		return cont;
		}).collect(Collectors.toList());
	}

	public static String findVID(String html) {
		Document doc = Jsoup.parse(html);
		return doc.select("#Playerholder > iframe").get(0).attr("src").split("vid=")[1];
	}
	
	public static String findDirectLink(String html) {
		Document doc = Jsoup.parse(html);
		Element script = doc.select("body > center > script").get(0);
		String htmlAgain = script.data();
		int idxInit = htmlAgain.indexOf(stringSearchDirectLinkInit
				) + stringSearchDirectLinkInit.length();
		int idxEnd = htmlAgain.indexOf(stringSearchDirectLinkEnd, idxInit);
		String directLink = htmlAgain.substring(idxInit, idxEnd);
		return directLink;
	}
	
}

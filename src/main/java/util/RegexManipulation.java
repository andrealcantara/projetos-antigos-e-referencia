package util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Preconditions;

public class RegexManipulation {
	
	public static List<String> searchAll(String regex, String source){
		List<String> retorno = new ArrayList<>();
		Matcher matcher = RegexManipulation.prepareVerifyRegex(regex, source);
		while(matcher.find()){
			retorno.add(matcher.group().trim());
		}
		return retorno;
	}

	public static String search(String regex, String source){
		String retorno = "";
		Matcher matcher = RegexManipulation.prepareVerifyRegex(regex, source);
		if(matcher.find()){
			retorno = matcher.group().trim();
		}
		return retorno;
	}
	
	private static Matcher prepareVerifyRegex(String regex, String source) {
		Matcher matcher = Pattern.compile(regex).matcher(source);
		Preconditions.checkArgument(matcher.find());
		return matcher;
	}

}

package util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Preconditions;

public class RegexManipulation {
	
	public static List<String> searchAll(String regex, String source){
		List<String> retorno = new ArrayList<>();
		Preconditions.checkArgument(source.matches(regex));
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		while(matcher.find()){
			retorno.add(matcher.group().trim());
		}
		return retorno;
	}

}

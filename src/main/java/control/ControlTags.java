package control;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Preconditions;

import model.Tag;

public class ControlTags {

	private static final String REGEX_IDENTIFY_TAG = "(\\{\\{){1}(okaeri@){1}([a-zA-Z0-9 =\"_\\.\\-]*)(\\}\\})";
	

	public List<Tag> findTags(String line) {
		List<Tag> retorno = new ArrayList<>();
		Pattern pattern = Pattern.compile(REGEX_IDENTIFY_TAG);
		Matcher matcher = pattern.matcher(line);
		Preconditions.checkArgument(matcher.find());
		matcher = pattern.matcher(line);
		while(matcher.find()){
			retorno.add(Tag.of(matcher.group()));
		}
		return retorno;
	}
}

package control;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.google.common.base.Preconditions;

import model.Tag;
import model.TagProperties;
import util.Configuracao;
import util.RegexManipulation;

public class ControlTags {

	private static final String REGEX_IDENTIFY_TAG = "(\\{\\{){1}(okaeri@){1}([a-zA-Z0-9 =\"_\\.\\-]*)(\\}\\})";
	private static final String REGEX_PATTERN = "^(\\{){2}("+Configuracao.defaultTags+"){1}.*(\\}){2}$";
	private static final String REGEX_PROPERTIES = "(name|type|size|needed|value)(=){1}(\"[a-zA-Z_-]+\"|\\d+|true|false){1}";
	

	public List<Tag> findTags(String line) {
		List<Tag> retorno = new ArrayList<>();
		Pattern pattern = Pattern.compile(REGEX_IDENTIFY_TAG);
		Matcher matcher = pattern.matcher(line);
		Preconditions.checkArgument(matcher.find());
		matcher = pattern.matcher(line);
		while(matcher.find()){
			retorno.add(this.processStringTags(matcher.group()));
		}
		return retorno;
	}
	
	public Tag processStringTags(String pattern) {
		Preconditions.checkArgument(pattern.matches(REGEX_PATTERN));
		List<String> properStr = RegexManipulation.searchAll(REGEX_PROPERTIES, pattern);
		List<TagProperties> properties = properStr.stream().map(this::processStringTagProperties).collect(Collectors.toList());
		return Tag.of(properties);
	}
	
	public TagProperties processStringTagProperties(String pattern) {
		Preconditions.checkArgument(pattern.matches(".*=.*"));
		String[] part = pattern.split("=");
		String tagName = part[0];
		String value = part[1];
		return TagPropertiesCheck.getInstance().check(tagName, value);
	}
}

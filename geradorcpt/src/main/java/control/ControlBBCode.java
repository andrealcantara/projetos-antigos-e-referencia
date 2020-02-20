package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

import com.google.common.base.Preconditions;

import model.BBCode;
import util.RegexManipulation;

public class ControlBBCode {
	private static final String regexBBCode = "\\[(\\/)?[a-zA-Z0-9=\\:\\/%\\.\\-\\_]+\\]";
	private static final String bbcodeIsClose = "^(\\[\\/).*";
	private static final String bbcodeBodyContent = "[a-zA-Z2-6]+";
	private static final String[] autoClosableBBCodes = {"img","hr","member"};
	private List<String> erroMessage;
	
	public ControlBBCode(){
		this.erroMessage = new ArrayList<>();
	}
	
	public boolean validationBBCode(String source){
		Preconditions.checkArgument(!source.isEmpty());
		this.erroMessage.clear();
		Stack<BBCode> bbCodes = new Stack<>();
		boolean retorno = RegexManipulation.searchAll(regexBBCode, source).stream().map(this::processStringBBCode).allMatch(bb -> {
			boolean localRetorno = true;
			if(bb.isClose()) {
				if(bbCodes.isEmpty()){
					localRetorno = false;
					this.erroMessage.add("Error: "+bb.toString()+", closed code wasn't opened.");
				} else {
					BBCode bbcode = bbCodes.pop();
					if(!bb.getTag().equals(bbcode.getTag())){
						this.erroMessage.add("Error: "+bb.toString()+", not is the last open code.");
						localRetorno = false;
					}
				}
			} else {
				if(!ControlBBCode.isAutoClosableBBCodes(bb.getTag())) {
					bbCodes.push(bb);
				}
			}
			return localRetorno;
		});
		if(!bbCodes.empty()){
			erroMessage.add("Error: "+bbCodes.pop().toString()+", wasn't resolved yet.");
			retorno = false;
		}
		if(!erroMessage.isEmpty()) {
			throw new RuntimeException("\n"+String.join("\n", erroMessage));
		}
		return retorno;
	}
	
	private static boolean isAutoClosableBBCodes(String nameTag){
		return Stream.of(autoClosableBBCodes).anyMatch(e -> e.equals(nameTag.toLowerCase()));
	}
	
	
	public BBCode processStringBBCode(String source) {
		Preconditions.checkArgument(!source.isEmpty());
		boolean close = source.matches(ControlBBCode.bbcodeIsClose);
		String tag = RegexManipulation.search(ControlBBCode.bbcodeBodyContent, source);
		String innerValue = "";
		if (source.contains("=")) {
			innerValue = source.split("=")[1].replace("]", "");
		}
		return BBCode.of(tag, close, innerValue);
	}
}

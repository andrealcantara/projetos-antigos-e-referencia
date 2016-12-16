package control;

import java.util.Stack;
import java.util.stream.Stream;

import com.google.common.base.Preconditions;

import model.BBCode;
import util.RegexManipulation;

public class ControlBBCode {
	public static final String regexBBCode = "\\[(\\/)?[a-zA-Z0-9=\\:\\/%\\.\\-\\_]+\\]";
	public static final String[] autoClosableBBCodes = {"img","hr","member"};
	
	
	
	public boolean validationBBCode(String source){
		Preconditions.checkArgument(!source.isEmpty());
		Stack<BBCode> bbCodes = new Stack<>();
		boolean retorno = RegexManipulation.searchAll(regexBBCode, source).stream().map(BBCode::of).allMatch(bb -> {
			boolean localRetorno = true;
			if(bb.isClose()) {
				if(bbCodes.isEmpty()){
					localRetorno = false;
				} else {
					BBCode bbcode = bbCodes.pop();
					if(!bb.getTag().equals(bbcode.getTag())){
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
			retorno = false;
		}
		return retorno;
		
	}
	
	private static boolean isAutoClosableBBCodes(String nameTag){
		return Stream.of(autoClosableBBCodes).anyMatch(e -> e.equals(nameTag.toLowerCase()));
	}
}

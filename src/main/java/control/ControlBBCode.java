package control;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

import com.google.common.base.Preconditions;

import model.BBCode;
import util.RegexManipulation;

public class ControlBBCode {
	public static final String regexBBCode = "\\[(\\/)?[a-zA-Z0-9=\\:\\/\\.\\-\\_]+\\]";
	public static final String[] autoClosableBBCodes = {"img"};
	
	
	
	public boolean validationBBCode(String source){
		Preconditions.checkArgument(!source.isEmpty());
		Queue<BBCode> bbOpenCodes = new LinkedList<>();
		Queue<BBCode> bbCloseCodes = new LinkedList<>();
		
		RegexManipulation.searchAll(regexBBCode, source).stream().map(BBCode::of).forEach(bb -> {
			if(bb.isClose()) {
				bbCloseCodes.add(bb);
			} else {
				if(!ControlBBCode.isAutoClosableBBCodes(bb.getTag())) {
					bbOpenCodes.add(bb);
				}
			}
		});
		 
		 
		
		return false;
		
	}
	
	private static boolean isAutoClosableBBCodes(String nameTag){
		return Stream.of(autoClosableBBCodes).anyMatch(e -> e.equals(nameTag.toLowerCase()));
	}
}

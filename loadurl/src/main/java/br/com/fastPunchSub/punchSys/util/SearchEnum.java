package br.com.fastPunchSub.punchSys.util;

import java.util.function.Predicate;
import java.util.stream.Stream;

import br.com.fastPunchSub.messageLoader.MessageLoader;
import br.com.fastPunchSub.messageLoader.MessageLoader.MessageType;

public interface SearchEnum {
	
	static <T> T findInEnum(String erroMsg, Predicate<T> predic, T[] values) {
		return Stream.of(values).filter(predic).findFirst()
				.orElseThrow(() -> new RuntimeException(erroMsg));
	}
}

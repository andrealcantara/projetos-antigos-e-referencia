package br.com.fastPunchSub.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

public class LevelLogger {
	private static final Logger LOGGER = LoggerFactory.getLogger(LevelLogger.class);
	private static final Map<Level, Consumer<String>> map = LevelLoggerCreate.mapper();
	private static boolean bool = true;
	// ;

	public static void log(Level level, String s) {
		if (LevelLogger.bool) {
			LevelLogger.map.get(level).accept(s);
		}
	}

	private static boolean viewLog(Level level) {
		boolean bool;
		switch (level) {
		case DEBUG:
			bool = LOGGER.isDebugEnabled();
			break;
		case ERROR:
			bool = LOGGER.isErrorEnabled();
			break;
		case INFO:
			bool = LOGGER.isInfoEnabled();
			break;
		case TRACE:
			bool = LOGGER.isTraceEnabled();
			break;
		case WARN:
			bool = LOGGER.isWarnEnabled();
			break;
		default:
			bool = false;
		}
		return bool;
	}

	private static class LevelLoggerCreate {
		private static Map<Level, Consumer<String>> mapper() {
			Map<Level, Consumer<String>> map = new HashMap<>();
			map.put(Level.TRACE, LOGGER::trace);
			map.put(Level.DEBUG, LOGGER::debug);
			map.put(Level.INFO, LOGGER::info);
			map.put(Level.WARN, LOGGER::warn);
			map.put(Level.ERROR, LOGGER::error);
			return map;
		}
	}
}
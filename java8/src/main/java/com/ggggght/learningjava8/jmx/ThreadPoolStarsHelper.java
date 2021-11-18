package com.ggggght.learningjava8.jmx;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolStarsHelper {

	private static final ThreadPoolStarsHelper instance = new ThreadPoolStarsHelper();

	private static final Map<String, ThreadPoolExecutor> MAP = new ConcurrentHashMap<>(8);

	public static ThreadPoolStarsHelper getInstance() {
		return instance;
	}

	private ThreadPoolStarsHelper() {
	}

	public static void register(String name, ThreadPoolExecutor executor) {
		MAP.put(name, executor);
	}

	public static ThreadPoolExecutor get(String name) {
		return MAP.get(name);
	}

	public static Map getMap() {
		return MAP;
	}

}

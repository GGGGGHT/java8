package com.ggggght.learningjava8.jmx;

public class ThreadPoolManagement implements ThreadPoolManagementMXBean {

	@Override
	public String getPoolName() {
		return ThreadPoolStarsHelper.getMap().keySet().toString();
	}

	@Override
	public int getActiveThreads(String name) {
		return ThreadPoolStarsHelper.get(name).getActiveCount();
	}

	@Override
	public int getPoolSize(String naame) {
		return ThreadPoolStarsHelper.get(naame).getPoolSize();
	}

}

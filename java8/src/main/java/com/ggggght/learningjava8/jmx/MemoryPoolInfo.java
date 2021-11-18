package com.ggggght.learningjava8.jmx;

import java.io.IOException;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("all")
public class MemoryPoolInfo {

	private static final Logger LOGGER = LoggerFactory.getLogger(MemoryPoolInfo.class);

	public static void main(String[] args) throws IOException {
		final List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();
		int poolsFound = 0;
		int poolsWithStats = 0;

		pools.forEach(pool -> {
			final String name = pool.getName();
			LOGGER.info("found pool: {}", name);
		});

		final List<GarbageCollectorMXBean> collectors = ManagementFactory.getGarbageCollectorMXBeans();
		int collectorsFound = 0;
		collectors.forEach(collector -> {
			final String name = collector.getName();
			LOGGER.info("found collector: {}", name);
			LOGGER.info("{} collection count = {}", name, collector.getCollectionCount());
		});

		final ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
		LOGGER.info("total loading class: {}", classLoadingMXBean.getTotalLoadedClassCount());
		LOGGER.info("loaded class count: {}", classLoadingMXBean.getLoadedClassCount());
	}

}

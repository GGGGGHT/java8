package com.ggggght.learningjava8.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;   

@SuppressWarnings("all")
public class PSTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(PSTest.class);
	
	// @Benchmark
    public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(PS.class.getSimpleName())
				.forks(1)
				.build();
		
		new Runner(opt).run();
	}
}

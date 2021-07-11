package com.ggggght.learningjava8.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("all")
public class PS {
	private static final Logger LOGGER = LoggerFactory.getLogger(PS.class);
    
    static List<Integer> nums = new ArrayList<>();
    static {
        Random r = new Random();
        for (int i = 0; i < 10000; i++) nums.add(1000000 + r.nextInt(1000000));
    }
    
    @Benchmark
    public static void foreach() {
        nums.forEach(v->isPrime(v));
    }
    
    static void parallel() {
        nums.parallelStream().forEach(PS::isPrime);
    }
    
    static boolean isPrime(int num) {
        for(int i=2; i<=num/2; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}

package com.ggggght.learningjava8.autowired;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Profile("Java7")
public class IterationCalculatingService implements CalculatingService {

	@Override
	public Integer sum(Integer... values) {
		int sum = 0;
		for (Integer value : values) {
			sum += value;
		}
		System.out.printf("[Java 7 迭代实现] %s 累加结果: %d\n.", Arrays.asList(values), sum);

		return sum;
	}

}

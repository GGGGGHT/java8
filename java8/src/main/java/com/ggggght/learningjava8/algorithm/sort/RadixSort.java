package com.ggggght.learningjava8.algorithm.sort;

/**
 * @author ght
 * @Desc 基数排序
 * @date 2020-04-27 16:41
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class RadixSort {

	static final Logger logger = LoggerFactory.getLogger(RadixSort.class);

	public static void sort(int[] number) // d表示最大的数有多少位
	{
		int max = number[0];
		for (int i : number) {
			if (i > max) {
				max = i;
			}
		}
		String maxStr = String.valueOf(max);
		int d = maxStr.length();

		int k = 0;
		int n = 1;
		int m = 1; // 控制键值排序依据在哪一位
		int[][] temp = new int[10][number.length]; // 数组的第一维表示可能的余数0-9
		int[] order = new int[10]; // 数组orderp[i]用来表示该位是i的数的个数
		while (m++ <= d) {
			for (int i = 0; i < number.length; i++) {
				int lsd = ((number[i] / n) % 10);
				temp[lsd][order[lsd]++] = number[i];
			}
			for (int i = 0; i < 10; i++) {
				if (order[i] != 0)
					for (int j = 0; j < order[i]; j++) {
						number[k++] = temp[i][j];
					}
				order[i] = 0;
			}

			n *= 10;
			k = 0;
		}
	}

	public static void main(String[] args) {
		/*
		 * int[] data = {73, 22, 93, 43, 55, 14, 28, 65, 39, 81, 33, 100};
		 */
		int[] data = new int[80000000];
		for (int i = 0; i < 800000000; i++) {
			data[i] = (int) (Math.random() * 800000000);
		}
		Instant start = Instant.now();
		RadixSort.sort(data);
		Instant end = Instant.now();
		System.out.println(Duration.between(start, end).getSeconds());
		// System.out.println(Arrays.toString(data));
	}

}
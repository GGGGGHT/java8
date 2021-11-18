package com.ggggght.learningjava8.algorithm.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author ght
 * @Desc 希尔排序
 * @date 2020-01-22 5:58 PM
 */

@SuppressWarnings("all")
public class ShellSort {

	public static int[] shell(int[] source) {
		// 希尔排序的第一轮
		// 是将10个数据分成了5组
		int len = source.length;
		int tmp = 0;
		for (int i = 5; i < len; i++) {
			for (int j = i - 5; j >= 0; j -= 5) {
				if (source[i] < source[j]) {
					tmp = source[i];
					source[i] = source[j];
					source[j] = tmp;
				}
			}
		}
		System.out.println("2:" + Arrays.toString(source));
		for (int i = 2; i < len; i++) {
			for (int j = i - 2; j >= 0; j -= 2) {
				if (source[i] < source[j]) {
					tmp = source[i];
					source[i] = source[j];
					source[j] = tmp;
				}
			}
		}
		System.out.println("1:" + Arrays.toString(source));
		for (int i = 1; i < len; i++) {
			for (int j = i - 1; j >= 0; j -= 1) {
				if (source[i] < source[j]) {
					tmp = source[i];
					source[i] = source[j];
					source[j] = tmp;
				}
			}
		}
		// System.out.println("1:" + Arrays.toString(source));
		return source;
	}

	public static void main(String[] args) {
		int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
		shell(arr);
		System.out.println(Arrays.toString(arr));
	}

}

package com.ggggght.learningjava8.algorithm.sort;

import javax.swing.text.AbstractWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author ght
 * @Desc 选择排序
 * @date 2020-01-22 2:33 PM
 * <p>
 * 数据量越大 选择排序相对于冒泡的效率更高
 */

@SuppressWarnings("all")
public class SelectSort {

	public static int[] SelectSort(int[] source) {
		int len = source.length;
		for (int i = 0; i < len - 1; i++) {
			int min = source[i];
			// minIndex 为最小值所在的索引
			int minIndex = -1;
			for (int j = i + 1; j < len; j++) {
				if (source[j] < min) {
					min = source[j];
					minIndex = j;
				}
			}
			if (-1 != minIndex) {
				source[minIndex] = source[i];
				source[i] = min;
			}
		}
		return source;
	}

	public static void main(String[] args) {
		int[] arr = { 3, -1, 5, 2, 10 };
		// int[] arr = {101, 34,119, 10};
		// int[] arr = new int[80000];
		// for (int i = 0; i < 80000; i++) {
		// arr[i] = (int) (Math.random() * 8000000);
		// }
		// Instant start = Instant.now();
		select(arr);
		System.out.println(Arrays.toString(arr));
		// Instant end = Instant.now();
		// System.out.println(Duration.between(start, end).toMillis());
	}

	public static void mySelectSort(int[] arr) {
		int len = arr.length;

		for (int i = 0; i < len; i++) {
			int min = i;
			for (int j = i + 1; j < len; j++) {
				min = arr[min] < arr[j] ? min : j;
			}

			if (min != i) {
				swap(arr, i, min);
			}
		}
	}

	// public static void select(int[] arr) {
	// // min为最小的位置
	// for (int j = 0; j < arr.length - 1; j++) {
	// int min = j;
	// for (int i = j + 1; i < arr.length; i++) {
	// min = arr[min] > arr[i] ? i : min;
	// }
	//
	// if (min != j) {
	// swap(arr, j, min);
	// }
	//
	// }
	//
	// System.out.println(Arrays.toString(arr));
	// }

	private static void swap(int[] arr, int i, int j) {
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}

	public static void select(int[] arr) {
		if (Objects.isNull(arr) || 0 == arr.length) {
			return;
		}

		int min = 0, len = arr.length, tmp = 0;
		for (int j = 0; j < len; j++) {
			for (int i = j; i < len; i++) {
				min = arr[i] < arr[min] ? i : min;
			}

			if (tmp != min) {
				swap(arr, tmp++, min);
			}
		}

	}

}

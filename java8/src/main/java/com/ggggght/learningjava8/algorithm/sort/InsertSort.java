package com.ggggght.learningjava8.algorithm.sort;

import lombok.val;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author ght
 * @Desc 插入排序
 * @date 2020-01-22 3:42 PM
 */

@SuppressWarnings("all")
public class InsertSort {
	public static int[] insert(int[] source) {
		int len = source.length;
		for (int i = 1; i < len; i++) {
			// 当前要插入的数据
			int insertValue = source[i];
			int insertIndex = i - 1;
			// 防止越界
			while (insertIndex >= 0 && insertValue < source[insertIndex]) {
				source[insertIndex + 1] = source[insertIndex];
				insertIndex--;
			}
			// 循环结束后insertIndex就是要插入的位置
			if (insertIndex + 1 != i) {
				source[insertIndex + 1] = insertValue;
			}
		}
		return source;
	}
	
	public static void main(String[] args) {
		int[] arr = {101, 34, 0, -1, 5, 8, 200, 119, 10};
		myInsert(arr);
		System.out.println(Arrays.toString(arr));
       /* int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Instant start = Instant.now();
        myInsert(arr);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());*/
	}
	
	// private static void myInsert(int[] arr) {
	// 	int len = arr.length;
	//
	// 	for (int i = 1; i < len; i++) {
	// 		while (i - 1 > -1 && arr[i - 1] > arr[i]) {
	// 			swap(arr, i, i-- - 1);
	// 		}
	// 	}
	// }
	
	private static void swap(int[] arr, int i, int j) {
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}
	
	private static void myInsert(int[] arr) {
		if (Objects.isNull(arr) || 0 == arr.length) {
			return;
		}
		
		for (int i = 1; i < arr.length; i++) {
			while (i - 1 >= 0 && arr[i] < arr[i - 1]) {
				swap(arr, i, i - 1);
				i--;
			}
		}
	}
}

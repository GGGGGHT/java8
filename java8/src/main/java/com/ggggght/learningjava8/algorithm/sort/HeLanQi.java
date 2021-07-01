package com.ggggght.learningjava8.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

public class HeLanQi {
	public static void main(String[] args) {
		int[] arr = {4, 2, 1, 3, 0, 5, 4, 1};
		heLanQi(arr, 4);
	}
	
	public static void heLanQi(int[] arr, int n) {
		int s = -1;
		int r = arr.length - 1;
		int i = 0;
		// int e;
		while (i < r) {
			if (arr[i] < n) {
				// 与左边交换
				arr[i] = arr[i] ^ arr[++s];
				arr[s] = arr[i] ^ arr[s];
				arr[i] = arr[i++] ^ arr[s];
			} else if (arr[i] > n) {
				arr[r] = arr[i] ^ arr[r];
				arr[i] = arr[i] ^ arr[r];
				arr[r] = arr[i] ^ arr[r--];
				//r--;
			}else {
				i++;
			}
		}
		System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
	}
	
	@Test
	public void test() {
		int i = 1;
		i = i++;
		System.out.println(i);
	}
}

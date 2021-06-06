package com.ggggght.learningjava8.algorithm.sort;

import org.junit.Test;

import javax.sound.midi.Soundbank;
import javax.xml.transform.Source;
import java.util.Arrays;

/**
 * @author ght
 * @Desc 归并算法
 * @date 2020-04-26 20:02
 */

@SuppressWarnings("all")
public class MergeSort {
	
	/**
	 * @param arr   待排序的数组
	 * @param left  左边有序序列的初始索引
	 * @param mid   中间索引
	 * @param right 右边索引
	 * @param tmp   中转数组
	 */
	public static void sort(int[] arr) {
		int[] temp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
		sort(arr, 0, arr.length - 1, temp);
	}
	
	private static void sort(int[] arr, int left, int right, int[] temp) {
		if (left < right) {
			int mid = (left + right) / 2;
			sort(arr, left, mid, temp);//左边归并排序，使得左子序列有序
			sort(arr, mid + 1, right, temp);//右边归并排序，使得右子序列有序
			//merge(arr, left, mid, right, temp);//将两个有序子数组合并操作
			merge(arr, left, right, mid);
		}
		
	}
	
	private static void merge(int[] arr, int left, int right, int mid) {
		int len = arr.length;
		int rightStart = mid + 1;
		int[] tmp = new int[len];
		int t = 0;
		while (left <= mid && rightStart <= right) {
			// if (arr[i] < arr[j]) tmp[t++] = arr[i++]; else  tmp[t++] = arr[j++];
			tmp[t++] = arr[left] < arr[rightStart] ? arr[left++] : arr[rightStart++];
		}
		
		if (left > mid) {
			while (rightStart <= len - 1) {
				tmp[rightStart++] = arr[rightStart++];
			}
		}
		
		if (rightStart > right) {
			while (left <= mid) {
				tmp[t++] = arr[left++];
			}
		}
		
		System.out.println(Arrays.toString(tmp));
	}
	
	private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		int len = arr.length;
		int rightStart = mid + 1;
		int[] tmp = new int[len];
		int t = 0;
		while (left <= mid && rightStart <= right) {
			// if (arr[i] < arr[j]) tmp[t++] = arr[i++]; else  tmp[t++] = arr[j++];
			tmp[t++] = arr[left] <= arr[rightStart] ? arr[left++] : arr[rightStart++];
		}
		
		if (left > mid) {
			while (rightStart <= len - 1) {
				tmp[rightStart++] = arr[rightStart++];
			}
		}
		
		if (rightStart > right) {
			while (left <= mid) {
				tmp[t++] = arr[left++];
			}
		}
		
		for (int i = 0; i < tmp.length; i++) {
			arr[i] = tmp[i];
		}
		//return tmp;
	}
	
	public static void main(String[] args) {
		int[] arr = {4, 2, 8, 5, 6, 3, 7, 1};
		sort(arr, 0, arr.length - 1, new int[arr.length]);
		
		System.out.println(Arrays.toString(arr));
	}
	
	
}

class MyMerge {
	
	private static void process(int[] arr, int i, int j) {
		if (i == j) {
			return;
		}
		
		int mid = i + ((j - i) >> 1);
		process(arr, i, mid);
		process(arr, mid + 1, j);
		merge(arr, i, j, mid);
	}
	
	private static void merge(int[] arr, int left, int right, int mid) {
		int l = left;
		int len = right - left + 1;
		int rightStart = mid + 1;
		int[] tmp = new int[len];
		int t = 0;
		while (left <= mid && rightStart <= right) {
			tmp[t++] = arr[left] <= arr[rightStart] ? arr[left++] : arr[rightStart++];
		}
		
		while (left <= mid) {
			tmp[t++] = arr[left++];
		}
		
		while (rightStart <= right) {
			tmp[t++] = arr[rightStart++];
		}
		
		System.out.println("tmp: " + Arrays.toString(tmp));
		for (int i = 0; i < tmp.length; i++) {
			arr[l++] = tmp[i];
		}
		
	}
	
	public static void main(String[] args) {
		//int arr[] = {4, 2, 1, 3};
		//process(arr, 0, arr.length - 1);
		//System.out.println("Arrays.toString(tmp) = " + Arrays.toString(arr));
		//noRecursive(arr);
		//System.out.println("Arrays.toString(tmp) = " + Arrays.toString(arr));
		getArraySum();
	}
	
	
	public static void noRecursive(int[] arr) {
		int len = arr.length;
		if (arr == null || len < 2) {
			return;
		}
		
		int mergeSize = 1;
		while (mergeSize < len) {
			int L = 0;
			while (L < len) {
				int M = L + mergeSize - 1;
				if (M >= len) break;
				
				int R = Math.min(M + mergeSize, len - 1);
				merge(arr, L, R, M);
				L = R + 1;
			}
			
			// 加上这个判断防止溢出
			if (mergeSize > len / 2) break;
			
			mergeSize <<= 1;
		}
	}
	
	/**
	 * 在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小各。
	 * 例子 [1，3，4，2，5]
	 * 1左边比1小的数： 没有
	 * 3左边比1小的数： 1
	 * 4左边比1小的数： 1 3
	 * 2左边比1小的数： 1
	 * 5左边比1小的数： 1 3 4 2
	 * <p>
	 * 所以数组小和为： 1 + 1 + 1 + 1 + 3 + 3 + 4 + 2 ＝ 16
	 */
	// 写一个对数器
	public static int logarithm(int[] arr) {
		int res = 0;
		int len = 0;
		if (arr == null || (len = arr.length) == 0) {
			return res;
		}
		
		for (int i = 1; i < len; i++) {
			int tmp = 0;
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i]) {
					tmp += arr[j];
				}
			}
			res += tmp;
		}
		
		return res;
	}
	
	@Test
	public static void getArraySum() {
		int[] arr = {1, 3, 4, 2, 5};
		mergeArraySum(arr);
		//System.out.println("logarithm(arr) = " + mergeArraySum(arr));
	}
	
	public static void mergeArraySum(int[] arr) {
		//int mid = arr.length - 1;
		mergeArray(arr, 0, arr.length, (arr.length >> 1));
	}
	
	public static int mergeArray(int[] arr, int left, int right, int mid) {
		int len;
		int[] tmp = new int[(len = right - left + 1)];
		int t = 0;
		int R = mid + 1;
		int L = left;
		int res = 0;
		while (L <= mid && R <= right) {
			tmp[t++] = arr[L] < arr[R] ? arr[L++] : arr[R++];
			res += arr[L] < arr[R] ? (right - R + 1) * arr[L] : 0;
		}
		
		while (L <= mid) {
			tmp[t++] = arr[L++];
		}
		
		while (R <= right) {
			tmp[t++] = arr[R++];
		}
		
		return res;
	}
}



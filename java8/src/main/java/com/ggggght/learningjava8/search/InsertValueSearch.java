package com.ggggght.learningjava8.search;


import java.util.Arrays;

/**
 * @author ght
 * @Desc 插值查找算法
 * @date 2020-05-06 12:54
 *
 * 插值查找的注意事项:
 * 1. 对于数据量较大,关键字分布比较均匀的情况下采用插值查找效率会比二分查找要高效
 * 2. 关键字分布不均匀的情况下,插值查找效率不一定有二分查找的效率高
 *
 * 插值查找寻找mid的公式:
 * int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left])
 */

@SuppressWarnings("all")
public class InsertValueSearch {
    private static int search(int[] arr, int val, int left, int right) {
        if (left > right || val < arr[0] || val > arr[arr.length - 1]) {
            return -1;
        }

        int mid = left + (right - left) * (val - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];

        if (val > midVal) {
            return search(arr, val, mid + 1, right);
        } else if (val < midVal) {
            return search(arr, val, left, mid - 1);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 1; i < 101; i++) {
            arr[i - 1] = i;
        }
        System.out.println(search(arr, 80, 0, arr.length - 1));
    }
}

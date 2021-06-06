package com.ggggght.learningjava8.algorithm.sort;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author ght
 * @Desc 冒泡排序算法
 * @date 2020-01-16 12:42 PM
 */

@SuppressWarnings("all")
public class BubbleSort {
    public static int[] bubble(int[] source) {
        int len = source.length;
        for (int i = 0; i < len - 1 ; i++) {
            boolean flag = false;
            for (int j = 0; j < len - 1 - i; j++) {
                if (source[j] > source[j + 1]) {
                    swap(source,j,j+1);
                    flag = true;
                }
            }

            if (!flag) {
                break;
            }
        }
        return source;
    }

    public static final int[] bu(int[] arr) {
        if (Objects.isNull(arr) || 0 == arr.length) {
            return arr;
        }
    
        int len = arr.length;
        for (int i = 0; i < len -1 ; i++) {
            boolean flag = false;
            for (int j = 0 ; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j , j + 1);
                    flag = true;
                }
            }
    
            if (!flag) {
                break;
            }
        }
        
        return arr;
    }
    
    private static final void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
    public static void main(String[] args) {
        int[] arr = {3, -1, 5, 2, 10};
        System.out.println(Arrays.toString(bu(arr)));
        /*int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Instant start = Instant.now();
        bubble(arr);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());*/
    }
}

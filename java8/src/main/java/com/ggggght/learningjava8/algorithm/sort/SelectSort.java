package com.ggggght.learningjava8.algorithm.sort;

import java.time.Duration;
import java.time.Instant;

/**
 * @author ght
 * @Desc 选择排序
 * @date 2020-01-22 2:33 PM
 *
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
        // int[] arr = {3, -1, 5, 2, 10};
        // int[] arr = {101, 34,119, 10};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Instant start = Instant.now();
        SelectSort(arr);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
    }
}

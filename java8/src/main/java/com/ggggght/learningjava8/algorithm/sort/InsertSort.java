package com.ggggght.learningjava8.algorithm.sort;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

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
        int[] arr = {101, 34, 119, 10};
        System.out.println(Arrays.toString(insert(arr)));
        /*int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Instant start = Instant.now();
        insert(arr);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());*/
    }
}

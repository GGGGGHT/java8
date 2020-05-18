package com.ggggght.learningjava8.search;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

/**
 * @author ght
 * @Desc
 * @date 2020-05-06 14:27
 */

@SuppressWarnings("all")
public class FibonacciSearch {
    public static int maxSize = 20;

    /**
     * 使用非递归的方式得到一个斐波那契数组
     */
    private static int[] Fibonacci() {
        int[] fib = new int[20];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    /**
     * 使用非递归来查找
     *
     * @param arr
     * @param val 需要匹配的值
     * @return 返回对应的下标 如果没有找到则返回 -1
     */
    private static int fibSearch(int[] arr, int val) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0; //表示斐波那契分割值的下标
        int mid = 0;
        int f[] = Fibonacci();
        while (high > f[k++] - 1) {
        }
        int[] tmp = Arrays.copyOf(arr, f[k]);
        for (int i = high + 1; i < tmp.length; i++) {
            tmp[i] = arr[high];
        }
        while (low < high) {
            mid = low + f[k - 1] + 1;
            if (val < tmp[mid]) {
                high = mid - 1;
                // 为什么需要k--
                k--;
            } else if (val > tmp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println(fibSearch(arr,10));
    }

}

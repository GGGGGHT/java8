package com.ggggght.learningjava8.search;

/**
 * @author ght
 * @Desc 线性查找
 * @date 2020-04-27 18:57
 */

@SuppressWarnings("all")
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {10, 3, -1, 5, 2};
        int i = seqSearch(arr, 2);
        if (i == -1) {
            System.out.println("not found!");
        }
    }

    public static int seqSearch(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (val == i) {
                return i;
            }
        }
        return -1;
    }
}

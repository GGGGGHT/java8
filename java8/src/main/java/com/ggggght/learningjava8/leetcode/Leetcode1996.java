package com.ggggght.learningjava8.leetcode;

import java.util.Arrays;

public class Leetcode1996 {
  public static void main(String[] args) {
    // System.out.println(numberOfWeakCharacters(new int[][] {{5, 5}, {6, 3}, {3, 6}}));
    // System.out.println(numberOfWeakCharacters(new int[][] {{2, 2}, {3, 3}}));
    // System.out.println(numberOfWeakCharacters(new int[][] {{1, 5}, {10, 4}, {4, 3}}));
    System.out.println(numberOfWeakCharacters(
        new int[][] {{7, 7}, {1, 2}, {9, 7}, {7, 3}, {3, 10}, {9, 8}, {8, 10}, {4, 3}, {1, 5},
            {1, 5}}));
  }

  /**
   * 暴力解 将所有的数据都映射到一个二维数组中, 当遍历时发现有比这[i][j]大的就将其节点上的数增加后置0 超出内存限制
   * @param properties
   * @return
   */
  public static int numberOfWeakCharacters(int[][] properties) {
    var len = 0;
    var col = 0;
    for (int[] property : properties) {
      len = Math.max(len, property[0]);
      col = Math.max(col, property[1]);
    }
    int[][] arr = new int[len + 1][col + 1];
    var res = 0;
    for (int[] property : properties) {
      arr[property[0]][property[1]]++;
    }

    for (int[] ints : arr) {
      System.out.println(Arrays.toString(ints));
    }

    for (int i = 0; i < arr.length; i++) {
      for (int i1 = 0; i1 < arr[i].length; i1++) {
        if (arr[i][i1] > 0) {
          for (int j = 0; j < i; j++) {
            for (int x = 0; x < i1; x++) {
              if (arr[j][x] > 0) {
                res += arr[j][x];
                arr[j][x] = 0;
              }
            }
          }
        }
      }
    }
    return res;
  }
}

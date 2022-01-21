package com.ggggght.learningjava8.leetcode;

public class Leetcode1572 {
  public static void main(String[] args) {
    var mat = new int[][] {{1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}};
    System.out.println(diagonalSum(mat));
    int[][] ints = {
        {1, 1, 1, 1},
        {1, 1, 1, 1},
        {1, 1, 1, 1},
        {1, 1, 1, 1}
    };
    System.out.println(diagonalSum(ints));
    System.out.println(diagonalSum(new int[][] {{5}}));
  }

  public static int diagonalSum(int[][] mat) {
    var res = 0;
    var isOdd = (mat.length & 1) == 1;
    // 奇数的中点
    var mid = mat.length >> 1;
    var len = mat.length - 1;
    for (int i = 0; i <= len; i++) {
      res += mat[i][i];
    }

    for (int i = len, t = 0; i >= 0; i--, t++) {
      if (i == mid && isOdd) {
        continue;
      }

      res += mat[t][i];
    }
    return res;
  }
}


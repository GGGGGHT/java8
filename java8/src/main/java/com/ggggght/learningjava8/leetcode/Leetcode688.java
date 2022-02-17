package com.ggggght.learningjava8.leetcode;

import java.util.Stack;

public class Leetcode688 {
  static int[][] dirs =
      new int[][] {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

  public static void main(String[] args) {
    System.out.println(knightProbability(3, 2, 0, 0));
  }
  public static double knightProbability(int n, int k, int row, int column) {
    if (k == 0) return 1;

    double[][][] dp = new double[k + 1][n][n];
    for (int step = 0; step <= k; step++) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (step == 0) {
            dp[step][i][j] = 1;
          } else {
            for (int[] dir : dirs) {
              int ni = i + dir[0], nj = j + dir[1];
              if (ni >= 0 && ni < n && nj >= 0 && nj < n) {
                dp[step][i][j] += dp[step - 1][ni][nj] / 8;
              }
            }
          }
        }
      }
    }
    return dp[k][row][column];
  }

}

// 0 0 0
// 0 0 0
// 0 0 0

// 1 2
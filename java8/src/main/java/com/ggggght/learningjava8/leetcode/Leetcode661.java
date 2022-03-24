package com.ggggght.learningjava8.leetcode;

import java.util.Arrays;
/**
 <p><strong>图像平滑器</strong> 是大小为&nbsp;<code>3 x 3</code> 的过滤器，用于对图像的每个单元格平滑处理，平滑处理后单元格的值为该单元格的平均灰度。</p>

 <p>每个单元格的<strong>&nbsp; 平均灰度</strong> 定义为：该单元格自身及其周围的 8 个单元格的平均值，结果需向下取整。（即，需要计算蓝色平滑器中 9 个单元格的平均值）。</p>

 <p>如果一个单元格周围存在单元格缺失的情况，则计算平均灰度时不考虑缺失的单元格（即，需要计算红色平滑器中 4 个单元格的平均值）。</p>

 <p><img src="https://assets.leetcode.com/uploads/2021/05/03/smoother-grid.jpg" style="height: 493px; width: 493px;" /></p>

 <p>给你一个表示图像灰度的 <code>m x n</code> 整数矩阵 <code>img</code> ，返回对图像的每个单元格平滑处理后的图像&nbsp;。</p>

 <p>&nbsp;</p>

 <p><strong>示例 1:</strong></p>

 <p><img src="https://assets.leetcode.com/uploads/2021/05/03/smooth-grid.jpg" /></p>

 <pre>
 <strong>输入:</strong>img = [[1,1,1],[1,0,1],[1,1,1]]
 <strong>输出:</strong>[[0, 0, 0],[0, 0, 0], [0, 0, 0]]
 <strong>解释:</strong>
 对于点 (0,0), (0,2), (2,0), (2,2): 平均(3/4) = 平均(0.75) = 0
 对于点 (0,1), (1,0), (1,2), (2,1): 平均(5/6) = 平均(0.83333333) = 0
 对于点 (1,1): 平均(8/9) = 平均(0.88888889) = 0
 </pre>

 <p><strong>示例 2:</strong></p>
 <img alt="" src="https://assets.leetcode.com/uploads/2021/05/03/smooth2-grid.jpg" />
 <pre>
 <strong>输入:</strong> img = [[100,200,100],[200,50,200],[100,200,100]]
 <strong>输出:</strong> [[137,141,137],[141,138,141],[137,141,137]]
 <strong>解释:</strong>
 对于点 (0,0), (0,2), (2,0), (2,2): floor((100+200+200+50)/4) = floor(137.5) = 137
 对于点 (0,1), (1,0), (1,2), (2,1): floor((200+200+50+200+100+100)/6) = floor(141.666667) = 141
 对于点 (1,1): floor((50+200+200+200+200+100+100+100+100)/9) = floor(138.888889) = 138
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示:</strong></p>

 <ul>
 <li><code>m == img.length</code></li>
 <li><code>n == img[i].length</code></li>
 <li><code>1 &lt;= m, n &lt;= 200</code></li>
 <li><code>0 &lt;= img[i][j] &lt;= 255</code></li>
 </ul>
 <div><div>Related Topics</div><div><li>数组</li><li>矩阵</li></div></div><br><div><li>👍 158</li><li>👎 0</li></div>
 */

public class Leetcode661 {
  public static void main(String[] args) {
    var arr = new int[][] {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
    // var arr = new int[][] {{100, 200, 100}, {200, 50, 200}, {100, 200, 100}};
    int[][] ans = new Leetcode661().imageSmoother2(arr);
    System.out.println("===============");
    for (int[] an : ans) {
      System.out.println(Arrays.toString(an));
    }
  }
  int[][] arr = new int[][] {
      {-1, -1},
      {-1, 0},
      {-1, 1},
      {0, -1},
      {0, 1},
      {1, -1},
      {1, 0},
      {1, 1}
  };
  int len;
  int col;
  public int[][] imageSmoother(int[][] img) {
    len = img.length;
    col = img[0].length;
    int[][] res = new int[len][col];

    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[i].length; j++) {
        int count = img[i][j];
        int tmp = 1;
        for (int[] ints : arr) {
          if (inArea(i + ints[0], j + ints[1])) {
            tmp++;
            count += img[i+ints[0]][j+ints[1]];
          }
        }
        res[i][j] = count / tmp;
      }
    }
    return res;
  }

  private boolean inArea(int i, int j) {
    return i >= 0 && i < len && j >= 0 && j < col;
  }

  public int[][] imageSmoother2(int[][] img) {
    int m = img.length, n = img[0].length;
    int[][] sum = new int[m + 10][n + 10];
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + img[i - 1][j - 1];
      }
    }
    for (int[] ints : sum) {
      System.out.println(Arrays.toString(ints));
    }
    int[][] ans = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int a = Math.max(0, i - 1), b = Math.max(0, j - 1);
        int c = Math.min(m - 1, i + 1), d = Math.min(n - 1, j + 1);
        int cnt = (c - a + 1) * (d - b + 1);
        int tot = sum[c + 1][d + 1] - sum[a][d + 1] - sum[c + 1][b] + sum[a][b];
        ans[i][j] = tot / cnt;
      }
    }
    return ans;
  }

}

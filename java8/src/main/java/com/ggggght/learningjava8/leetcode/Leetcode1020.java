package com.ggggght.learningjava8.leetcode;

import java.util.Arrays;
/**
 <p>给你一个大小为 <code>m x n</code> 的二进制矩阵 <code>grid</code> ，其中 <code>0</code> 表示一个海洋单元格、<code>1</code> 表示一个陆地单元格。</p>

 <p>一次 <strong>移动</strong> 是指从一个陆地单元格走到另一个相邻（<strong>上、下、左、右</strong>）的陆地单元格或跨过 <code>grid</code> 的边界。</p>

 <p>返回网格中<strong> 无法 </strong>在任意次数的移动中离开网格边界的陆地单元格的数量。</p>

 <p>&nbsp;</p>

 <p><strong>示例 1：</strong></p>
 <img alt="" src="https://assets.leetcode.com/uploads/2021/02/18/enclaves1.jpg" style="height: 200px; width: 200px;" />
 <pre>
 <strong>输入：</strong>grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 <strong>输出：</strong>3
 <strong>解释：</strong>有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
 </pre>

 <p><strong>示例 2：</strong></p>
 <img alt="" src="https://assets.leetcode.com/uploads/2021/02/18/enclaves2.jpg" style="height: 200px; width: 200px;" />
 <pre>
 <strong>输入：</strong>grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 <strong>输出：</strong>0
 <strong>解释：</strong>所有 1 都在边界上或可以到达边界。
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>m == grid.length</code></li>
 <li><code>n == grid[i].length</code></li>
 <li><code>1 &lt;= m, n &lt;= 500</code></li>
 <li><code>grid[i][j]</code> 的值为 <code>0</code> 或 <code>1</code></li>
 </ul>
 <div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>并查集</li><li>数组</li><li>矩阵</li></div></div><br><div><li>👍 88</li><li>👎 0</li></div>
 */

public class Leetcode1020 {
  public static void main(String[] args) {
    System.out.println(
        numEnclaves(new int[][] {{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}}));
    var arr = new int[][] {
        {0, 0, 0, 1, 1, 1, 0, 1, 0, 0},
        {1, 1, 0, 0, 0, 1, 0, 1, 1, 1},
        {0, 0, 0, 1, 1, 1, 0, 1, 0, 0},
        {0, 1, 1, 0, 0, 0, 1, 0, 1, 0},
        {0, 1, 1, 1, 1, 1, 0, 0, 1, 0},
        {0, 0, 1, 0, 1, 1, 1, 1, 0, 1},
        {0, 1, 1, 0, 0, 0, 1, 1, 1, 1},
        {0, 0, 1, 0, 0, 1, 0, 1, 0, 1},
        {1, 0, 1, 0, 1, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 0, 0, 0, 1}};
    System.out.println(numEnclaves(arr));
  }

  public static int numEnclaves(int[][] grid) {
    var ans = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int i1 = 0; i1 < grid[i].length; i1++) {
        int dfs = dfs(grid, i, i1);
        if(dfs != -1) {
          ans += dfs;
        }
      }
    }

    for (int[] ints : grid) {

      System.out.println(Arrays.toString(ints));
    }
    return ans;
  }

  static int dfs(int[][] grid, int i, int j) {
    if (!inArea(grid, i, j) || grid[i][j] != 1) return 0;

    grid[i][j] = 2;
    int tmp = 1;
    var tmpD = dfs(grid, i, j - 1);
    var tmpL = dfs(grid, i, j + 1);
    var tmpA = dfs(grid, i + 1, j);
    var tmpF = dfs(grid, i - 1, j);
    if(tmpD == -1 || tmpL == -1 || tmpA == -1 || tmpF == -1) {
      return -1;
    }
    if (i == 0 || i == grid.length - 1 || j == 0 || j == grid[0].length - 1) {
      return -1;
    }
    return tmpD + tmpL + tmpA + tmpF + tmp;
  }

  static boolean inArea(int[][] grid, int r, int c) {
    return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length;
  }
}

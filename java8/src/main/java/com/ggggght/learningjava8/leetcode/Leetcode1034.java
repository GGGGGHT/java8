package com.ggggght.learningjava8.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * <p>给你一个大小为 <code>m x n</code> 的整数矩阵 <code>grid</code> ，表示一个网格。另给你三个整数&nbsp;<code>row</code>、<code>col</code> 和 <code>color</code> 。网格中的每个值表示该位置处的网格块的颜色。</p>
 *
 * <p>当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一<strong> 连通分量 </strong>。</p>
 *
 * <p><strong>连通分量的边界</strong><strong> </strong>是指连通分量中的所有与不在分量中的网格块相邻（四个方向上）的所有网格块，或者在网格的边界上（第一行/列或最后一行/列）的所有网格块。</p>
 *
 * <p>请你使用指定颜色&nbsp;<code>color</code> 为所有包含网格块&nbsp;<code>grid[row][col]</code> 的 <strong>连通分量的边界</strong> 进行着色，并返回最终的网格&nbsp;<code>grid</code> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
 * <strong>输出：</strong>[[3,3],[3,2]]</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
 * <strong>输出：</strong>[[1,3,3],[2,3,3]]</pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
 * <strong>输出：</strong>[[2,2,2],[2,1,2],[2,2,2]]</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == grid.length</code></li>
 * <li><code>n == grid[i].length</code></li>
 * <li><code>1 &lt;= m, n &lt;= 50</code></li>
 * <li><code>1 &lt;= grid[i][j], color &lt;= 1000</code></li>
 * <li><code>0 &lt;= row &lt; m</code></li>
 * <li><code>0 &lt;= col &lt; n</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 * <div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>数组</li><li>矩阵</li></div></div><br><div><li>👍 56</li><li>👎 0</li></div>
 */

public class Leetcode1034 {
  public static void main(String[] args) {
    Leetcode1034 leetcode1034 = new Leetcode1034();
    // int[][] arr = new int[][] {{1, 1, 1,}, {1, 1, 1}, {1, 1, 1}};
    int[][] arr = new int[][] {{1, 2,2}, {2, 3, 2}};
    // int[][] arr = new int[][] {{1, 1}, {1, 2}};
    int[][] res = leetcode1034.colorBorder(arr, 0, 1, 3);
    for (int[] re : res) {
      System.out.println(Arrays.toString(re));
    }
  }

  public int[][] colorBorder(int[][] grid, int row, int col, int color) {
    int m = grid.length, n = grid[0].length;
    int[][] ans = new int[m][n];
    int[][] dirs = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
    Deque<int[]> d = new ArrayDeque<>();
    d.addLast(new int[]{row, col});
    while (!d.isEmpty()) {
      int[] poll = d.pollFirst();
      int x = poll[0], y = poll[1], cnt = 0;
      for (int[] di : dirs) {
        int nx = x + di[0], ny = y + di[1];
        if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
        if (grid[x][y] != grid[nx][ny]) continue;
        else cnt++;
        if (ans[nx][ny] != 0) continue;
        d.addLast(new int[]{nx, ny});
      }
      ans[x][y] = cnt == 4 ? grid[x][y] : color;
    }
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (ans[i][j] == 0) ans[i][j] = grid[i][j];
      }
    }
    return ans;
  }
}

// 1 1
// 1 2
// ===
// 3 3
// 3 2

// ===
// 1 2 2
// 2 3 2
// ===
// 1 3 3
// 2 3 3

// 1 1 1
// 1 1 1
// 1 1 1
// ===
//

package com.ggggght.learningjava8.leetcode;

/**
 * <p>一个机器人位于一个 <em>m x n </em>网格的左上角 （起始点在下图中标记为“Start” ）。</p>
 *
 * <p>机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。</p>
 *
 * <p>现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？</p>
 *
 * <p><img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/robot_maze.png" style="height: 183px; width: 400px;" /></p>
 *
 * <p>网格中的障碍物和空位置分别用 <code>1</code> 和 <code>0</code> 来表示。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/04/robot1.jpg" style="width: 242px; height: 242px;" />
 * <pre>
 * <strong>输入：</strong>obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * <strong>输出：</strong>2
 * <strong>解释：</strong>
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 <code>2</code> 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/04/robot2.jpg" style="width: 162px; height: 162px;" />
 * <pre>
 * <strong>输入：</strong>obstacleGrid = [[0,1],[0,0]]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == obstacleGrid.length</code></li>
 * <li><code>n == obstacleGrid[i].length</code></li>
 * <li><code>1 <= m, n <= 100</code></li>
 * <li><code>obstacleGrid[i][j]</code> 为 <code>0</code> 或 <code>1</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li><li>矩阵</li></div></div><br><div><li>👍 696</li><li>👎 0</li></div>
 */

public class Leetcode63 {
  public static void main(String[] args) {
    int[][] arr = {{0}};
    int[][] arr1 = {{1, 0}};
    System.out.println(uniquePathsWithObstacles(arr));
    System.out.println(uniquePathsWithObstacles(arr1));
  }

  public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int line = obstacleGrid.length;
    int col = obstacleGrid[0].length;
    if (obstacleGrid[0][0] == 1) return 0;
    if (col == line && col == 1 && obstacleGrid[line - 1][col - 1] == 0) return 1;
    int[][] dp = new int[line][col];
    if (col > 1) {
      dp[0][1] = obstacleGrid[0][1] == 1 ? 0 : 1;
    }
    if (line > 1) {
      dp[1][0] = obstacleGrid[1][0] == 1 ? 0 : 1;
    }
    for (int i = 0; i < line; i++) {
      for (int j = 0; j < col; j++) {
        if (i == 0 && j == 1 || i == 1 && j == 0) continue;
        int up = i - 1 >= 0 ? dp[i - 1][j] : 0;
        int left = j - 1 >= 0 ? dp[i][j - 1] : 0;
        if (obstacleGrid[i][j] != 1) {
          dp[i][j] = up + left;
        }
      }
    }
    return dp[line - 1][col - 1];
  }
}

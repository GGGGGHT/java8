package com.ggggght.learningjava8.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 <p>给你一个 <code>m * n</code> 的矩阵，矩阵中的数字 <strong>各不相同</strong> 。请你按 <strong>任意</strong> 顺序返回矩阵中的所有幸运数。</p>

 <p>幸运数是指矩阵中满足同时下列两个条件的元素：</p>

 <ul>
 <li>在同一行的所有元素中最小</li>
 <li>在同一列的所有元素中最大</li>
 </ul>

 <p>&nbsp;</p>

 <p><strong>示例 1：</strong></p>

 <pre><strong>输入：</strong>matrix = [[3,7,8],[9,11,13],[15,16,17]]
 <strong>输出：</strong>[15]
 <strong>解释：</strong>15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 </pre>

 <p><strong>示例 2：</strong></p>

 <pre><strong>输入：</strong>matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
 <strong>输出：</strong>[12]
 <strong>解释：</strong>12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 </pre>

 <p><strong>示例 3：</strong></p>

 <pre><strong>输入：</strong>matrix = [[7,8],[1,2]]
 <strong>输出：</strong>[7]
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>m == mat.length</code></li>
 <li><code>n == mat[i].length</code></li>
 <li><code>1 &lt;= n, m &lt;= 50</code></li>
 <li><code>1 &lt;=&nbsp;matrix[i][j]&nbsp;&lt;= 10^5</code></li>
 <li>矩阵中的所有元素都是不同的</li>
 </ul>
 <div><div>Related Topics</div><div><li>数组</li><li>矩阵</li></div></div><br><div><li>👍 71</li><li>👎 0</li></div>
 */

public class Leetcode1380 {
  public static void main(String[] args) {
    System.out.println(luckyNumbers(new int[][] {{3, 7, 8}, {9, 11, 13}, {15, 16, 17}}));
    System.out.println(luckyNumbers(new int[][] {{1,10,4,2},{9,3,8,7},{15,16,17,12}}));
    System.out.println(luckyNumbers(new int[][] {{7, 8}, {1, 2}}));
    System.out.println(luckyNumbers(new int[][] {{7, 8}, {10, 2}}));
  }
  // 在同一行的所有元素中最小
  // 在同一列的所有元素中最大
  public static List<Integer> luckyNumbers (int[][] matrix) {
    ArrayList<Integer> ans = new ArrayList<>();
    for (int[] ints : matrix) {
      var min = Integer.MAX_VALUE ;
      int minIdx = -1;
      // 在同一行的所有元素中最小
      for (int i = 0; i < ints.length; i++) {
        if (min > ints[i]) {
          minIdx = i;
          min = ints[i];
        }
      }

      // 在同一列的所有元素中最大
      var tmpMax = min;
      for (int[] ints1 : matrix) {
        tmpMax = Math.max(ints1[minIdx], tmpMax);
      }

      if (tmpMax == min) {
        ans.add(tmpMax);
      }

    }

    return ans;
  }
}

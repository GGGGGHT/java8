package com.ggggght.learningjava8.leetcode;

import java.util.Arrays;
/**
 <p>给你一个大小为 <code>m x n</code> 的矩阵 <code>mat</code> ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。</p>

 <p>&nbsp;</p>

 <p><strong>示例 1：</strong></p>
 <img alt="" src="https://assets.leetcode.com/uploads/2021/04/10/diag1-grid.jpg" style="width: 334px; height: 334px;" />
 <pre>
 <strong>输入：</strong>mat = [[1,2,3],[4,5,6],[7,8,9]]
 <strong>输出：</strong>[1,2,4,7,5,3,6,8,9]
 </pre>

 <p><strong>示例 2：</strong></p>

 <pre>
 <strong>输入：</strong>mat = [[1,2],[3,4]]
 <strong>输出：</strong>[1,2,3,4]
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>m == mat.length</code></li>
 <li><code>n == mat[i].length</code></li>
 <li><code>1 &lt;= m, n &lt;= 10<sup>4</sup></code></li>
 <li><code>1 &lt;= m * n &lt;= 10<sup>4</sup></code></li>
 <li><code>-10<sup>5</sup> &lt;= mat[i][j] &lt;= 10<sup>5</sup></code></li>
 </ul>
 <div><div>Related Topics</div><div><li>数组</li><li>矩阵</li><li>模拟</li></div></div><br><div><li>👍 258</li><li>👎 0</li></div>
 */

public class Leetcode498 {
  public static void main(String[] args) {
    // var arr = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    var arr = new int[][] {{1, 2}, {3, 4}};
    System.out.println(Arrays.toString(findDiagonalOrder(arr)));
  }

  public static int[] findDiagonalOrder(int[][] mat) {
    int endR = mat.length - 1, endC = mat[0].length - 1;
    // 用来标识从哪边开始打印
    int[] res = new int[(endR + 1) * (endC + 1)];
    int idx = 0;
    int tR = 0, tC = 0, dR = 0, dC = 0;
    boolean fromUp = false;
    while (tR != endR + 1) {
      int ttr = tR, ttc = tC, tdr = dR, tdc = dC;
      if (fromUp) {
        while (ttr != dR + 1) {
          res[idx++] = mat[ttr++][ttc--];
        }
      } else {
        while (tdr != tR - 1) {
          res[idx++] = mat[tdr--][tdc++];
        }
      }

      tR = tC == endC ? tR + 1 : tR;
      tC = tC == endC ? tC : tC + 1;
      dC = dR == endR ? dC + 1 : dC;
      dR = dR == endR ? dR : dR + 1;
      fromUp = !fromUp;
    }

    return res;
  }
}
// 1 | 0,0 -> -1,1
// 0 | 0,1 -> 1,0 -> 2,-1
// 1 | 2,0 -> 1,1 -> 0,2 -> -1,3
// 0 | 1,2 -> 2,1 -> 3,0
// 1 | 2,2 -> 1,4
//       0 1
//      ----
//  0  | 1 2
//  1  | 3 4
//   2 -1
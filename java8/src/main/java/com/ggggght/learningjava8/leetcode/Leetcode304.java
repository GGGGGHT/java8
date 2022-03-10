package com.ggggght.learningjava8.leetcode;

import java.util.Arrays;

/**
 <p><big><small>给定一个二维矩阵 <code>matrix</code>，</small></big>以下类型的多个请求：</p>

 <ul>
 <li><big><small>计算其子矩形范围内元素的总和，该子矩阵的 <strong>左上角</strong> 为 <code>(row1,&nbsp;col1)</code> ，<strong>右下角</strong> 为 <code>(row2,&nbsp;col2)</code> 。</small></big></li>
 </ul>

 <p>实现 <code>NumMatrix</code> 类：</p>

 <ul>
 <li><code>NumMatrix(int[][] matrix)</code>&nbsp;给定整数矩阵 <code>matrix</code> 进行初始化</li>
 <li><code>int sumRegion(int row1, int col1, int row2, int col2)</code>&nbsp;返回<big><small> <strong>左上角</strong></small></big><big><small> <code>(row1,&nbsp;col1)</code>&nbsp;、<strong>右下角</strong>&nbsp;<code>(row2,&nbsp;col2)</code></small></big> 所描述的子矩阵的元素 <strong>总和</strong> 。</li>
 </ul>

 <p>&nbsp;</p>

 <p><strong>示例 1：</strong></p>

 <p><img src="https://pic.leetcode-cn.com/1626332422-wUpUHT-image.png" style="width: 200px;" /></p>

 <pre>
 <strong>输入:</strong>
 ["NumMatrix","sumRegion","sumRegion","sumRegion"]
 [[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,2,2],[1,2,2,4]]
 <strong>输出:</strong>
 [null, 8, 11, 12]

 <strong>解释:</strong>
 NumMatrix numMatrix = new NumMatrix([[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]);
 numMatrix.sumRegion(2, 1, 4, 3); // return 8 (红色矩形框的元素总和)
 numMatrix.sumRegion(1, 1, 2, 2); // return 11 (绿色矩形框的元素总和)
 numMatrix.sumRegion(1, 2, 2, 4); // return 12 (蓝色矩形框的元素总和)
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>m == matrix.length</code></li>
 <li><code>n == matrix[i].length</code></li>
 <li><code>1 &lt;= m,&nbsp;n &lt;=&nbsp;200</code><meta charset="UTF-8" /></li>
 <li><code>-10<sup>5</sup>&nbsp;&lt;= matrix[i][j] &lt;= 10<sup>5</sup></code></li>
 <li><code>0 &lt;= row1 &lt;= row2 &lt; m</code></li>
 <li><code>0 &lt;= col1 &lt;= col2 &lt; n</code></li>
 <li><meta charset="UTF-8" />最多调用 <code>10<sup>4</sup></code> 次&nbsp;<code>sumRegion</code> 方法</li>
 </ul>
 <div><div>Related Topics</div><div><li>设计</li><li>数组</li><li>矩阵</li><li>前缀和</li></div></div><br><div><li>👍 354</li><li>👎 0</li></div>
 */

public class Leetcode304 {

  class NumMatrix {
    int[][] arr;
    int[][] matrix;

    public NumMatrix(int[][] matrix) {
      this.matrix = matrix;
      arr = new int[matrix.length][matrix[0].length];
      for (int i = 0; i < arr.length; i++) {
        for (int i1 = 0; i1 < arr[i].length; i1++) {
          arr[i][i1] = i == 0 && i1 == 0 ? matrix[0][0]
              : (i1 - 1 < 0 ? arr[i - 1][arr[i - 1].length - 1] : arr[i][i1 - 1]) + matrix[i][i1];
        }
      }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
      if (row1 == row2 && col1 == col2) return matrix[row1][col1];
      if (row1 == 0 && col1 == 0 && col2 == matrix[0].length - 1) return arr[row2][col2];

      int ans = 0;
      for (int i = 0; i <= row2 - row1; i++) {
        var tmp = 0;
        if (i + row1 - 1 < 0 && col1 - 1 < 0) {
        } else if (col1 - 1 < 0) {
          tmp = arr[i + row1 - 1][arr[i].length - 1];
        } else {
          tmp = arr[i + row1][col1 - 1];
        }
        ans += arr[i + row1][col2] - tmp;
      }
      return ans;
    }
  }
  /**
   * <a href='https://leetcode-cn.com/problems/range-sum-query-2d-immutable/solution/er-wei-qian-zhui-he-jian-dan-tui-dao-tu-sqekv/'>二维</a>
   */
  class NumMatrix1 {
    int[][] arr;
    public int sumRegion(int row1, int col1, int row2, int col2) {
      return this.arr[row2 + 1][col2 + 1] + this.arr[row1][col1]
          - this.arr[row1][col2 + 1]
          - this.arr[row2 + 1][col1];
    }
    public  NumMatrix1(int[][] matrix) {
      arr = new int[matrix.length+1][matrix[0].length+1];
      for (int i = 1; i < arr.length; i++) {
        for (int i1 = 1; i1 < arr[0].length; i1++) {
          arr[i][i1] = arr[i - 1][i1] + arr[i][i1 - 1] - arr[i - 1][i1 - 1] + matrix[i-1][i1-1];
        }
      }

      for (int[] ints : arr) {
        System.out.println(Arrays.toString(ints));
      }
    }
  }

}

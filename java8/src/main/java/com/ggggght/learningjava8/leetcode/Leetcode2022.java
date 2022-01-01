package com.ggggght.learningjava8.leetcode;

import java.util.Arrays;

/**
 * <p>给你一个下标从 <strong>0</strong>&nbsp;开始的一维整数数组&nbsp;<code>original</code>&nbsp;和两个整数&nbsp;<code>m</code>&nbsp;和&nbsp;&nbsp;<code>n</code>&nbsp;。你需要使用&nbsp;<code>original</code>&nbsp;中&nbsp;<strong>所有</strong>&nbsp;元素创建一个&nbsp;<code>m</code>&nbsp;行&nbsp;<code>n</code>&nbsp;列的二维数组。</p>
 *
 * <p><code>original</code>&nbsp;中下标从 <code>0</code>&nbsp;到 <code>n - 1</code>&nbsp;（都 <strong>包含</strong> ）的元素构成二维数组的第一行，下标从 <code>n</code>&nbsp;到 <code>2 * n - 1</code>&nbsp;（都 <strong>包含</strong>&nbsp;）的元素构成二维数组的第二行，依此类推。</p>
 *
 * <p>请你根据上述过程返回一个<em>&nbsp;</em><code>m x n</code>&nbsp;的二维数组。如果无法构成这样的二维数组，请你返回一个空的二维数组。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img src="https://assets.leetcode.com/uploads/2021/08/26/image-20210826114243-1.png" style="width: 500px; height: 174px;">
 * <pre><b>输入：</b>original = [1,2,3,4], m = 2, n = 2
 * <b>输出：</b>[[1,2],[3,4]]
 * <strong>解释：
 * </strong>构造出的二维数组应该包含 2 行 2 列。
 * original 中第一个 n=2 的部分为 [1,2] ，构成二维数组的第一行。
 * original 中第二个 n=2 的部分为 [3,4] ，构成二维数组的第二行。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><b>输入：</b>original = [1,2,3], m = 1, n = 3
 * <b>输出：</b>[[1,2,3]]
 * <b>解释：</b>
 * 构造出的二维数组应该包含 1 行 3 列。
 * 将 original 中所有三个元素放入第一行中，构成要求的二维数组。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre><b>输入：</b>original = [1,2], m = 1, n = 1
 * <b>输出：</b>[]
 * <strong>解释：
 * </strong>original 中有 2 个元素。
 * 无法将 2 个元素放入到一个 1x1 的二维数组中，所以返回一个空的二维数组。
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre><b>输入：</b>original = [3], m = 1, n = 2
 * <b>输出：</b>[]
 * <strong>解释：</strong>
 * original 中只有 1 个元素。
 * 无法将 1 个元素放满一个 1x2 的二维数组，所以返回一个空的二维数组。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= original.length &lt;= 5 * 10<sup>4</sup></code></li>
 * <li><code>1 &lt;= original[i] &lt;= 10<sup>5</sup></code></li>
 * <li><code>1 &lt;= m, n &lt;= 4 * 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>矩阵</li><li>模拟</li></div></div><br><div><li>👍 47</li><li>👎 0</li></div>
 */

public class Leetcode2022 {

  public static int[][] construct2DArray(int[] original, int m, int n) {
    var len = original.length;
    if (len != m * n) {
      return new int[m][];
    }

    int[][] res = new int[m][n];
    for (int i = 0; i < m; i++) {
      res[i] = Arrays.copyOfRange(original, i == 0 ? 0 : n * i, (i + 1) * n);
    }
    return res;
  }
}

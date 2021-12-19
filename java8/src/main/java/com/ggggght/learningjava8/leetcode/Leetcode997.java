package com.ggggght.learningjava8.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import org.springframework.util.AntPathMatcher;

/**
 * <p>在一个小镇里，按从 <code>1</code> 到 <code>n</code> 为 <code>n</code> 个人进行编号。传言称，这些人中有一个是小镇上的秘密法官。</p>
 *
 * <p>如果小镇的法官真的存在，那么：</p>
 *
 * <ol>
 * <li>小镇的法官不相信任何人。</li>
 * <li>每个人（除了小镇法官外）都信任小镇的法官。</li>
 * <li>只有一个人同时满足条件 1 和条件 2 。</li>
 * </ol>
 *
 * <p>给定数组 <code>trust</code>，该数组由信任对 <code>trust[i] = [a, b]</code> 组成，表示编号为 <code>a</code> 的人信任编号为 <code>b</code> 的人。</p>
 *
 * <p>如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的编号。否则，返回 <code>-1</code>。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 2, trust = [[1,2]]
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 3, trust = [[1,3],[2,3]]
 * <strong>输出：</strong>3
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 3, trust = [[1,3],[2,3],[3,1]]
 * <strong>输出：</strong>-1
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 3, trust = [[1,2],[2,3]]
 * <strong>输出：</strong>-1
 * </pre>
 *
 * <p><strong>示例 5：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
 * <strong>输出：</strong>3</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= n <= 1000</code></li>
 * <li><code>0 <= trust.length <= 10<sup>4</sup></code></li>
 * <li><code>trust[i].length == 2</code></li>
 * <li><code>trust[i]</code> 互不相同</li>
 * <li><code>trust[i][0] != trust[i][1]</code></li>
 * <li><code>1 <= trust[i][0], trust[i][1] <= n</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>图</li><li>数组</li><li>哈希表</li></div></div><br><div><li>👍 194</li><li>👎 0</li></div>
 */

public class Leetcode997 {
  public static void main(String[] args) {
    var arr = new int[][] {{1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}};
    // var arr = new int[][] {{1, 2}, {2, 3}};
    // var arr = new int[][] {{1,2}, {2, 3}};
    System.out.println(findJudge(4, arr));
  }

  // 1 2
  // 2 3
  // 0010
  // 0001
  // 0000
  public static int findJudge(int n, int[][] trust) {
    // 入度
    int[] in = new int[n + 1];
    // 矩阵
    int[][] m = new int[n + 1][n + 1];

    for (int[] ints : trust) {
      m[ints[0]][ints[1]] = 1;
      in[ints[1]]++;
    }

    // System.out.println("Arrays.toString(in) = " + Arrays.toString(in));
    int res = -1;
    // 将所有入度不为0的都压入栈中
    for (int i = 1; i < m.length; i++) {
      boolean findNonZero = false;
      for (int i1 = 0; i1 < m[i].length; i1++) {
        if (m[i][i1] != 0) {
          findNonZero = true;
          break;
        }
      }

      if (!findNonZero) {
        res = i;
      }
    }

    return res == -1 ? -1 : in[res] == n - 1 ? res : -1;
  }

  /**
   * 令 mm 为 trust 数组长度，对于每个 trust[i] = (a, b)trust[i]=(a,b) 而言，看作是从 aa 指向 bb 的有向边。
   *
   * 遍历 trust，统计每个节点的「入度」和「出度」：若存在 a -> ba−>b，则 aa 节点「出度」加一，bb 节点「入度」加一。
   *
   * 最后遍历所有点，若存在「入度」数量为 n - 1n−1，且「出度」数量为 00 的节点即是法官。
   *
   * @param n
   * @param trust
   * @return
   */
  public int findJudge1(int n, int[][] trust) {
    int[] in = new int[n + 1], out = new int[n + 1];
    for (int[] t : trust) {
      int a = t[0], b = t[1];
      in[b]++; out[a]++;
    }
    for (int i = 1; i <= n; i++) {
      if (in[i] == n - 1 && out[i] == 0) return i;
    }
    return -1;
  }
}

// 0 1 2 3 4
// 0 0 0 3 2
package com.ggggght.learningjava8.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * <p>你正在参加一个多角色游戏，每个角色都有两个主要属性：<strong>攻击</strong> 和 <strong>防御</strong> 。给你一个二维整数数组 <code>properties</code> ，其中 <code>properties[i] = [attack<sub>i</sub>, defense<sub>i</sub>]</code> 表示游戏中第 <code>i</code> 个角色的属性。</p>
 *
 * <p>如果存在一个其他角色的攻击和防御等级 <strong>都严格高于</strong> 该角色的攻击和防御等级，则认为该角色为 <strong>弱角色</strong> 。更正式地，如果认为角色 <code>i</code> <strong>弱于</strong> 存在的另一个角色 <code>j</code> ，那么 <code>attack<sub>j</sub> &gt; attack<sub>i</sub></code> 且 <code>defense<sub>j</sub> &gt; defense<sub>i</sub></code> 。</p>
 *
 * <p>返回 <strong>弱角色</strong> 的数量。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>properties = [[5,5],[6,3],[3,6]]
 * <strong>输出：</strong>0
 * <strong>解释：</strong>不存在攻击和防御都严格高于其他角色的角色。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>properties = [[2,2],[3,3]]
 * <strong>输出：</strong>1
 * <strong>解释：</strong>第一个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>properties = [[1,5],[10,4],[4,3]]
 * <strong>输出：</strong>1
 * <strong>解释：</strong>第三个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>2 &lt;= properties.length &lt;= 10<sup>5</sup></code></li>
 * <li><code>properties[i].length == 2</code></li>
 * <li><code>1 &lt;= attack<sub>i</sub>, defense<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>栈</li><li>贪心</li><li>数组</li><li>排序</li><li>单调栈</li></div></div><br><div><li>👍 63</li><li>👎 0</li></div>
 */

public class Leetcode1996 {
  public static void main(String[] args) {
    // System.out.println(numberOfWeakCharacters(new int[][] {{5, 5}, {6, 3}, {3, 6}}));
    // System.out.println(numberOfWeakCharacters(new int[][] {{2, 2}, {3, 3}}));
    // System.out.println(numberOfWeakCharacters(new int[][] {{1, 5}, {10, 4}, {4, 3}}));
    System.out.println(numberOfWeakCharacters(
        new int[][] {{7, 7}, {1, 2}, {9, 7}, {7, 3}, {3, 10}, {9, 8}, {8, 10}, {4, 3}, {1, 5},
            {1, 5}}));
  }

  /**
   * 暴力解 将所有的数据都映射到一个二维数组中, 当遍历时发现有比这[i][j]大的就将其节点上的数增加后置0 超出内存限制
   *
   * @param properties
   * @return
   */
  public static int numberOfWeakCharacters(int[][] properties) {
    var len = 0;
    var col = 0;
    for (int[] property : properties) {
      len = Math.max(len, property[0]);
      col = Math.max(col, property[1]);
    }
    int[][] arr = new int[len + 1][col + 1];
    var res = 0;
    for (int[] property : properties) {
      arr[property[0]][property[1]]++;
    }

    for (int[] ints : arr) {
      System.out.println(Arrays.toString(ints));
    }

    for (int i = 0; i < arr.length; i++) {
      for (int i1 = 0; i1 < arr[i].length; i1++) {
        if (arr[i][i1] > 0) {
          for (int j = 0; j < i; j++) {
            for (int x = 0; x < i1; x++) {
              if (arr[j][x] > 0) {
                res += arr[j][x];
                arr[j][x] = 0;
              }
            }
          }
        }
      }
    }
    return res;
  }

  public int numberOfWeakCharacters2(int[][] properties) {
    // 先按攻击力排序（升序），然后倒序遍历，记录后面最大的防御力
    // 这里排序要注意一下，如果有相同攻击力的，我们让防御力低的排在后面
    // 这样就可以避免出现 [攻击力相同，防御力不同] 数据的干扰
    Arrays.sort(properties, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

    // 反向遍历，只要后面有更大的防御力，当前角色就是弱角色
    int ans = 0, max = 0;
    for (int i = properties.length - 1; i >= 0; i--) {
      if (properties[i][1] < max) {
        ans++;
      } else {
        max = properties[i][1];
      }
    }

    return ans;
  }
}

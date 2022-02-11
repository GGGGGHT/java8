package com.ggggght.learningjava8.leetcode;

import java.util.Arrays;

/**
 * <p>给你一个 <strong>下标从 0 开始</strong> 的整数数组 <code>nums</code> ，其中 <code>nums[i]</code> 表示第 <code>i</code> 名学生的分数。另给你一个整数 <code>k</code> 。</p>
 *
 * <p>从数组中选出任意 <code>k</code> 名学生的分数，使这 <code>k</code> 个分数间 <strong>最高分</strong> 和 <strong>最低分</strong> 的 <strong>差值</strong> 达到<strong> 最小化</strong> 。</p>
 *
 * <p>返回可能的 <strong>最小差值</strong> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>nums = [90], k = 1
 * <strong>输出：</strong>0
 * <strong>解释：</strong>选出 1 名学生的分数，仅有 1 种方法：
 * - [<em><strong>90</strong></em>] 最高分和最低分之间的差值是 90 - 90 = 0
 * 可能的最小差值是 0
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>nums = [9,4,1,7], k = 2
 * <strong>输出：</strong>2
 * <strong>解释：</strong>选出 2 名学生的分数，有 6 种方法：
 * - [<em><strong>9</strong></em>,<em><strong>4</strong></em>,1,7] 最高分和最低分之间的差值是 9 - 4 = 5
 * - [<em><strong>9</strong></em>,4,<em><strong>1</strong></em>,7] 最高分和最低分之间的差值是 9 - 1 = 8
 * - [<em><strong>9</strong></em>,4,1,<em><strong>7</strong></em>] 最高分和最低分之间的差值是 9 - 7 = 2
 * - [9,<em><strong>4</strong></em>,<em><strong>1</strong></em>,7] 最高分和最低分之间的差值是 4 - 1 = 3
 * - [9,<em><strong>4</strong></em>,1,<em><strong>7</strong></em>] 最高分和最低分之间的差值是 7 - 4 = 3
 * - [9,4,<em><strong>1</strong></em>,<em><strong>7</strong></em>] 最高分和最低分之间的差值是 7 - 1 = 6
 * 可能的最小差值是 2</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= k &lt;= nums.length &lt;= 1000</code></li>
 * <li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>排序</li><li>滑动窗口</li></div></div><br><div><li>👍 25</li><li>👎 0</li></div>
 */

public class Leetcode1984 {
  public static void main(String[] args) {
    System.out.println(minimumDifference(new int[] {9, 4, 1, 7}, 2));
  }

  /**
   * 排序+滑动窗口
   *
   * @param nums
   * @param k
   * @return
   */
  public static int minimumDifference(int[] nums, int k) {
    if (k == 1) return 0;
    Arrays.sort(nums);
    int res = nums[k - 1] - nums[0];
    for (int i = k; i < nums.length; i++) {
      res = Math.min(res, nums[i] - nums[i - k + 1]);
    }
    return res;
  }
}

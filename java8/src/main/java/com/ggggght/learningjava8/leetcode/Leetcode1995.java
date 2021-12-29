package com.ggggght.learningjava8.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>给你一个 <strong>下标从 0 开始</strong> 的整数数组 <code>nums</code> ，返回满足下述条件的 <strong>不同</strong> 四元组 <code>(a, b, c, d)</code> 的 <strong>数目</strong> ：</p>
 *
 * <ul>
 * <li><code>nums[a] + nums[b] + nums[c] == nums[d]</code> ，且</li>
 * <li><code>a &lt; b &lt; c &lt; d</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>nums = [1,2,3,6]
 * <strong>输出：</strong>1
 * <strong>解释：</strong>满足要求的唯一一个四元组是 (0, 1, 2, 3) 因为 1 + 2 + 3 == 6 。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>nums = [3,3,6,4,5]
 * <strong>输出：</strong>0
 * <strong>解释：</strong>[3,3,6,4,5] 中不存在满足要求的四元组。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre><strong>输入：</strong>nums = [1,1,1,3,5]
 * <strong>输出：</strong>4
 * <strong>解释：</strong>满足要求的 4 个四元组如下：
 * - (0, 1, 2, 3): 1 + 1 + 1 == 3
 * - (0, 1, 3, 4): 1 + 1 + 3 == 5
 * - (0, 2, 3, 4): 1 + 1 + 3 == 5
 * - (1, 2, 3, 4): 1 + 1 + 3 == 5
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>4 &lt;= nums.length &lt;= 50</code></li>
 * <li><code>1 &lt;= nums[i] &lt;= 100</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>枚举</li></div></div><br><div><li>👍 48</li><li>👎 0</li></div>
 */

public class Leetcode1995 {
  public static void main(String[] args) {
    Leetcode1995 leetcode1995 = new Leetcode1995();
    // System.out.println(leetcode1995.countQuadruplets(new int[] {9, 6, 8, 23, 39, 23}));
    System.out.println(leetcode1995.countQuadruplets2(new int[] {28, 8, 49, 85, 37, 90, 20, 8}));
  }

  public int countQuadruplets(int[] nums) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }
    List<Integer> list = new ArrayList<>();
    List<List<Integer>> ll = new ArrayList<>();
    Set<Integer> set = new HashSet<>();
    traceBack(list, map, nums, 0, ll);

    System.out.println(ll);
    return ll.size();
  }

  void traceBack(List<Integer> list, HashMap<Integer, Integer> map, int[] nums, int startIdx,
      List<List<Integer>> ll) {
    if (list.size() == 3) {
      int sum = list.stream().mapToInt(i -> i).sum();

      Integer integer = map.get(sum);
      if (integer != null) {
        while (integer-- > 0) {
          ll.add(new ArrayList<>(list));
        }
      }
    }

    for (int i = startIdx; i < nums.length; i++) {
      list.add(nums[i]);
      traceBack(list, map, nums, i + 1, ll);
      list.remove(list.size() - 1);
    }
  }

  /**
   * 从最后开始 将nums[d]映射到数组中 判断前面是否有nums[a]+nums[b]+nums[c] = nums[d]
   * @param nums
   * @return
   */
  public int countQuadruplets2(int[] nums) {
    int n = nums.length, ans = 0;
    int[] cnt = new int[5001];
    for (int c = n - 2; c >= 2; c--) {
      cnt[nums[c + 1]]++;
      for (int a = 0; a < n; a++) {
        for (int b = a + 1; b < c; b++) {
          ans += cnt[nums[a] + nums[b] + nums[c]];
        }
      }
    }
    return ans;
  }

}
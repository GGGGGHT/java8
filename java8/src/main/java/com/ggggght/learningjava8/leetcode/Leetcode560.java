package com.ggggght.learningjava8.leetcode;

import java.util.HashMap;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 * 提示：
 * <p>
 * <p>
 * 1 <= nums.length <= 2 * 10⁴
 * -1000 <= nums[i] <= 1000
 * -10⁷ <= k <= 10⁷
 * <p>
 * Related Topics 数组 哈希表 前缀和 👍 1203 👎 0
 */
public class Leetcode560 {
  public static void main(String[] args) {
    Leetcode560 leetcode560 = new Leetcode560();
    var arr = new int[] {1,2, 3};
    System.out.println(leetcode560.subarraySum2(arr, 3));
  }

  // lowB解法
  public int subarraySum(int[] nums, int k) {
    var res = 0;
    for (int i = 0; i < nums.length; i++) {
      int tmp = nums[i];
      if (tmp == k) res++;
      for (int j = i + 1; j < nums.length; j++) {
        tmp += nums[j];
        if (tmp == k) {
          res++;
        }
      }
    }
    return res;
  }

  /**
   * 前缀和+HashMap
   *
   * @param nums
   * @param k
   * @return
   */
  public int subarraySum2(int[] nums, int k) {
    int count = 0, pre = 0;
    HashMap<Integer, Integer> mp = new HashMap<>();
    mp.put(0, 1);
    for (int i = 0; i < nums.length; i++) {
      pre += nums[i];
      if (mp.containsKey(pre - k)) {
        count += mp.get(pre - k);
      }
      mp.put(pre, mp.getOrDefault(pre, 0) + 1);
    }
    return count;
  }
}

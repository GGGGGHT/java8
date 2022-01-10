package com.ggggght.learningjava8.leetcode;

/**
 * <p>给你一个整数数组 <code>nums</code>&nbsp;，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre><strong>输入:</strong> [2,3,-2,4]
 * <strong>输出:</strong> <code>6</code>
 * <strong>解释:</strong>&nbsp;子数组 [2,3] 有最大乘积 6。
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre><strong>输入:</strong> [-2,0,-1]
 * <strong>输出:</strong> 0
 * <strong>解释:</strong>&nbsp;结果不能为 2, 因为 [-2,-1] 不是子数组。</pre>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 1443</li><li>👎 0</li></div>
 */

public class Leetcode152 {
  public static void main(String[] args) {
    System.out.println(maxProduct(new int[] {2, 3, -2, 4}));
  }

  /**
   * 所有子数组都会以某一个位置结束,只要求出以每个位置结尾的子数组最大的累乘积,在最大累乘积中最大的就是最终结果.
   * 如何求出所以以i位置结尾的子数组的最大乘积? 假设以arr[i-1]为结尾的最小累乘积为min,以arr[i-1]结尾的最大累乘积为max
   * 那么,以arr[i]结尾的最大累乘积只有3种可能:
   * 1. 可能是max*arr[i].max表示以arr[i-1]结尾的最大累乘积,那么当然有可能以arr[i]结尾的最大累乘积是arr[i]*max eg: [3,4,5]在算到5的时候
   * 2. 可能是min*arr[i],min有可能是负数,如果arr[i]也是负数,两个负数相乘的结果也可能很大,eg: [-2,3,-4]在算到-4的时候
   * 3. 可以是arr[i],以arr[i]结尾的最大累乘积并不一定要包含arr[i]前面的数.eg: [0,0,100]
   * @param nums
   * @return
   */
  public static int maxProduct(int[] nums) {
    int len = nums.length;

    int max = nums[0], min = nums[0];
    int res = nums[0];
    int maxEnd = 0, minEnd = 0;
    for (int i = 1; i < len; i++) {
      maxEnd = max * nums[i];
      minEnd = min * nums[i];
      max = Math.max(Math.max(maxEnd, minEnd), nums[i]);
      min = Math.min(Math.min(maxEnd, minEnd), nums[i]);
      res = Math.max(res, max);
    }
    return res;
  }
}

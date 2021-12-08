package com.ggggght.learningjava8.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> ，找出三个长度为 <code>k</code> 、互不重叠、且&nbsp;<code>3 * k</code> 项的和最大的子数组，并返回这三个子数组。</p>
 *
 * <p>以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 <strong>0</strong> 开始）。如果有多个结果，返回字典序最小的一个。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,1,2,6,7,5,1], k = 2
 * <strong>输出：</strong>[0,3,5]
 * <strong>解释：</strong>子数组 [1, 2], [2, 6], [7, 5] 对应的起始下标为 [0, 3, 5]。
 * 也可以取 [2, 1], 但是结果 [1, 3, 5] 在字典序上更大。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,1,2,1,2,1,2,1], k = 2
 * <strong>输出：</strong>[0,2,4]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
 * <li><code>1 &lt;= nums[i] &lt;&nbsp;2<sup>16</sup></code></li>
 * <li><code>1 &lt;= k &lt;= floor(nums.length / 3)</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 191</li><li>👎 0</li></div>
 */

public class Leetcode689 {
  public static void main(String[] args) {
    Leetcode689 leetcode689 = new Leetcode689();
    // [7,13,20,19,19,2,10,1,1,19]
    // 3
    // int[] arr = {1, 2, 1, 2, 6, 7, 5, 1};
    int[] arr =
        {4, 5, 10, 6, 11, 17, 4, 11, 1, 3};
    System.out.println(Arrays.toString(leetcode689.maxSumOfThreeSubarrays3(arr, 1)));
  }

  /**
   * 使用数组保存以i开始长度为k的子数组的和的值 超时..
   *
   * @param nums
   * @param k
   * @return
   */
  public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
    int len = nums.length;
    int[] dp = new int[len];
    // dp[i]表示以i为左边界  k和长度的和
    for (int i = 0; i < len - k + 1; i++) {
      if (i == 0) {
        int tmp = 0;
        int t = 0;
        while (t < k) {
          tmp += nums[t++];
        }
        dp[i] = tmp;
      } else {
        dp[i] = dp[i - 1] - nums[i - 1] + nums[i + k - 1];
      }
    }

    System.out.println(Arrays.toString(dp));
    int[] res = new int[3];
    int max = -1;
    for (int i = 0; i < len - k; i++) {
      int left = i + k;
      int right = i + 2 * k;
      // right变动的时候 left不变
      // left变动的时候  right需要相应的做出变化
      while (left < right && right < len) {
        int add = dp[i] + dp[left] + dp[right];
        if (add > max) {
          max = add;
          res[0] = i;
          res[1] = left;
          res[2] = right;
        }
        right++;
        if (right == len - 1) {
          left++;
          right = left + k;
        }
      }
    }

    return res;
  }

  /**
   * 单个子数组的最大和
   * 使用滑动窗口 使用右边界的滑动窗口
   *
   * @param nums
   * @param k
   * @return
   */
  public int[] maxSumOfThreeSubarrays1(int[] nums, int k) {
    int[] ans = new int[1];
    int sum1 = 0, maxSum1 = 0;
    for (int i = 0; i < nums.length; ++i) {
      sum1 += nums[i];
      if (i >= k - 1) {
        if (sum1 > maxSum1) {
          maxSum1 = sum1;
          ans[0] = i - k + 1;
        }
        sum1 -= nums[i - k + 1];
      }
    }
    return ans;
  }

  /**
   * 两个无重叠数组的最大和
   * 使用两个窗口
   * 第一个窗口从[0,k-1]
   * 第二个窗口从[k,2k-1]
   *
   * @param nums
   * @param k
   * @return
   */
  public int[] maxSumOfThreeSubarrays2(int[] nums, int k) {
    int[] ans = new int[2];
    int sum1 = 0, maxSum1 = 0, maxSum1Idx = 0;
    int sum2 = 0, maxSum12 = 0;
    for (int i = k; i < nums.length; ++i) {
      sum1 += nums[i - k];
      sum2 += nums[i];
      if (i >= k * 2 - 1) {
        if (sum1 > maxSum1) {
          maxSum1 = sum1;
          maxSum1Idx = i - k * 2 + 1;
        }
        if (maxSum1 + sum2 > maxSum12) {
          maxSum12 = maxSum1 + sum2;
          ans[0] = maxSum1Idx;
          ans[1] = i - k + 1;
        }
        sum1 -= nums[i - k * 2 + 1];
        sum2 -= nums[i - k + 1];
      }
    }
    return ans;
  }

  /**
   * 三个无重叠窗口的最大值
   * @param nums
   * @param k
   * @return
   */
  public int[] maxSumOfThreeSubarrays3(int[] nums, int k) {
    int[] ans = new int[3];
    int sum1 = 0, maxSum1 = 0, maxSum1Idx = 0;
    int sum2 = 0, maxSum12 = 0, maxSum12Idx1 = 0, maxSum12Idx2 = 0;
    int sum3 = 0, maxSum123 = 0;

    for (int i = 2 * k; i < nums.length; ++i) {
      sum1 += nums[i - 2 * k];
      sum2 += nums[i - k];
      sum3 += nums[i];
      if (i >= k * 3 - 1) {
        if (sum1 > maxSum1) {
          maxSum1 = sum1;
          maxSum1Idx = i - k * 3 + 1;
        }
        if (maxSum1 + sum2 > maxSum12) {
          maxSum12 = maxSum1 + sum2;
          maxSum12Idx1 = maxSum1Idx;
          maxSum12Idx2 = i - k * 2 + 1;
        }
        if (maxSum12 + sum3 > maxSum123) {
          maxSum123 = maxSum12 + sum3;
          ans[0] = maxSum12Idx1;
          ans[1] = maxSum12Idx2;
          ans[2] = i - k + 1;
        }
        sum1 -= nums[i - k * 3 + 1];
        sum2 -= nums[i - k * 2 + 1];
        sum3 -= nums[i - k + 1];
      }
    }
    return ans;
  }
}

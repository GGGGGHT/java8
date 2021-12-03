package com.ggggght.learningjava8.leetcode;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
 * <p>
 * <p>
 * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
 * <p>
 * <p>
 * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 * <p>
 * 以这种方式修改数组后，返回数组 可能的最大和 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums = [4,2,3], k = 1
 * 输出：5
 * 解释：选择下标 1 ，nums 变为 [4,-2,3] 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums = [3,-1,0,2], k = 3
 * 输出：6
 * 解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：nums = [2,-3,-1,5,-4], k = 2
 * 输出：13
 * 解释：选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= nums.length <= 10⁴
 * -100 <= nums[i] <= 100
 * 1 <= k <= 10⁴
 * <p>
 * Related Topics 贪心 数组 排序 👍 136 👎
 */
public class Leetcode1005 {
  public static void main(String[] args) {
    Leetcode1005 solution = new Leetcode1005();
    var arr = new int[] {-5,-4,-3,-2,-1};
    int i = solution.largestSumAfterKNegations(arr, 8);
    System.out.println(i);
  }

  /**
   * 如果k是奇数
   * 如果k是偶数
   *
   * @param nums
   * @param K
   * @return
   */
  public int largestSumAfterKNegations(int[] nums, int K) {
    nums = IntStream.of(nums)
        .boxed()
        .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
        .mapToInt(Integer::intValue).toArray();
    int len = nums.length;
    for (int i = 0; i < len; i++) {
      //从前向后遍历，遇到负数将其变为正数，同时K--
      if (nums[i] < 0 && K > 0) {
        nums[i] = -nums[i];
        K--;
      }
    }
    // 如果K还大于0，那么反复转变数值最小的元素，将K用完
    if (K % 2 == 1) nums[len - 1] = -nums[len - 1];
    return Arrays.stream(nums).sum();
  }
}

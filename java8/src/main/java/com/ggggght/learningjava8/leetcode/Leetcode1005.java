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
    var arr = new int[] {4, 2, 3};
    // int i = solution.largestSumAfterKNegations(arr, 1);
    // int i2 = solution.largestSumAfterKNegations2(arr, 1);
    int i3 = solution.largestSumAfterKNegations3(arr, 1);
    // System.out.println(i);
    // System.out.println(i2);
  }

  /**
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

  public int largestSumAfterKNegations2(int[] nums, int k) {
    Arrays.sort(nums);
    for (int i = 0; i < nums.length && k > 0; i++) {
      if (nums[i] < 0) {
        nums[i] = -nums[i];
        k--;
      }
    }

    Arrays.sort(nums);
    if ((k & 1) == 1) nums[0] = -nums[0];

    return Arrays.stream(nums).sum();
  }

  @SuppressWarnings("all")
  public int largestSumAfterKNegations3(int[] A, int K) {
    int[] number = new int[201];//-100 <= A[i] <= 100,这个范围的大小是201
    for (int t : A) {
      number[t + 100]++;//将[-100,100]映射到[0,200]上
    }
    int i = 0;
    while (K > 0) {
      while (number[i] == 0)//找到A[]中最小的数字
        i++;
      number[i]--;//此数字个数-1
      number[200 - i]++;//其相反数个数+1
      if (i > 100) {//若原最小数索引>100,则新的最小数索引应为200-i.(索引即number[]数组的下标)
        i = 200 - i;
      }
      K--;
    }
    int sum = 0;
    for (int j = i; j <number.length ; j++) {//遍历number[]求和
      sum += (j-100)*number[j];//j-100是数字大小,number[j]是该数字出现次数.
    }
    return sum;
  }
}

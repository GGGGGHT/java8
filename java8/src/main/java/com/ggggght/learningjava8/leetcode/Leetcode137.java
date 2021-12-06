package com.ggggght.learningjava8.leetcode;

/**
 * <p>给你一个整数数组 <code>nums</code> ，除某个元素仅出现 <strong>一次</strong> 外，其余每个元素都恰出现 <strong>三次 。</strong>请你找出并返回那个只出现了一次的元素。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [2,2,3,2]
 * <strong>输出：</strong>3
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [0,1,0,1,0,1,99]
 * <strong>输出：</strong>99
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 3 * 10<sup>4</sup></code></li>
 * <li><code>-2<sup>31</sup> <= nums[i] <= 2<sup>31</sup> - 1</code></li>
 * <li><code>nums</code> 中，除某个元素仅出现 <strong>一次</strong> 外，其余每个元素都恰出现 <strong>三次</strong></li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong>你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？</p>
 * <div><div>Related Topics</div><div><li>位运算</li><li>数组</li></div></div><br><div><li>👍 775</li><li>👎 0</li></div>
 */
public class Leetcode137 {
  /**
   * 将所有的1都映射到一个数组中
   * 完成之后对3取模 剩下的1即是结果的组成部分
   * @param nums
   * @return
   */
  public int singleNumber(int[] nums) {
    int[] arr = new int[32];
    for (int num : nums) {
      for (int i = 0; i < 32; i++) {
        if (((num >> i) & 1) == 1) {
          arr[i]++;
        }
      }
    }
    int res = 0;
    for (int i = 0; i < 32; i++) {
      arr[i] %= 3;
      if (arr[i] != 0) {
        res += (1 << i);
      }
    }

    return res;
  }
}

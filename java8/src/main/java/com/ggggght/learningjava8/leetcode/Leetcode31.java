package com.ggggght.learningjava8.leetcode;

import java.awt.image.SampleModel;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>实现获取 <strong>下一个排列</strong> 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。</p>
 *
 * <p>如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。</p>
 *
 * <p>必须<strong><a href="https://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank"> 原地 </a></strong>修改，只允许使用额外常数空间。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3]
 * <strong>输出：</strong>[1,3,2]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [3,2,1]
 * <strong>输出：</strong>[1,2,3]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,1,5]
 * <strong>输出：</strong>[1,5,1]
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1]
 * <strong>输出：</strong>[1]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 100</code></li>
 * <li><code>0 &lt;= nums[i] &lt;= 100</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>双指针</li></div></div><br><div><li>👍 1450</li><li>👎 0</li></div>
 */

public class Leetcode31 {
  public static void main(String[] args) {
    // var arr = new int[] {1, 1};
    // var arr1 = new int[] {1, 5, 1};
    // var arr2 = new int[] {3, 2, 1};
    // var arr3 = new int[] {1, 1, 5};
    // var arr4 = new int[] {1, 3, 2};
    // var arr5 = new int[] {5, 4, 7, 5, 3, 2};
    // var arr6 = new int[] {4, 2, 0, 2, 3, 2, 0};
    var arr7 = new int[] {1, 7, 8, 5, 7, 4, 1, 8, 5, 9, 7, 5};
    // nextPermutation(arr);
    // nextPermutation(arr1);
    // nextPermutation(arr2);
    // nextPermutation(arr3);
    // nextPermutation(arr4);
    nextPermutation(arr7);
  }

  public static void nextPermutation(int[] nums) {
    int len = nums.length;
    if (len < 2) {
      return;
    }

    int start = len - 2, end = len - 1;
    int smallLeft = -1, rightEnd = 0;
    while (start >= 0) {
      if (nums[start] < nums[end]) {
        if (smallLeft < start) {
          smallLeft = start;
          rightEnd = end;
        }
        if (start == 0 && end > 0) {
          start = end - 2;
          end -= 1;
        } else {
          start--;
        }
      } else {
        if (start == 0) {
          start = end - 2;
          end -= 1;
        } else {
          start--;
        }
      }
    }

    System.out.println(smallLeft);
    System.out.println(rightEnd);
    // 没有找到比更大的
    if (smallLeft == -1) {
      Arrays.sort(nums);
    } else {
      // 交换两个位置的值
      nums[smallLeft] = nums[smallLeft] ^ nums[rightEnd];
      nums[rightEnd] = nums[smallLeft] ^ nums[rightEnd];
      nums[smallLeft] = nums[smallLeft] ^ nums[rightEnd];

      // 对start之后的位置进行排序
      Arrays.sort(nums, smallLeft + 1, nums.length);
    }

    System.out.println(Arrays.toString(nums));
  }
}

package com.ggggght.learningjava8.leetcode;
/**
 <p>给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。</p>

 <p>请你找出并返回只出现一次的那个数。</p>

 <p>你设计的解决方案必须满足 <code>O(log n)</code> 时间复杂度和 <code>O(1)</code> 空间复杂度。</p>

 <p>&nbsp;</p>

 <p><strong>示例 1:</strong></p>

 <pre>
 <strong>输入:</strong> nums = [1,1,2,3,3,4,4,8,8]
 <strong>输出:</strong> 2
 </pre>

 <p><strong>示例 2:</strong></p>

 <pre>
 <strong>输入:</strong> nums =  [3,3,7,7,10,11,11]
 <strong>输出:</strong> 10
 </pre>

 <p>&nbsp;</p>

 <p><meta charset="UTF-8" /></p>

 <p><strong>提示:</strong></p>

 <ul>
 <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
 <li><code>0 &lt;= nums[i]&nbsp;&lt;= 10<sup>5</sup></code></li>
 </ul>
 <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 359</li><li>👎 0</li></div>
 */

public class Leetcode540 {
  public static void main(String[] args) {
    System.out.println(singleNonDuplicate(new int[] {1, 1, 2, 3, 3, 4, 4, 6, 6}));
    System.out.println(singleNonDuplicate1(new int[] {1, 1, 2, 3, 3, 4, 4, 6, 6}));
    System.out.println(singleNonDuplicate(new int[] {3, 3, 7, 7, 10, 11, 11}));
    System.out.println(singleNonDuplicate1(new int[] {3, 3, 7, 7, 10, 11, 11}));
    System.out.println(singleNonDuplicate1(new int[] {1, 1, 2}));
  }

  /**
   * 使用异或运算 但时间复杂度O(n)
   *
   * @param nums
   * @return
   */
  public static int singleNonDuplicate(int[] nums) {
    var ans = 0;
    for (int num : nums) {
      ans ^= num;
    }

    return ans;
  }

  /**
   * 时间复杂度O(log n)
   *
   * @param nums
   * @return
   */
  public static int singleNonDuplicate1(int[] nums) {
    var n = nums.length;
    var l = 0;
    var r = n - 1;
    while (l < r) {
      var mid = l + r >> 1;
      // mid是奇数 证明
      if ((mid & 1) == 0) {
        if (mid + 1 < n && nums[mid] == nums[mid + 1]) {
          l = mid + 1;
        } else {
          r = mid;
        }
      } else {
        if (mid - 1 >= 0 && nums[mid - 1] == nums[mid]) {
          l = mid + 1;
        } else {
          r = mid;
        }
      }
    }
    return nums[r];
  }
}

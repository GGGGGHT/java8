package com.ggggght.learningjava8.leetcode;

import java.util.Stack;
/**
 <p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> ，该数组的大小为 <code>n</code> ，请你计算 <code>nums[j] - nums[i]</code> 能求得的 <strong>最大差值 </strong>，其中 <code>0 &lt;= i &lt; j &lt; n</code> 且 <code>nums[i] &lt; nums[j]</code> 。</p>

 <p>返回 <strong>最大差值</strong> 。如果不存在满足要求的 <code>i</code> 和 <code>j</code> ，返回 <code>-1</code> 。</p>

 <p>&nbsp;</p>

 <p><strong>示例 1：</strong></p>

 <pre><strong>输入：</strong>nums = [7,<em><strong>1</strong></em>,<em><strong>5</strong></em>,4]
 <strong>输出：</strong>4
 <strong>解释：</strong>
 最大差值出现在 i = 1 且 j = 2 时，nums[j] - nums[i] = 5 - 1 = 4 。
 注意，尽管 i = 1 且 j = 0 时 ，nums[j] - nums[i] = 7 - 1 = 6 &gt; 4 ，但 i &gt; j 不满足题面要求，所以 6 不是有效的答案。
 </pre>

 <p><strong>示例 2：</strong></p>

 <pre><strong>输入：</strong>nums = [9,4,3,2]
 <strong>输出：</strong>-1
 <strong>解释：</strong>
 不存在同时满足 i &lt; j 和 nums[i] &lt; nums[j] 这两个条件的 i, j 组合。
 </pre>

 <p><strong>示例 3：</strong></p>

 <pre><strong>输入：</strong>nums = [<em><strong>1</strong></em>,5,2,<em><strong>10</strong></em>]
 <strong>输出：</strong>9
 <strong>解释：</strong>
 最大差值出现在 i = 0 且 j = 3 时，nums[j] - nums[i] = 10 - 1 = 9 。
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>n == nums.length</code></li>
 <li><code>2 &lt;= n &lt;= 1000</code></li>
 <li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
 </ul>
 <div><div>Related Topics</div><div><li>数组</li></div></div><br><div><li>👍 33</li><li>👎 0</li></div>
 */

public class Leetcode2016 {
  public static void main(String[] args) {
    System.out.println(maximumDifference(new int[] {7, 1, 5, 4}));
    System.out.println(maximumDifference(new int[] {9, 4, 3, 2}));
    System.out.println(maximumDifference(new int[] {1, 5, 2, 10}));
  }
  public static int maximumDifference(int[] nums) {
    int ans = -1;
    Stack<Integer> stack = new Stack<>();
    stack.push(nums[nums.length - 1]);
    for (int i = nums.length - 2; i >= 0; i--) {
      if (nums[i] < stack.peek()) {
        ans = Math.max(stack.peek() - nums[i], ans);
      } else {
        stack.push(nums[i]);
      }
    }
    return ans;
  }
}

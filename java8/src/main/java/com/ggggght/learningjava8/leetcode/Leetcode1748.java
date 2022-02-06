package com.ggggght.learningjava8.leetcode;
/**
 <p>给你一个整数数组 <code>nums</code> 。数组中唯一元素是那些只出现 <strong>恰好一次</strong> 的元素。</p>

 <p>请你返回 <code>nums</code> 中唯一元素的 <strong>和</strong> 。</p>

 <p> </p>

 <p><strong>示例 1：</strong></p>

 <pre><b>输入：</b>nums = [1,2,3,2]
 <b>输出：</b>4
 <b>解释：</b>唯一元素为 [1,3] ，和为 4 。
 </pre>

 <p><strong>示例 2：</strong></p>

 <pre><b>输入：</b>nums = [1,1,1,1,1]
 <b>输出：</b>0
 <b>解释：</b>没有唯一元素，和为 0 。
 </pre>

 <p><strong>示例 3 ：</strong></p>

 <pre><b>输入：</b>nums = [1,2,3,4,5]
 <b>输出：</b>15
 <b>解释：</b>唯一元素为 [1,2,3,4,5] ，和为 15 。
 </pre>

 <p> </p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>1 &lt;= nums.length &lt;= 100</code></li>
 <li><code>1 &lt;= nums[i] &lt;= 100</code></li>
 </ul>
 <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>计数</li></div></div><br><div><li>👍 37</li><li>👎 0</li></div>
 */

public class Leetcode1748 {
  public static void main(String[] args) {
    System.out.println(sumOfUnique(new int[] {1, 1, 1, 1, 1}));
  }
  public static int sumOfUnique(int[] nums) {
    int[] arr = new int[101];
    int res = 0;
    for (int num : nums) {
      res += ++arr[num] == 1 ? num : (arr[num] == 2 ? -num : 0);
    }
    return res;
  }
}

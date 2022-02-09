package com.ggggght.learningjava8.leetcode;
/**
 <p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;，请你返回数对&nbsp;<code>(i, j)</code>&nbsp;的数目，满足&nbsp;<code>i &lt; j</code>&nbsp;且&nbsp;<code>|nums[i] - nums[j]| == k</code>&nbsp;。</p>

 <p><code>|x|</code>&nbsp;的值定义为：</p>

 <ul>
 <li>如果&nbsp;<code>x &gt;= 0</code>&nbsp;，那么值为&nbsp;<code>x</code>&nbsp;。</li>
 <li>如果&nbsp;<code>x &lt; 0</code>&nbsp;，那么值为&nbsp;<code>-x</code>&nbsp;。</li>
 </ul>

 <p>&nbsp;</p>

 <p><strong>示例 1：</strong></p>

 <pre><b>输入：</b>nums = [1,2,2,1], k = 1
 <b>输出：</b>4
 <b>解释：</b>差的绝对值为 1 的数对为：
 - [<em><strong>1</strong></em>,<em><strong>2</strong></em>,2,1]
 - [<em><strong>1</strong></em>,2,<em><strong>2</strong></em>,1]
 - [1,<em><strong>2</strong></em>,2,<em><strong>1</strong></em>]
 - [1,2,<em><strong>2</strong></em>,<em><strong>1</strong></em>]
 </pre>

 <p><strong>示例 2：</strong></p>

 <pre><b>输入：</b>nums = [1,3], k = 3
 <b>输出：</b>0
 <b>解释：</b>没有任何数对差的绝对值为 3 。
 </pre>

 <p><strong>示例 3：</strong></p>

 <pre><b>输入：</b>nums = [3,2,1,5,4], k = 2
 <b>输出：</b>3
 <b>解释：</b>差的绝对值为 2 的数对为：
 - [<em><strong>3</strong></em>,2,<em><strong>1</strong></em>,5,4]
 - [<em><strong>3</strong></em>,2,1,<em><strong>5</strong></em>,4]
 - [3,<em><strong>2</strong></em>,1,5,<em><strong>4</strong></em>]
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>1 &lt;= nums.length &lt;= 200</code></li>
 <li><code>1 &lt;= nums[i] &lt;= 100</code></li>
 <li><code>1 &lt;= k &lt;= 99</code></li>
 </ul>
 <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>计数</li></div></div><br><div><li>👍 50</li><li>👎 0</li></div>
 */

public class Leetcode2006 {
  public static void main(String[] args) {
    System.out.println(countKDifference(new int[] {1, 2, 2, 1}, 1));
    System.out.println(countKDifference(new int[] {1, 3}, 3));
    System.out.println(countKDifference(new int[] {3,2,1,5,4}, 2));
  }
  public static int countKDifference(int[] nums, int k) {
    var res = 0;
    int left = 0, right;
    var len = nums.length;
    while (left <= len - 1) {
      right = left + 1;
      while (right <= len - 1) {
        if(k == Math.abs(nums[left] - nums[right++])) {
          res++;
        }
      }
      left++;
    }
    return res;
  }

  public int countKDifference2(int[] nums, int k) {
    int[] cnts = new int[110];
    int n = nums.length, ans = 0;
    for (int i = 0; i < n; i++) {
      int t = nums[i];
      if (t - k >= 1) ans += cnts[t - k];
      if (t + k <= 100) ans += cnts[t + k];
      cnts[t]++;
    }
    return ans;
  }

}

package com.ggggght.learningjava8.leetcode;

/**
 <p>给你一个整数数组 <code>nums</code> ，请你找出 <code>nums</code> 子集 <strong>按位或</strong> 可能得到的<strong> </strong><strong>最大值</strong> ，并返回按位或能得到最大值的 <strong>不同非空子集的数目</strong> 。</p>

 <p>如果数组 <code>a</code> 可以由数组 <code>b</code> 删除一些元素（或不删除）得到，则认为数组 <code>a</code> 是数组 <code>b</code> 的一个 <strong>子集</strong> 。如果选中的元素下标位置不一样，则认为两个子集 <strong>不同</strong> 。</p>

 <p>对数组 <code>a</code> 执行 <strong>按位或</strong>&nbsp;，结果等于 <code>a[0] <strong>OR</strong> a[1] <strong>OR</strong> ... <strong>OR</strong> a[a.length - 1]</code>（下标从 <strong>0</strong> 开始）。</p>

 <p>&nbsp;</p>

 <p><strong>示例 1：</strong></p>

 <pre>
 <strong>输入：</strong>nums = [3,1]
 <strong>输出：</strong>2
 <strong>解释：</strong>子集按位或能得到的最大值是 3 。有 2 个子集按位或可以得到 3 ：
 - [3]
 - [3,1]
 </pre>

 <p><strong>示例 2：</strong></p>

 <pre>
 <strong>输入：</strong>nums = [2,2,2]
 <strong>输出：</strong>7
 <strong>解释：</strong>[2,2,2] 的所有非空子集的按位或都可以得到 2 。总共有 2<sup>3</sup> - 1 = 7 个子集。
 </pre>

 <p><strong>示例 3：</strong></p>

 <pre>
 <strong>输入：</strong>nums = [3,2,1,5]
 <strong>输出：</strong>6
 <strong>解释：</strong>子集按位或可能的最大值是 7 。有 6 个子集按位或可以得到 7 ：
 - [3,5]
 - [3,1,5]
 - [3,2,5]
 - [3,2,1,5]
 - [2,5]
 - [2,1,5]</pre>

 <p>&nbsp;</p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>1 &lt;= nums.length &lt;= 16</code></li>
 <li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
 </ul>
 <div><div>Related Topics</div><div><li>位运算</li><li>数组</li><li>回溯</li></div></div><br><div><li>👍 97</li><li>👎 0</li></div>
 */
public class Leetcode2044 {
  public static void main(String[] args) {
    Leetcode2044 leetcode2044 = new Leetcode2044();
    // System.out.println(leetcode2044.countMaxOrSubsets(new int[] {3, 1}));
    System.out.println(leetcode2044.countMaxOrSubsets(new int[] {2, 2,2}));
    System.out.println(leetcode2044.countMaxOrSubsets(new int[] {3,2, 1,5}));

  }


  public int countMaxOrSubsets(int[] nums) {
    int n = nums.length;
    int max = 0;
    int ans = 1;
    for (int num : nums) {
     max |= num;
    }

    for (int mask = 0; mask < (1 << n); mask++) {
      int or = 0;
      for (int i = 0; i < n; i++) {
        if ((mask >> i & 1) == 1) {
          or |= nums[i];
        }
      }
      if (or == max) {
        ans++;
      }
    }

    return ans;
  }


}

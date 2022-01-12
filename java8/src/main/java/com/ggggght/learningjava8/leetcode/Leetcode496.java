package com.ggggght.learningjava8.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
/**
 <p><code>nums1</code>&nbsp;中数字&nbsp;<code>x</code>&nbsp;的 <strong>下一个更大元素</strong> 是指&nbsp;<code>x</code>&nbsp;在&nbsp;<code>nums2</code> 中对应位置 <strong>右侧</strong> 的 <strong>第一个</strong> 比&nbsp;<code>x</code><strong>&nbsp;</strong>大的元素。</p>

 <p>给你两个<strong> 没有重复元素</strong> 的数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code> ，下标从 <strong>0</strong> 开始计数，其中<code>nums1</code>&nbsp;是&nbsp;<code>nums2</code>&nbsp;的子集。</p>

 <p>对于每个 <code>0 &lt;= i &lt; nums1.length</code> ，找出满足 <code>nums1[i] == nums2[j]</code> 的下标 <code>j</code> ，并且在 <code>nums2</code> 确定 <code>nums2[j]</code> 的 <strong>下一个更大元素</strong> 。如果不存在下一个更大元素，那么本次查询的答案是 <code>-1</code> 。</p>

 <p>返回一个长度为&nbsp;<code>nums1.length</code> 的数组<em> </em><code>ans</code><em> </em>作为答案，满足<em> </em><code>ans[i]</code><em> </em>是如上所述的 <strong>下一个更大元素</strong> 。</p>

 <p>&nbsp;</p>

 <p><strong>示例 1：</strong></p>

 <pre>
 <strong>输入：</strong>nums1 = [4,1,2], nums2 = [1,3,4,2].
 <strong>输出：</strong>[-1,3,-1]
 <strong>解释：</strong>nums1 中每个值的下一个更大元素如下所述：
 - 4 ，用加粗斜体标识，nums2 = [1,3,<strong>4</strong>,2]。不存在下一个更大元素，所以答案是 -1 。
 - 1 ，用加粗斜体标识，nums2 = [<em><strong>1</strong></em>,3,4,2]。下一个更大元素是 3 。
 - 2 ，用加粗斜体标识，nums2 = [1,3,4,<em><strong>2</strong></em>]。不存在下一个更大元素，所以答案是 -1 。</pre>

 <p><strong>示例 2：</strong></p>

 <pre>
 <strong>输入：</strong>nums1 = [2,4], nums2 = [1,2,3,4].
 <strong>输出：</strong>[3,-1]
 <strong>解释：</strong>nums1 中每个值的下一个更大元素如下所述：
 - 2 ，用加粗斜体标识，nums2 = [1,<em><strong>2</strong></em>,3,4]。下一个更大元素是 3 。
 - 4 ，用加粗斜体标识，nums2 = [1,2,3,<em><strong>4</strong></em>]。不存在下一个更大元素，所以答案是 -1 。
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>1 &lt;= nums1.length &lt;= nums2.length &lt;= 1000</code></li>
 <li><code>0 &lt;= nums1[i], nums2[i] &lt;= 10<sup>4</sup></code></li>
 <li><code>nums1</code>和<code>nums2</code>中所有整数 <strong>互不相同</strong></li>
 <li><code>nums1</code> 中的所有整数同样出现在 <code>nums2</code> 中</li>
 </ul>

 <p>&nbsp;</p>

 <p><strong>进阶：</strong>你可以设计一个时间复杂度为 <code>O(nums1.length + nums2.length)</code> 的解决方案吗？</p>
 <div><div>Related Topics</div><div><li>栈</li><li>数组</li><li>哈希表</li><li>单调栈</li></div></div><br><div><li>👍 626</li><li>👎 0</li></div>
 */

public class Leetcode496 {
  /**
   * 使用单调栈 反向搜索
   * @param nums1
   * @param nums2
   * @return
   */
  public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
    int n = nums1.length, m = nums2.length;
    Deque<Integer> d = new ArrayDeque<>();
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = m - 1; i >= 0; i--) {
      int x = nums2[i];
      while (!d.isEmpty() && d.peekLast() <= x) d.pollLast();
      map.put(x, d.isEmpty() ? -1 : d.peekLast());
      d.addLast(x);
    }
    int[] ans = new int[n];
    for (int i = 0; i < n; i++) ans[i] = map.get(nums1[i]);
    return ans;
  }
}

package com.ggggght.learningjava8.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/**
 <p>给你一个整数数组 <code>arr</code> ，请使用 <strong>煎饼翻转</strong><em> </em>完成对数组的排序。</p>

 <p>一次煎饼翻转的执行过程如下：</p>

 <ul>
 <li>选择一个整数 <code>k</code> ，<code>1 <= k <= arr.length</code></li>
 <li>反转子数组 <code>arr[0...k-1]</code>（<strong>下标从 0 开始</strong>）</li>
 </ul>

 <p>例如，<code>arr = [3,2,1,4]</code> ，选择 <code>k = 3</code> 进行一次煎饼翻转，反转子数组 <code>[3,2,1]</code> ，得到 <code>arr = [<strong>1</strong>,<strong>2</strong>,<strong>3</strong>,4]</code> 。</p>

 <p>以数组形式返回能使 <code>arr</code> 有序的煎饼翻转操作所对应的 <code>k</code> 值序列。任何将数组排序且翻转次数在 <code>10 * arr.length</code> 范围内的有效答案都将被判断为正确。</p>

 <p> </p>

 <p><strong>示例 1：</strong></p>

 <pre>
 <strong>输入：</strong>[3,2,4,1]
 <strong>输出：</strong>[4,2,4,3]
 <strong>解释：</strong>
 我们执行 4 次煎饼翻转，k 值分别为 4，2，4，和 3。
 初始状态 arr = [3, 2, 4, 1]
 第一次翻转后（k = 4）：arr = [<strong>1</strong>, <strong>4</strong>, <strong>2</strong>, <strong>3</strong>]
 第二次翻转后（k = 2）：arr = [<strong>4</strong>, <strong>1</strong>, 2, 3]
 第三次翻转后（k = 4）：arr = [<strong>3</strong>, <strong>2</strong>, <strong>1</strong>, <strong>4</strong>]
 第四次翻转后（k = 3）：arr = [<strong>1</strong>, <strong>2</strong>, <strong>3</strong>, 4]，此时已完成排序。
 </pre>

 <p><strong>示例 2：</strong></p>

 <pre>
 <strong>输入：</strong>[1,2,3]
 <strong>输出：</strong>[]
 <strong>解释：
 </strong>输入已经排序，因此不需要翻转任何内容。
 请注意，其他可能的答案，如 [3，3] ，也将被判断为正确。
 </pre>

 <p> </p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>1 <= arr.length <= 100</code></li>
 <li><code>1 <= arr[i] <= arr.length</code></li>
 <li><code>arr</code> 中的所有整数互不相同（即，<code>arr</code> 是从 <code>1</code> 到 <code>arr.length</code> 整数的一个排列）</li>
 </ul>
 <div><div>Related Topics</div><div><li>贪心</li><li>数组</li><li>双指针</li><li>排序</li></div></div><br><div><li>👍 236</li><li>👎 0</li></div>
 */

public class Leetcode969 {
  public static void main(String[] args) {
    System.out.println(new Leetcode969().pancakeSort(new int[] {3, 2, 4, 1}));
    // System.out.println(new Leetcode969().pancakeSort(new int[] {1,2,3}));
  }

  public List<Integer> pancakeSort(int[] arr) {
    int n = arr.length;
    int[] idxs = new int[n + 10];
    for (int i = 0; i < n; i++) idxs[arr[i]] = i;
    List<Integer> ans = new ArrayList<>();
    for (int i = n; i >= 1; i--) {
      int idx = idxs[i];
      if (idx == i - 1) continue;
      if (idx != 0) {
        ans.add(idx + 1);
        reverse(arr, 0, idx, idxs);
      }
      ans.add(i);
      reverse(arr, 0, i - 1, idxs);
    }
    return ans;
  }
  void reverse(int[] arr, int i, int j, int[] idxs) {
    while (i < j) {
      idxs[arr[i]] = j; idxs[arr[j]] = i;
      int c = arr[i];
      arr[i++] = arr[j];
      arr[j--] = c;
    }
  }

}

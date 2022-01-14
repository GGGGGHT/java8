package com.ggggght.learningjava8.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 <p>给定两个以升序排列的整数数组 <code>nums1</code> 和<strong> </strong><code>nums2</code><strong> </strong>, 以及一个整数 <code>k</code><strong> </strong>。</p>

 <p>定义一对值 <code>(u,v)</code>，其中第一个元素来自 <code>nums1</code>，第二个元素来自 <code>nums2</code><strong> </strong>。</p>

 <p>请找到和最小的 <code>k</code> 个数对 <code>(u<sub>1</sub>,v<sub>1</sub>)</code>, <code> (u<sub>2</sub>,v<sub>2</sub>)</code>  ...  <code>(u<sub>k</sub>,v<sub>k</sub>)</code> 。</p>

 <p> </p>

 <p><strong>示例 1:</strong></p>

 <pre>
 <strong>输入:</strong> nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 <strong>输出:</strong> [1,2],[1,4],[1,6]
 <strong>解释: </strong>返回序列中的前 3 对数：
 [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 </pre>

 <p><strong>示例 2:</strong></p>

 <pre>
 <strong>输入: </strong>nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 <strong>输出: </strong>[1,1],[1,1]
 <strong>解释: </strong>返回序列中的前 2 对数：
 [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 </pre>

 <p><strong>示例 3:</strong></p>

 <pre>
 <strong>输入: </strong>nums1 = [1,2], nums2 = [3], k = 3
 <strong>输出:</strong> [1,3],[2,3]
 <strong>解释: </strong>也可能序列中所有的数对都被返回:[1,3],[2,3]
 </pre>

 <p> </p>

 <p><strong>提示:</strong></p>

 <ul>
 <li><code>1 <= nums1.length, nums2.length <= 10<sup>4</sup></code></li>
 <li><code>-10<sup>9</sup> <= nums1[i], nums2[i] <= 10<sup>9</sup></code></li>
 <li><code>nums1</code>, <code>nums2</code> 均为升序排列</li>
 <li><code>1 <= k <= 1000</code></li>
 </ul>
 <div><div>Related Topics</div><div><li>数组</li><li>堆（优先队列）</li></div></div><br><div><li>👍 281</li><li>👎 0</li></div>
 */
public class Leetcode373 {
  public static void main(String[] args) {
    Leetcode373 leetcode373 = new Leetcode373();
    // System.out.println(leetcode373.kSmallestPairs(new int[] {1, 2}, new int[] {3}, 3));
    // System.out.println(leetcode373.nthUglyNumber2(10));
    System.out.println(leetcode373.kSmallestPairs2(new int[] {1, 2}, new int[] {4, 5, 9}, 3));
  }

  public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    List<List<Integer>> res = new ArrayList<>(k);

    PriorityQueue<List<Integer>> queue = new PriorityQueue<>(
        (o1, o2) -> o1.stream().mapToInt(i -> i).sum() - o2.stream().mapToInt(i -> i).sum());
    List<Integer> path = new ArrayList<>();
    backtracking(path, 0, 0, queue, nums1, nums2);
    while (k-- > 0 && !queue.isEmpty()) {
      res.add(queue.poll());
    }
    return res;
  }

  /**
   * 使用回溯加优先队列 TLE
   *
   * @param path
   * @param _1startIdx
   * @param _2startIdx
   */
  void backtracking(List<Integer> path, int _1startIdx, int _2startIdx,
      PriorityQueue<List<Integer>> queue, int[] nums1, int[] nums2) {
    if (path.size() == 2) {
      queue.add(new ArrayList<>(path));
      return;
    }

    for (int i = _1startIdx; i < nums1.length; i++) {
      path.add(nums1[i]);
      for (int j = _2startIdx; j < nums2.length; j++) {
        path.add(nums2[j]);
        backtracking(path, _1startIdx, _2startIdx + 1, queue, nums1, nums2);
        path.remove(1);
      }
      path.remove(0);
    }
  }

  boolean flag = true;
  public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
    int n = nums1.length, m = nums2.length;
    if (n > m && !(flag = false)) return kSmallestPairs2(nums2, nums1, k);
    List<List<Integer>> ans = new ArrayList<>();
    PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->(nums1[a[0]]+nums2[a[1]])-(nums1[b[0]]+nums2[b[1]]));
    for (int i = 0; i < Math.min(n, k); i++) q.add(new int[]{i, 0});
    while (ans.size() < k && !q.isEmpty()) {
      int[] poll = q.poll();
      int a = poll[0], b = poll[1];
      ans.add(new ArrayList<>(){{
        add(flag ? nums1[a] : nums2[b]);
        add(flag ? nums2[b] : nums1[a]);
      }});
      if (b + 1 < m) q.add(new int[]{a, b + 1});
    }
    return ans;
  }
}

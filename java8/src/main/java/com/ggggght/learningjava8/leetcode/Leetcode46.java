package com.ggggght.learningjava8.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>给定一个不含重复数字的数组 <code>nums</code> ，返回其 <strong>所有可能的全排列</strong> 。你可以 <strong>按任意顺序</strong> 返回答案。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3]
 * <strong>输出：</strong>[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [0,1]
 * <strong>输出：</strong>[[0,1],[1,0]]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1]
 * <strong>输出：</strong>[[1]]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 6</code></li>
 * <li><code>-10 <= nums[i] <= 10</code></li>
 * <li><code>nums</code> 中的所有整数 <strong>互不相同</strong></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>回溯</li></div></div><br><div><li>👍 1685</li><li>👎 0</li></div>
 */

public class Leetcode46 {
  public static void main(String[] args) {
    Leetcode46 leetcode46 = new Leetcode46();
    System.out.println(leetcode46.permute(new int[] {1}));
  }

  List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
  LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果
  boolean[] used;

  public List<List<Integer>> permute(int[] nums) {
    if (nums.length == 0) {
      return result;
    }
    used = new boolean[nums.length];
    permuteHelper(nums);
    return result;
  }

  private void permuteHelper(int[] nums) {
    if (path.size() == nums.length) {
      result.add(new ArrayList<>(path));
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      if (used[i]) {
        continue;
      }
      used[i] = true;
      path.add(nums[i]);
      permuteHelper(nums);
      path.removeLast();
      used[i] = false;
    }
  }

}

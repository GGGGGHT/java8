package com.ggggght.learningjava8.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode39 {
  public static void main(String[] args) {
    Leetcode39 leetcode39 = new Leetcode39();
    // leetcode39.combinationSum(new int[] {2, 3, 6, 7}, 7);
    // List<List<Integer>> combine = leetcode39.combine(1, 1);
    List<List<Integer>> combine = leetcode39.combinationSum(new int[] {1}, 2);
    combine.forEach(System.out::println);
    System.out.println(combine.size());
  }

  /**
   * 回溯
   *
   * @param candidates
   * @param target
   * @return
   */
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(candidates);
    if (target < candidates[0]) {
      return res;
    }
    List<Integer> path = new ArrayList<>();
    backtracking(0, res, path, candidates, target);
    return res;
  }

  private void backtracking(int startIndex, List<List<Integer>> res, List<Integer> path,
      int[] candidates, int target) {
    // 结束条件 path中的合大于或等于target
    var r = 0;
    for (Integer integer : path) {
      int valueOf = integer;
      r += valueOf;
    }
    if (r >= target) {
      if (r == target) {
        res.add(new ArrayList<>(path));
      }
      return;
    }

    for (int i = startIndex; i < candidates.length; i++) {
      path.add(candidates[i]);
      backtracking(i, res, path, candidates, target);
      path.remove(path.size() - 1);
    }
  }
}

// 2 3 6 7
// 2 2 2 2
// === ===
// 2 5 11 18
//
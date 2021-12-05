package com.ggggght.learningjava8.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode77 {
  public static void main(String[] args) {
    Leetcode77 leetcode77 = new Leetcode77();
    List<List<Integer>> combine = leetcode77.combine(10, 3);
    // combine.forEach(System.out::println);
    System.out.println(combine.size());
  }


  public List<List<Integer>> combine(int n, int k) {
    var res = new ArrayList<List<Integer>>();
    var path = new ArrayList<Integer>();
    backtracking(res, path, 1, k, n);
    return res;
  }

  /**
   * 使用回溯算法
   * @param res
   * @param list
   * @param startIndex
   * @param k
   * @param n
   */
  private void backtracking(List<List<Integer>> res, List<Integer> list, int startIndex, int k,
      int n) {
    // 退出条件 当stack中有两个数时
    if (list.size() == k) {
      res.add(new ArrayList<>(list));
      return;
    }

    for (int i = startIndex; i <= n; i++) {
      list.add(i);
      backtracking(res, list, i + 1, k, n);
      // 删除最后一个节点
      list.remove(list.size() - 1);
    }
  }
}


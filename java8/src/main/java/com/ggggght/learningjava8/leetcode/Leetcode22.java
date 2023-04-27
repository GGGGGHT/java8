package com.ggggght.learningjava8.leetcode;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：n = 1
 * 输出：["()"]
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= n <= 8
 * <p>
 * Related Topics 字符串 动态规划 回溯 👍 2186 👎 0
 */
public class Leetcode22 {
  public static void main(String[] args) {
    List<String> strings = new Leetcode22().generateParenthesis(4);
    System.out.println(strings);
  }

  @NotNull
  private static HashSet<Integer> getIntegers(String str) {
    HashSet<Integer> s = new HashSet<>();
    int i = 0;
    while (i <= str.length()) {
      int n = str.indexOf("(", i);
      if (n != -1) {
        s.add(n);
      }
      i++;
    }
    return s;
  }

  public List<String> generateParenthesis(int n) {
    HashMap<Integer, List<String>> map = new HashMap<>();
    map.put(1, List.of("()"));

    for (int i = 2; i <= n; i++) {
      var tmp = getT(i, map);
      map.put(i, tmp);
    }
    return map.get(n);
  }

  public List<String> getT(int i,
      HashMap<Integer, List<String>> map) {
    var pattern = map.get(1).get(0);
    List<String> reduce = map.get(i - 1);
    HashSet<String> res = new HashSet<>();

    for (String s : reduce) {
      HashSet<Integer> set = getIntegers(s);
      for (Integer position : set) {
        var t = s.substring(0, position) + "()" + s.substring(position);
        var m = s.substring(0, position+1) + "()" + s.substring(position+1);
        res.add(t);
        res.add(m);
      }
    }
    return new ArrayList<>(res);
  }

}

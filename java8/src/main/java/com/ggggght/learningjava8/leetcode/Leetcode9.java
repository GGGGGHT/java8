package com.ggggght.learningjava8.leetcode;

import java.util.Arrays;

/**
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * <p>
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 121
 * 输出：true
 * <p>
 * 示例 2：
 * <p>
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * <p>
 * 示例 3：
 * <p>
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 * <p>
 * 示例 4：
 * <p>
 * 输入：x = -101
 * 输出：false
 * <p>
 * 提示：
 * <p>
 * <p>
 * -2³¹ <= x <= 2³¹ - 1
 * <p>
 * 进阶：你能不将整数转为字符串来解决这个问题吗？
 * Related Topics 数学 👍 1704 👎
 */

public class Leetcode9 {
  public static void main(String[] args) {
    Leetcode9 leetcode9 = new Leetcode9();
    System.out.println(leetcode9.isPalindrome(-121));
  }

  public boolean isPalindrome(int x) {
    if(x < 0) return false;
    int pal = getPal(x);
    int[] ints = new int[pal];
    int i = 0;
    while (i < ints.length) {
      ints[i] = x % 10;
      x /= 10;
      i++;
    }

    i = 0;
    int t = ints.length-1;
    while (i < t) {
      if (ints[i++] != ints[t--]) return false;
    }
    return true;
  }

  /**
   * 获取x是几位数
   * @param x
   * @return
   */
  public int getPal(int x) {
    int t = 1;
    while (x / 10 != 0) {
      x /= 10;
      t++;
    }

    return t;
  }

  /**
   * 获取t要除的数
   * @param t
   * @return
   */
  public int getLeft(int t) {
    int m = 1;
    while (--t > 0) {
      m *= 10;
    }

    return m;
  }
}

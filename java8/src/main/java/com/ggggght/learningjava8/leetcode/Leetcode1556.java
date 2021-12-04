package com.ggggght.learningjava8.leetcode;

import java.util.Arrays;

/**
 * 给你一个整数 n，请你每隔三位添加点（即 "." 符号）作为千位分隔符，并将结果以字符串格式返回。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 987
 * 输出："987"
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 1234
 * 输出："1.234"
 * <p>
 * 示例 3：
 * <p>
 * 输入：n = 123456789
 * 输出："123.456.789"
 * <p>
 * <p>
 * 示例 4：
 * <p>
 * 输入：n = 0
 * 输出："0"
 * <p>
 * 提示：
 * <p>
 * <p>
 * 0 <= n < 2^31
 * <p>
 * Related Topics 字符串 👍 15 👎 0
 */
public class Leetcode1556 {
  public static void main(String[] args) {
    Leetcode1556 leetcode1556 = new Leetcode1556();
    System.out.println(leetcode1556.thousandSeparator(1234));
    System.out.println(leetcode1556.thousandSeparator(987));
    System.out.println(leetcode1556.thousandSeparator(123456789));
  }

  /**
   * 使用StringBuilder
   *
   * @param n
   * @return
   */
  public String thousandSeparator(int n) {
    StringBuilder builder = new StringBuilder();
    var a = 0;
    do {
      var mod = n % 10;
      n /= 10;
      builder.append(mod);
      a++;
      if (a % 3 == 0 && n != 0) {
        builder.append(".");
      }
    } while (n != 0);
    return builder.reverse().toString();
  }
}

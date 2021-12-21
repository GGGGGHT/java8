package com.ggggght.learningjava8.leetcode;

import java.util.Arrays;

/**
 * <p>给定两个以字符串形式表示的非负整数&nbsp;<code>num1</code>&nbsp;和&nbsp;<code>num2</code>，返回&nbsp;<code>num1</code>&nbsp;和&nbsp;<code>num2</code>&nbsp;的乘积，它们的乘积也表示为字符串形式。</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre><strong>输入:</strong> num1 = &quot;2&quot;, num2 = &quot;3&quot;
 * <strong>输出:</strong> &quot;6&quot;</pre>
 *
 * <p><strong>示例&nbsp;2:</strong></p>
 *
 * <pre><strong>输入:</strong> num1 = &quot;123&quot;, num2 = &quot;456&quot;
 * <strong>输出:</strong> &quot;56088&quot;</pre>
 *
 * <p><strong>说明：</strong></p>
 *
 * <ol>
 * <li><code>num1</code>&nbsp;和&nbsp;<code>num2</code>&nbsp;的长度小于110。</li>
 * <li><code>num1</code> 和&nbsp;<code>num2</code> 只包含数字&nbsp;<code>0-9</code>。</li>
 * <li><code>num1</code> 和&nbsp;<code>num2</code>&nbsp;均不以零开头，除非是数字 0 本身。</li>
 * <li><strong>不能使用任何标准库的大数类型（比如 BigInteger）</strong>或<strong>直接将输入转换为整数来处理</strong>。</li>
 * </ol>
 * <div><div>Related Topics</div><div><li>数学</li><li>字符串</li><li>模拟</li></div></div><br><div><li>👍 791</li><li>👎 0</li></div>
 */

public class Leetcode43 {
  public static void main(String[] args) {
    // System.out.println("multiply(\"2\" ,\"3\") = " + multiply("2", "3"));
    // System.out.println("multiply(\"123\",\"456\") = " + multiply("123", "456"));
    System.out.println("multiply(\"123\",\"456\") = " + multiply1("498828660196", "840477629533"));
    // System.out.println("multiply(\"123\",\"456\") = " + multiply("123456789", "987654321"));
    System.out.println("multiply2(\"123\",\"456\") = " + multiply2("123", "456"));
  System.out.println("multiply(\"123\",\"456\") = " + multiply2("498828660196", "840477629533"));
  }

  public static String multiply1(String num1, String num2) {
    if (num1.equals("0") || num2.equals("0")) {
      return "0";
    }
    // 保存计算结果
    String res = "0";

    // num2 逐位与 num1 相乘
    for (int i = num2.length() - 1; i >= 0; i--) {
      int carry = 0;
      // 保存 num2 第i位数字与 num1 相乘的结果
      StringBuilder temp = new StringBuilder();
      // 补 0
      for (int j = 0; j < num2.length() - 1 - i; j++) {
        temp.append(0);
      }
      int n2 = num2.charAt(i) - '0';

      // num2 的第 i 位数字 n2 与 num1 相乘
      for (int j = num1.length() - 1; j >= 0 || carry != 0; j--) {
        int n1 = j < 0 ? 0 : num1.charAt(j) - '0';
        int product = (n1 * n2 + carry) % 10;
        temp.append(product);
        carry = (n1 * n2 + carry) / 10;
      }
      // 将当前结果与新计算的结果求和作为新的结果
      res = addString(res, temp.reverse().toString());
    }
    return res;
  }

  /**
   * 对两个字符串数字进行相加，返回字符串形式的和
   */
  public static String addStrings(String num1, String num2) {
    StringBuilder builder = new StringBuilder();
    int carry = 0;
    for (int i = num1.length() - 1, j = num2.length() - 1;
        i >= 0 || j >= 0 || carry != 0;
        i--, j--) {
      int x = i < 0 ? 0 : num1.charAt(i) - '0';
      int y = j < 0 ? 0 : num2.charAt(j) - '0';
      int sum = (x + y + carry) % 10;
      builder.append(sum);
      carry = (x + y + carry) / 10;
    }
    return builder.reverse().toString();
  }

  public static String multiply(String num1, String num2) {
    if (num1.equals("0") || num2.equals("0")) return "0";

    int len = num2.length();
    StringBuilder builder = new StringBuilder();
    String res = "0";
    for (int i = len - 1; i >= 0; i--) {
      builder.setLength(0);
      // 加0
      for (int a = 0; a < len - 1 - i; a++) builder.append('0');
      int j = num2.charAt(i) - '0';
      // 使j与num1中每个数相乘
      int carry = 0;
      for (int a = num1.length() - 1; a >= 0; a--) {
        int t = num1.charAt(a) - '0';
        int p = (j * t + carry) % 10;
        carry = (j * t + carry) / 10;
        builder.append(p);
      }

      if (carry != 0) builder.append(carry);
      res = addString(builder.reverse().toString(), res);
    }

    return res;
  }

  private static String addString(String s1, String s2) {
    StringBuilder builder = new StringBuilder();
    int carry = 0;
    for (int i = s1.length() - 1, j = s2.length() - 1;
        i >= 0 || j >= 0 || carry > 0;
        i--, j--) {
      int a = i >= 0 ? s1.charAt(i) - '0' : 0;
      int b = j >= 0 ? s2.charAt(j) - '0' : 0;
      int t = (a + b + carry) % 10;
      carry = (a + b + carry) / 10;
      builder.append(t);
    }

    return builder.reverse().toString();
  }

  public static String multiply2(String num1, String num2) {
    if(num1.equals("0") || num2.equals("0")) return "0";
    var len1 = num1.length();
    var len2 = num2.length();
    var arr = new int[len1 + len2];
    for (int i = len1 - 1; i >= 0; i--) {
      int i1 = num1.charAt(i) - '0';
      for (int j = len2 - 1; j >= 0; j--) {
        int j1 = num2.charAt(j) - '0';
        int p = i1 * j1 + arr[i + j + 1];
        // 是否需要进位
        arr[i + j] += p / 10;
        arr[i + j + 1] = p % 10;
      }
    }

    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < arr.length; i++) {
      if(i == 0 && arr[i] == 0) continue;
      builder.append(arr[i]);
    }
    return builder.toString();
  }
}

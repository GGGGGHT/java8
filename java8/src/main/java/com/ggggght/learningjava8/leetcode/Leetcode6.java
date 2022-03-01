package com.ggggght.learningjava8.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * <p>
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * <p>
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 * 示例 1：
 * <p>
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：s = "A", numRows = 1
 * 输出："A"
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 * <p>
 * Related Topics 字符串 👍 1383 👎 0
 */
public class Leetcode6 {
  public static void main(String[] args) {
    var str =
        "Apalindromeisaword,phrase,number,orothersequenceofunitsthatcanbereadthesamewayineitherdirection,withgeneralallowancesforadjustmentstopunctuationandworddividers.";
    System.out.println(str.length());
    String paypalishiring = new Leetcode6().convert("PAYPALISHIRING",
        3);
    System.out.println(paypalishiring);
  }

  public String convert(String s, int numRows) {
    if (numRows < 2) return s;
    List<StringBuilder> rows = new ArrayList<StringBuilder>();
    for (int i = 0; i < numRows; i++) rows.add(new StringBuilder());
    int i = 0, flag = -1;
    for (char c : s.toCharArray()) {
      rows.get(i).append(c);
      if (i == 0 || i == numRows - 1) flag = -flag;
      i += flag;
    }
    StringBuilder res = new StringBuilder();
    for (StringBuilder row : rows) res.append(row);
    return res.toString();
  }
  
  /**
    * 对于第一行和最后一行：公差为 2 * (n − 1) 的等差数列，首项是 i 对于其他行：两个公差为 2 * (n − 1) 的等差数列交替排列，首项分别是 i 和 2 * n − i − 2
    */
  public String convert2(String s, int r) {
         int n = s.length();
        if (n == 1 || r == 1) return s;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            if (i == 0 || i == r - 1) {
                int pos = i, offset = 2 * (r - 1);
                while (pos < n) {
                    sb.append(s.charAt(pos));
                    pos += offset;
                }
            } else {
                int pos1 = i, pos2 = 2 * r - i - 2;
                int offset = 2 * (r - 1);
                while (pos1 < n || pos2 < n) {
                    if (pos1 < n) {
                        sb.append(s.charAt(pos1));
                        pos1 += offset;
                    }
                    if (pos2 < n) {
                        sb.append(s.charAt(pos2));
                        pos2 += offset;
                    }
                }
            }
        }
        return sb.toString();
    }
}

// PAYPALISHIRING
// P       I
// A     H R
// Y   S   I
// L I     I  G
// I       N


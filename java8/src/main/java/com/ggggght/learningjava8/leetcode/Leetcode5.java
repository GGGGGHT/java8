package com.ggggght.learningjava8.leetcode;

import java.util.Arrays;

/**
 * <p>给你一个字符串 <code>s</code>，找到 <code>s</code> 中最长的回文子串。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "babad"
 * <strong>输出：</strong>"bab"
 * <strong>解释：</strong>"aba" 同样是符合题意的答案。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "cbbd"
 * <strong>输出：</strong>"bb"
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "a"
 * <strong>输出：</strong>"a"
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "ac"
 * <strong>输出：</strong>"a"
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= s.length <= 1000</code></li>
 * <li><code>s</code> 仅由数字和英文字母（大写和/或小写）组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 4447</li><li>👎 0</li></div>
 */

public class Leetcode5 {
  public static void main(String[] args) {
    Leetcode5 leetcode5 = new Leetcode5();
    leetcode5.longestPalindrome("ababa");
    leetcode5.longestPalindrome("abba");
  }

  public String longestPalindrome(String s) {
    int len;
    if (s == null || (len = s.length()) == 0) return s;
    int count = 2 * len - 1;
    // 最大的长度
    int maxLength = 0;
    // 最大长度所开始的位置
    int startIdx = -1;
    char[] chars = s.toCharArray();
    for (int i = 0; i < count; i++) {
      int left;
      int right;
      if ((i & 1) == 1) {
        // 奇数是以二个字符为中点
        left = i >> 1;
        right = (i >> 1) + 1;
      } else {
        // 偶数是以单字符为中点
        left = i / 2;
        right = i / 2;
      }
      while (left >= 0 && right < len && chars[left] == chars[right]) {
        left--;
        right++;
      }

      // 1 -1 + 1 + 1 + 1
      var l = right - 1 - (left + 1) + 1;
      if (l > maxLength) {
        maxLength = l;
        startIdx = left + 1;
      }
    }
    return s.substring(startIdx, startIdx + maxLength);
  }
}

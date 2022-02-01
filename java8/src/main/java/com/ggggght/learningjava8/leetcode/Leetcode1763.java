package com.ggggght.learningjava8.leetcode;

/**
 * <p>当一个字符串 <code>s</code> 包含的每一种字母的大写和小写形式 <strong>同时</strong> 出现在 <code>s</code> 中，就称这个字符串 <code>s</code> 是 <strong>美好</strong> 字符串。比方说，<code>"abABB"</code> 是美好字符串，因为 <code>'A'</code> 和 <code>'a'</code> 同时出现了，且 <code>'B'</code> 和 <code>'b'</code> 也同时出现了。然而，<code>"abA"</code> 不是美好字符串因为 <code>'b'</code> 出现了，而 <code>'B'</code> 没有出现。</p>
 *
 * <p>给你一个字符串 <code>s</code> ，请你返回 <code>s</code> 最长的 <strong>美好子字符串</strong> 。如果有多个答案，请你返回 <strong>最早</strong> 出现的一个。如果不存在美好子字符串，请你返回一个空字符串。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <b>输入：</b>s = "YazaAay"
 * <b>输出：</b>"aAa"
 * <strong>解释：</strong>"aAa" 是一个美好字符串，因为这个子串中仅含一种字母，其小写形式 'a' 和大写形式 'A' 也同时出现了。
 * "aAa" 是最长的美好子字符串。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <b>输入：</b>s = "Bb"
 * <b>输出：</b>"Bb"
 * <b>解释：</b>"Bb" 是美好字符串，因为 'B' 和 'b' 都出现了。整个字符串也是原字符串的子字符串。</pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <b>输入：</b>s = "c"
 * <b>输出：</b>""
 * <b>解释：</b>没有美好子字符串。</pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <b>输入：</b>s = "dDzeE"
 * <b>输出：</b>"dD"
 * <strong>解释：</strong>"dD" 和 "eE" 都是最长美好子字符串。
 * 由于有多个美好子字符串，返回 "dD" ，因为它出现得最早。</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= s.length <= 100</code></li>
 * <li><code>s</code> 只包含大写和小写英文字母。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>位运算</li><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 58</li><li>👎 0</li></div>
 */

public class Leetcode1763 {
  public static void main(String[] args) {
    Leetcode1763 leetcode1763 = new Leetcode1763();
    System.out.println(leetcode1763.longestNiceSubstring("YazaAay"));
  }
  private int maxPos;
  private int maxLen;

  public String longestNiceSubstring(String s) {
    this.maxPos = 0;
    this.maxLen = 0;

    int types = 0;
    for (int i = 0; i < s.length(); ++i) {
      types |= 1 << (Character.toLowerCase(s.charAt(i)) - 'a');
    }
    types = Integer.bitCount(types);
    for (int i = 1; i <= types; ++i) {
      check(s, i);
    }
    return s.substring(maxPos, maxPos + maxLen);
  }

  public void check(String s, int typeNum) {
    int[] lowerCnt = new int[26];
    int[] upperCnt = new int[26];
    int cnt = 0;
    for (int l = 0, r = 0, total = 0; r < s.length(); ++r) {
      int idx = Character.toLowerCase(s.charAt(r)) - 'a';
      if (Character.isLowerCase(s.charAt(r))) {
        ++lowerCnt[idx];
        if (lowerCnt[idx] == 1 && upperCnt[idx] > 0) {
          ++cnt;
        }
      } else {
        ++upperCnt[idx];
        if (upperCnt[idx] == 1 && lowerCnt[idx] > 0) {
          ++cnt;
        }
      }
      total += (lowerCnt[idx] + upperCnt[idx]) == 1 ? 1 : 0;
      while (total > typeNum) {
        idx = Character.toLowerCase(s.charAt(l)) - 'a';
        total -= (lowerCnt[idx] + upperCnt[idx]) == 1 ? 1 : 0;
        if (Character.isLowerCase(s.charAt(l))) {
          --lowerCnt[idx];
          if (lowerCnt[idx] == 0 && upperCnt[idx] > 0) {
            --cnt;
          }
        } else {
          --upperCnt[idx];
          if (upperCnt[idx] == 0 && lowerCnt[idx] > 0) {
            --cnt;
          }
        }
        ++l;
      }
      if (cnt == typeNum && r - l + 1 > maxLen) {
        maxPos = l;
        maxLen = r - l + 1;
      }
    }
  }

}

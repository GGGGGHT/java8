package com.ggggght.learningjava8.leetcode;

import java.util.Arrays;
import java.util.HashSet;
/**
 <p>把字符串 <code>s</code> 看作是&ldquo;abcdefghijklmnopqrstuvwxyz&rdquo;的无限环绕字符串，所以&nbsp;<code>s</code> 看起来是这样的：&quot;...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....&quot;.&nbsp;</p>

 <p>现在我们有了另一个字符串 <code>p</code> 。你需要的是找出 <code>s</code> 中有多少个唯一的 <code>p</code> 的非空子串，尤其是当你的输入是字符串 <code>p</code> ，你需要输出字符串&nbsp;<code>s</code> 中 <code>p</code> 的不同的非空子串的数目。&nbsp;</p>

 <p><strong>注意:</strong> <code>p</code>&nbsp;仅由小写的英文字母组成，p 的大小可能超过 10000。</p>

 <p>&nbsp;</p>

 <p><strong>示例&nbsp;1:</strong></p>

 <pre>
 <strong>输入:</strong> &quot;a&quot;
 <strong>输出:</strong> 1
 <strong>解释:</strong> 字符串 S 中只有一个&quot;a&quot;子字符。
 </pre>

 <p>&nbsp;</p>

 <p><strong>示例 2:</strong></p>

 <pre>
 <strong>输入:</strong> &quot;cac&quot;
 <strong>输出:</strong> 2
 <strong>解释:</strong> 字符串 S 中的字符串&ldquo;cac&rdquo;只有两个子串&ldquo;a&rdquo;、&ldquo;c&rdquo;。.
 </pre>

 <p>&nbsp;</p>

 <p><strong>示例 3:</strong></p>

 <pre>
 <strong>输入:</strong> &quot;zab&quot;
 <strong>输出:</strong> 6
 <strong>解释:</strong> 在字符串 S 中有六个子串&ldquo;z&rdquo;、&ldquo;a&rdquo;、&ldquo;b&rdquo;、&ldquo;za&rdquo;、&ldquo;ab&rdquo;、&ldquo;zab&rdquo;。.
 </pre>

 <p>&nbsp;</p>
 <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 180</li><li>👎 0</li></div>
 */

public class Leetcode467 {

  public static void main(String[] args) {
    // System.out.println(
    //     "findSubstringInWraproundString(\"a\") = " + findSubstringInWraproundString("a"));
    // System.out.println(
    //     "findSubstringInWraproundString(\"cac\") = " + findSubstringInWraproundString("cac"));
    // System.out.println(
    //     "findSubstringInWraproundString(\"zaba\") = " + findSubstringInWraproundString("zaba"));
    System.out.println(
        "findSubstringInWraproundString(\"aabb\") = " + findSubstringInWraproundString("aabb"));
    System.out.println("findSubstringInWraproundString(\"uvwxyzabcdefg\") = " + findSubstringInWraproundString("zaba"));
    System.out.println("f(\"aabb\") = " + f("aabb"));
    System.out.println("f(\"uvwxyzabcdefg\") = " + f("uvwxyzabcdefg"));
    System.out.println("f(\"zaba\") = " + f("zaba"));
  }

  /**
   * TLE
   *
   * @param p
   * @return
   */
  public static int findSubstringInWraproundString(String p) {
    char[] chars = p.toCharArray();
    if (chars.length <= 0) return 0;

    // dp[i]表示以i结尾的字符的子串个数
    int[] c = new int[26];
    int[] dp = new int[chars.length];
    Arrays.fill(dp, 1);
    // 将单个字符串的值映射上
    c[chars[0] - 'a']++;
    HashSet<String> set = new HashSet<>();
    set.add(p.substring(0, 1));
    for (int i = 1; i < dp.length; i++) {
      set.add(p.substring(i, i + 1));
      if (chars[i] == 'a' && chars[i - 1] == 'z') {
        dp[i] = dp[i - 1] + 1;
        for (int t = 0; t < dp[i - 1]; t++) {
          set.add(p.substring(i - dp[i - 1] + t, i - dp[i - 1] + dp[i]));
        }
      } else {
        if (chars[i] - chars[i - 1] == 1) {
          dp[i] = dp[i - 1] + 1;
          for (int t = 0; t < dp[i - 1]; t++) {
            set.add(p.substring(i - dp[i - 1] + t, i - dp[i - 1] + dp[i]));
          }
        } else {
          if (c[chars[i] - 'a'] != 1) {
            dp[i] = 1;
            set.add(p.substring(i, i + 1));
          }
        }
      }
    }

    return set.size();
  }

  public static int f(String p) {
    char[] chars = p.toCharArray();
    int[] dp = new int[chars.length];
    // 记录字符串最长的匹配长度
    int[] c = new int[26];

    int res = 0;
    dp[0] = 1;
    // 将所有的单个字符都加上
    for (char aChar : chars) {
      if (c[aChar - 'a'] == 0) {
        c[aChar - 'a']++;
        res++;
      }
    }

    for (int i = 1; i < chars.length; i++) {
      int i1 = chars[i] - chars[i - 1];
      if (i1 == 1 || i1 == -25) {
        dp[i] += (dp[i - 1] == 0 ? 1 : dp[i - 1]) + 1;
        // 以chars[i]为结尾的
        if (c[chars[i] - 'a'] < dp[i]) {
          res = res + dp[i] - c[chars[i] - 'a'];
          c[chars[i] - 'a'] = dp[i];
        }
      }
    }

    // System.out.println(Arrays.toString(dp));
    return res;
  }
}

package com.ggggght.learningjava8.leetcode;

import java.util.ArrayList;
import java.util.List;
/**
 <p>给你一个整数&nbsp;<code>n</code>&nbsp;，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于&nbsp;&nbsp;<code>n</code>&nbsp;的 <strong>最简&nbsp;</strong>分数&nbsp;。分数可以以 <strong>任意&nbsp;</strong>顺序返回。</p>

 <p>&nbsp;</p>

 <p><strong>示例 1：</strong></p>

 <pre><strong>输入：</strong>n = 2
 <strong>输出：</strong>[&quot;1/2&quot;]
 <strong>解释：</strong>&quot;1/2&quot; 是唯一一个分母小于等于 2 的最简分数。</pre>

 <p><strong>示例 2：</strong></p>

 <pre><strong>输入：</strong>n = 3
 <strong>输出：</strong>[&quot;1/2&quot;,&quot;1/3&quot;,&quot;2/3&quot;]
 </pre>

 <p><strong>示例 3：</strong></p>

 <pre><strong>输入：</strong>n = 4
 <strong>输出：</strong>[&quot;1/2&quot;,&quot;1/3&quot;,&quot;1/4&quot;,&quot;2/3&quot;,&quot;3/4&quot;]
 <strong>解释：</strong>&quot;2/4&quot; 不是最简分数，因为它可以化简为 &quot;1/2&quot; 。</pre>

 <p><strong>示例 4：</strong></p>

 <pre><strong>输入：</strong>n = 1
 <strong>输出：</strong>[]
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>1 &lt;= n &lt;= 100</code></li>
 </ul>
 <div><div>Related Topics</div><div><li>数学</li><li>字符串</li><li>数论</li></div></div><br><div><li>👍 47</li><li>👎 0</li></div>
 */

public class Leetcode1447 {
  public static void main(String[] args) {
    System.out.println(simplifiedFractions(11));

    // System.out.println(gcd(4,2));
    // System.out.println(gcd(7,3));
  }

  public static List<String> simplifiedFractions(int n) {
    if (n == 1) return new ArrayList<>();

    StringBuilder builder = new StringBuilder();
    List<String> res = new ArrayList<>();
    for (int i = 1; i < n; i++) {
      builder.setLength(0);
      builder.append(i).append('/');
      for (int j = 2; j <= n; j++) {
        if (i == j || j < i || gcd(j, i) != 1) continue;
        if (builder.charAt(builder.length() - 1) != '/') { // 1/10  4 - 1

          builder.delete(builder.indexOf("/") + 1, builder.length());
        }
        builder.append(j);
        res.add(builder.toString());
      }
    }
    return res;
  }

  // 有最小公约数
  static int gcd(int m, int n) {
    if (m < 0 || n < 0) return -1;

    if (n == 0) return m;

    while (m % n != 0) {
      int tmp = m % n;
      m = n;
      n = tmp;
    }

    return n;
  }
}
//  10
// 110
//

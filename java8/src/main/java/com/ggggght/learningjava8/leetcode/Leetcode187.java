package com.ggggght.learningjava8.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>所有 DNA 都由一系列缩写为 <code>'A'</code>，<code>'C'</code>，<code>'G'</code> 和 <code>'T'</code> 的核苷酸组成，例如：<code>"ACGAATTCCG"</code>。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。</p>
 *
 * <p>编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 <code>s</code> 中出现次数超过一次。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * <strong>输出：</strong>["AAAAACCCCC","CCCCCAAAAA"]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "AAAAAAAAAAAAA"
 * <strong>输出：</strong>["AAAAAAAAAA"]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 <= s.length <= 10<sup>5</sup></code></li>
 * <li><code>s[i]</code> 为 <code>'A'</code>、<code>'C'</code>、<code>'G'</code> 或 <code>'T'</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>位运算</li><li>哈希表</li><li>字符串</li><li>滑动窗口</li><li>哈希函数</li><li>滚动哈希</li></div></div><br><div><li>👍 324</li><li>👎 0</li></div>
 */
public class Leetcode187 {
  public static void main(String[] args) {
    System.out.println(findRepeatedDnaSequences("AAAAAAAAAAAAA"));
  }

  /**
   * rabin karp
   * @param s
   * @return
   */
  public static List<String> findRepeatedDnaSequences(String s) {
    char[] chars = s.toCharArray();
    Map<Integer, Integer> hash2Count = new HashMap<>();
    List<String> res = new ArrayList<>();
    int t = 0;
    int p = 1_000_000_000;
    for (int i = 0; i < 10; i++) {
      if (chars[i] == 'A') {
        t = t * 10 + 1;
      } else if (chars[i] == 'C') {
        t = t * 10 + 2;
      } else if (chars[i] == 'G') {
        t = t * 10 + 3;
      } else {
        t = t * 10 + 4;
      }
    }

    hash2Count.put(t, 1);
    for (int i = 1; i <= chars.length - 10; i++) {
      if (chars[i - 1] == 'A') {
        t -= p;
      } else if (chars[i - 1] == 'C') {
        t -= 2 * p;
      } else if (chars[i - 1] == 'G') {
        t -= 3 * p;
      } else {
        t -= 4 * p;
      }

      // 加最后的位置
      if (chars[i + 10 - 1] == 'A') {
        t = t * 10 + 1;
      } else if (chars[i + 10 - 1] == 'C') {
        t = t * 10 + 2;
      } else if (chars[i + 10 - 1] == 'G') {
        t = t * 10 + 3;
      } else {
        t = t * 10 + 4;
      }

      if (hash2Count.containsKey(t) && hash2Count.get(t) == 1) {
        res.add(s.substring(i, i + 10));
        hash2Count.put(t, hash2Count.get(t) + 1);
      }
      if (!hash2Count.containsKey(t)) {
        hash2Count.put(t, 1);
      }
    }
    return res;
  }
}

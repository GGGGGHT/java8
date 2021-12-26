package com.ggggght.learningjava8.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>给出第一个词&nbsp;<code>first</code> 和第二个词&nbsp;<code>second</code>，考虑在某些文本&nbsp;<code>text</code>&nbsp;中可能以 <code>"first second third"</code> 形式出现的情况，其中&nbsp;<code>second</code>&nbsp;紧随&nbsp;<code>first</code>&nbsp;出现，<code>third</code>&nbsp;紧随&nbsp;<code>second</code>&nbsp;出现。</p>
 *
 * <p>对于每种这样的情况，将第三个词 "<code>third</code>" 添加到答案中，并返回答案。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>text = "alice is a good girl she is a good student", first = "a", second = "good"
 * <strong>输出：</strong>["girl","student"]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>text = "we will we will rock you", first = "we", second = "will"
 * <strong>输出：</strong>["we","rock"]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= text.length &lt;= 1000</code></li>
 * <li><code>text</code>&nbsp;由小写英文字母和空格组成</li>
 * <li><code>text</code> 中的所有单词之间都由 <strong>单个空格字符</strong> 分隔</li>
 * <li><code>1 &lt;= first.length, second.length &lt;= 10</code></li>
 * <li><code>first</code> 和&nbsp;<code>second</code>&nbsp;由小写英文字母组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li></div></div><br><div><li>👍 59</li><li>👎 0</li></div>
 */

public class Leetcode1078 {
  public static void main(String[] args) {
    System.out.println(Arrays.toString(findOcurrences("jkypmsxd jkypmsxd kcyxdfnoa jkypmsxd kcyxdfnoa jkypmsxd kcyxdfnoa kcyxdfnoa jkypmsxd kcyxdfnoa", "kcyxdfnoa", "jkypmsxd")));
  }
  public static String[] findOcurrences(String text, String first, String second) {
    List<String> l = new ArrayList<>();
    String[] s = text.split(" ");
    for (int i = 0; i + 2 < s.length; i++) {
      if (s[i].equals(first) && s[i + 1].equals(second)) {
        l.add(s[i + 2]);
      }
    }
    return l.toArray(new String[0]);
  }
}

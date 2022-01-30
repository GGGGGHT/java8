package com.ggggght.learningjava8.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 <p><strong>句子</strong> 是一串由空格分隔的单词。每个 <strong>单词</strong><em> </em>仅由小写字母组成。</p>

 <p>如果某个单词在其中一个句子中恰好出现一次，在另一个句子中却 <strong>没有出现</strong> ，那么这个单词就是 <strong>不常见的</strong><em> </em>。</p>

 <p>给你两个 <strong>句子</strong> <code>s1</code> 和 <code>s2</code> ，返回所有 <strong>不常用单词</strong> 的列表。返回列表中单词可以按 <strong>任意顺序</strong> 组织。</p>

 <p>&nbsp;</p>

 <ol>
 </ol>

 <p><strong>示例 1：</strong></p>

 <pre>
 <strong>输入：</strong>s1 = "this apple is sweet", s2 = "this apple is sour"
 <strong>输出：</strong>["sweet","sour"]
 </pre>

 <p><strong>示例 2：</strong></p>

 <pre>
 <strong>输入：</strong>s1 = "apple apple", s2 = "banana"
 <strong>输出：</strong>["banana"]
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>1 &lt;= s1.length, s2.length &lt;= 200</code></li>
 <li><code>s1</code> 和 <code>s2</code> 由小写英文字母和空格组成</li>
 <li><code>s1</code> 和 <code>s2</code> 都不含前导或尾随空格</li>
 <li><code>s1</code> 和 <code>s2</code> 中的所有单词间均由单个空格分隔</li>
 </ul>
 <div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 118</li><li>👎 0</li></div>
 */

public class Leetcode884 {
  public static void main(String[] args) {
    System.out.println(
        Arrays.toString(uncommonFromSentences("this apple is sweet", "this apple is sour")));
    System.out.println(
        Arrays.toString(uncommonFromSentences("", "banana")));
  }
  public static String[] uncommonFromSentences(String s1, String s2) {
    String[] str1 = s1.split(" ");
    String[] str2 = s2.split(" ");
    HashMap<String, Integer> map = new HashMap<>();
    for (String s : str1) {
      map.put(s, map.getOrDefault(s, 0) + 1);
    }

    for (String s : str2) {
      map.put(s, map.getOrDefault(s, 0) + 1);
    }


    return map.entrySet().stream().filter(e -> e.getValue() == 1).map(Map.Entry::getKey).toList().toArray(String[]::new);
  }
}

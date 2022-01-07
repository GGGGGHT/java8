package com.ggggght.learningjava8.leetcode;

import java.util.ArrayList;
import java.util.List;
/**
 <p>给你一个字符串数组 <code>words</code> ，只返回可以使用在 <strong>美式键盘</strong> 同一行的字母打印出来的单词。键盘如下图所示。</p>

 <p><strong>美式键盘</strong> 中：</p>

 <ul>
 <li>第一行由字符 <code>"qwertyuiop"</code> 组成。</li>
 <li>第二行由字符 <code>"asdfghjkl"</code> 组成。</li>
 <li>第三行由字符 <code>"zxcvbnm"</code> 组成。</li>
 </ul>

 <p><img alt="American keyboard" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/keyboard.png" style="width: 100%; max-width: 600px" /></p>

 <p> </p>

 <p><strong>示例 1：</strong></p>

 <pre>
 <strong>输入：</strong>words = ["Hello","Alaska","Dad","Peace"]
 <strong>输出：</strong>["Alaska","Dad"]
 </pre>

 <p><strong>示例 2：</strong></p>

 <pre>
 <strong>输入：</strong>words = ["omk"]
 <strong>输出：</strong>[]
 </pre>

 <p><strong>示例 3：</strong></p>

 <pre>
 <strong>输入：</strong>words = ["adsdf","sfd"]
 <strong>输出：</strong>["adsdf","sfd"]
 </pre>

 <p> </p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>1 <= words.length <= 20</code></li>
 <li><code>1 <= words[i].length <= 100</code></li>
 <li><code>words[i]</code> 由英文字母（小写和大写字母）组成</li>
 </ul>
 <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 195</li><li>👎 0</li></div>
 */

public class Leetcode500 {
  char[] f = {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'};
  char[] s = {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'};
  char[] t = {'z', 'x', 'c', 'v', 'b', 'n', 'm'};
  char[][] schar = {f, s, t};

  public String[] findWords(String[] words) {

    List<String> list = new ArrayList<>();
    for (String word : words) {
      char[] chars = word.toLowerCase().toCharArray();
      char aChar = chars[0];
      int m = -1;
      for (int i = 0; i < 3 && m == -1; i++) {
        for (char c : schar[i]) {
          if (c == aChar) {
            m = i;
            break;
          }
        }
      }

      boolean match = true;
      for (int i = 1; i < chars.length; i++) {
        if (!exists(schar[m], chars[i])) {
          match = false;
          break;
        }
      }

      if (match) {
        list.add(word);
      }
    }
    return list.toArray(String[]::new);
  }

  private static boolean exists(char[] chars, char c) {
    for (char aChar : chars) {
      if (aChar == c) {
        return true;
      }
    }

    return false;
  }

  static String[] ss = new String[] {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
  static int[] hash = new int[26];

  static {
    for (int i = 0; i < ss.length; i++) {
      for (char c : ss[i].toCharArray()) hash[c - 'a'] = i;
    }
  }

  public String[] findWords1(String[] words) {
    List<String> list = new ArrayList<>();
    out:
    for (String w : words) {
      int t = -1;
      for (char c : w.toCharArray()) {
        c = Character.toLowerCase(c);
        if (t == -1) {
          t = hash[c - 'a'];
        } else if (t != hash[c - 'a']) continue out;
      }
      list.add(w);
    }
    return list.toArray(new String[list.size()]);
  }
}

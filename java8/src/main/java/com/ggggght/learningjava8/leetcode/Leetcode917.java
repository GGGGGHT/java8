package com.ggggght.learningjava8.leetcode;
/**
 <p>给你一个字符串 <code>s</code> ，根据下述规则反转字符串：</p>

 <ul>
 <li>所有非英文字母保留在原有位置。</li>
 <li>所有英文字母（小写或大写）位置反转。</li>
 </ul>

 <p>返回反转后的 <code>s</code><em> 。</em></p>

 <p>&nbsp;</p>

 <ol>
 </ol>

 <p><strong>示例 1：</strong></p>

 <pre>
 <strong>输入：</strong>s = "ab-cd"
 <strong>输出：</strong>"dc-ba"
 </pre>

 <ol>
 </ol>

 <p><strong>示例 2：</strong></p>

 <pre>
 <strong>输入：</strong>s = "a-bC-dEf-ghIj"
 <strong>输出：</strong>"j-Ih-gfE-dCba"
 </pre>

 <ol>
 </ol>

 <p><strong>示例 3：</strong></p>

 <pre>
 <strong>输入：</strong>s = "Test1ng-Leet=code-Q!"
 <strong>输出：</strong>"Qedo1ct-eeLg=ntse-T!"
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示</strong></p>

 <ul>
 <li><code>1 &lt;= s.length &lt;= 100</code></li>
 <li><code>s</code> 仅由 ASCII 值在范围 <code>[33, 122]</code> 的字符组成</li>
 <li><code>s</code> 不含 <code>'\"'</code> 或 <code>'\\'</code></li>
 </ul>
 <div><div>Related Topics</div><div><li>双指针</li><li>字符串</li></div></div><br><div><li>👍 130</li><li>👎 0</li></div>
 */

public class Leetcode917 {
  public static void main(String[] args) {
    System.out.println(reverseOnlyLetters("ab-cd"));
    System.out.println(reverseOnlyLetters("a-bC-dEf-ghIj")); // j-Ih-Cba
    System.out.println(reverseOnlyLetters("Test1ng-Leet=code-Q!"));
  }

  public static String reverseOnlyLetters(String s) {
    var r = s.length() - 1;
    char[] chars = s.toCharArray();
    int l = 0;
    while (l < r) {
      if (Character.isLetter(chars[l]) && Character.isLetter(chars[r])) {
        var tmp = chars[l];
        chars[l] = chars[r];
        chars[r] = tmp;
        l++;
        r--;
      } else {
        if (Character.isLetter(chars[l])) {
          r--;
        } else {
          l++;
        }
      }
    }
    return new String(chars);
  }
}

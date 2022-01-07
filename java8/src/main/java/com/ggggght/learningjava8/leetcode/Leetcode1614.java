package com.ggggght.learningjava8.leetcode;

/**
 <p>如果字符串满足以下条件之一，则可以称之为 <strong>有效括号字符串</strong><strong>（valid parentheses string</strong>，可以简写为 <strong>VPS</strong>）：</p>

 <ul>
 <li>字符串是一个空字符串 <code>""</code>，或者是一个不为 <code>"("</code> 或 <code>")"</code> 的单字符。</li>
 <li>字符串可以写为 <code>AB</code>（<code>A</code> 与 <code>B</code> 字符串连接），其中 <code>A</code> 和 <code>B</code> 都是 <strong>有效括号字符串</strong> 。</li>
 <li>字符串可以写为 <code>(A)</code>，其中 <code>A</code> 是一个 <strong>有效括号字符串</strong> 。</li>
 </ul>

 <p>类似地，可以定义任何有效括号字符串 <code>S</code> 的 <strong>嵌套深度</strong> <code>depth(S)</code>：</p>

 <ul>
 <li><code>depth("") = 0</code></li>
 <li><code>depth(C) = 0</code>，其中 <code>C</code> 是单个字符的字符串，且该字符不是 <code>"("</code> 或者 <code>")"</code></li>
 <li><code>depth(A + B) = max(depth(A), depth(B))</code>，其中 <code>A</code> 和 <code>B</code> 都是 <strong>有效括号字符串</strong></li>
 <li><code>depth("(" + A + ")") = 1 + depth(A)</code>，其中 <code>A</code> 是一个 <strong>有效括号字符串</strong></li>
 </ul>

 <p>例如：<code>""</code>、<code>"()()"</code>、<code>"()(()())"</code> 都是 <strong>有效括号字符串</strong>（嵌套深度分别为 0、1、2），而 <code>")("</code> 、<code>"(()"</code> 都不是 <strong>有效括号字符串</strong> 。</p>

 <p>给你一个 <strong>有效括号字符串</strong> <code>s</code>，返回该字符串的<em> </em><code>s</code> <strong>嵌套深度</strong> 。</p>

 <p> </p>

 <p><strong>示例 1：</strong></p>

 <pre>
 <strong>输入：</strong>s = "(1+(2*3)+((<strong>8</strong>)/4))+1"
 <strong>输出：</strong>3
 <strong>解释：</strong>数字 8 在嵌套的 3 层括号中。
 </pre>

 <p><strong>示例 2：</strong></p>

 <pre>
 <strong>输入：</strong>s = "(1)+((2))+(((<strong>3</strong>)))"
 <strong>输出：</strong>3
 </pre>

 <p><strong>示例 3：</strong></p>

 <pre>
 <strong>输入：</strong>s = "1+(<strong>2</strong>*3)/(2-1)"
 <strong>输出：</strong>1
 </pre>

 <p><strong>示例 4：</strong></p>

 <pre>
 <strong>输入：</strong>s = "<strong>1</strong>"
 <strong>输出：</strong>0
 </pre>

 <p> </p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>1 <= s.length <= 100</code></li>
 <li><code>s</code> 由数字 <code>0-9</code> 和字符 <code>'+'</code>、<code>'-'</code>、<code>'*'</code>、<code>'/'</code>、<code>'('</code>、<code>')'</code> 组成</li>
 <li>题目数据保证括号表达式 <code>s</code> 是 <strong>有效的括号表达式</strong></li>
 </ul>
 <div><div>Related Topics</div><div><li>栈</li><li>字符串</li></div></div><br><div><li>👍 56</li><li>👎 0</li></div>
 */

public class Leetcode1614 {
  public static void main(String[] args) {
    var s = "(1+(2*3)+((8)/4))+1";
    var t = "(1)+((2))+(((3)))";
    System.out.println(maxDepth(s));
    System.out.println(maxDepth(t));
    System.out.println(maxDepth("1"));
    System.out.println(maxDepth("1+(2*3)/(2-1)"));
    System.out.println(maxDepth("()(()())"));
  }
  public static int maxDepth(String s) {
    char[] chars = s.toCharArray();
    int maxLeft = 0, currLeft = 0;
    // Stack<Character> stack = new Stack<>();
    for (char aChar : chars) {
      if (aChar == '(') {
        currLeft += 1;
        maxLeft = Math.max(currLeft, maxLeft);
      }

      if (aChar == ')') {
        currLeft -= 1;
      }
    }

    return maxLeft;
  }

}

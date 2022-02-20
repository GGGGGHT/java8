package com.ggggght.learningjava8.leetcode;

import java.util.Stack;
/**
 <p>有两种特殊字符：</p>

 <ul>
 <li>第一种字符可以用一个比特&nbsp;<code>0</code>&nbsp;来表示</li>
 <li>第二种字符可以用两个比特(<code>10</code>&nbsp;或&nbsp;<code>11</code>)来表示、</li>
 </ul>

 <p>给定一个以 <code>0</code> 结尾的二进制数组&nbsp;<code>bits</code>&nbsp;，如果最后一个字符必须是一位字符，则返回 <code>true</code> 。</p>

 <p>&nbsp;</p>

 <p><strong>示例&nbsp;1:</strong></p>

 <pre>
 <strong>输入:</strong> bits = [1, 0, 0]
 <strong>输出:</strong> true
 <strong>解释:</strong> 唯一的编码方式是一个两比特字符和一个一比特字符。
 所以最后一个字符是一比特字符。
 </pre>

 <p><strong>示例&nbsp;2:</strong></p>

 <pre>
 <strong>输入:</strong> bits = [1, 1, 1, 0]
 <strong>输出:</strong> false
 <strong>解释:</strong> 唯一的编码方式是两比特字符和两比特字符。
 所以最后一个字符不是一比特字符。
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示:</strong></p>

 <ul>
 <li><code>1 &lt;= bits.length &lt;= 1000</code></li>
 <li><code>bits[i] == 0 or 1</code></li>
 </ul>
 <div><div>Related Topics</div><div><li>数组</li></div></div><br><div><li>👍 217</li><li>👎 0</li></div>
 */

public class Leetcode717 {
  public static void main(String[] args) {
    System.out.println(isOneBitCharacter(new int[] {1, 0, 0}));
    System.out.println(isOneBitCharacter(new int[] {1, 1, 1, 0}));
  }

  public static boolean isOneBitCharacter(int[] bits) {

    Stack<Integer> stack = new Stack<>();
    int len = bits.length;
    for (int i = 0; i < len; i++) {
      if (bits[i] == 0) {
        stack.push(0);
      } else {
        // bits = 1
        if (i + 1 < len) {
          stack.push(10 + bits[++i]);
        }
      }
    }
    return stack.peek() == 0;
  }

  /**
   * 使用 n 代指 bits 的长度，idx 为当前「比特字」的开头，从前往后扫描每个「比特字」，如果最后一个「比特字」的开头为 n - 1 返回 True，否则返回 False。
   *
   * @param bits
   * @return
   */
  public boolean isOneBitCharacter1(int[] bits) {
    int n = bits.length, idx = 0;
    while (idx < n - 1) {
      if (bits[idx] == 0) idx++;
      else idx += 2;
    }
    return idx == n - 1;
  }

}

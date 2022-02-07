package com.ggggght.learningjava8.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 <p>如果字符串中不含有任何 <code>&#39;aaa&#39;</code>，<code>&#39;bbb&#39;</code> 或 <code>&#39;ccc&#39;</code> 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。</p>

 <p>给你三个整数 <code>a</code>，<code>b</code> ，<code>c</code>，请你返回 <strong>任意一个</strong> 满足下列全部条件的字符串 <code>s</code>：</p>

 <ul>
 <li><code>s</code> 是一个尽可能长的快乐字符串。</li>
 <li><code>s</code> 中 <strong>最多</strong> 有<code>a</code> 个字母 <code>&#39;a&#39;</code>、<code>b</code>&nbsp;个字母 <code>&#39;b&#39;</code>、<code>c</code> 个字母 <code>&#39;c&#39;</code> 。</li>
 <li><code>s </code>中只含有 <code>&#39;a&#39;</code>、<code>&#39;b&#39;</code> 、<code>&#39;c&#39;</code> 三种字母。</li>
 </ul>

 <p>如果不存在这样的字符串 <code>s</code> ，请返回一个空字符串 <code>&quot;&quot;</code>。</p>

 <p>&nbsp;</p>

 <p><strong>示例 1：</strong></p>

 <pre><strong>输入：</strong>a = 1, b = 1, c = 7
 <strong>输出：</strong>&quot;ccaccbcc&quot;
 <strong>解释：</strong>&quot;ccbccacc&quot; 也是一种正确答案。
 </pre>

 <p><strong>示例 2：</strong></p>

 <pre><strong>输入：</strong>a = 2, b = 2, c = 1
 <strong>输出：</strong>&quot;aabbc&quot;
 </pre>

 <p><strong>示例 3：</strong></p>

 <pre><strong>输入：</strong>a = 7, b = 1, c = 0
 <strong>输出：</strong>&quot;aabaa&quot;
 <strong>解释：</strong>这是该测试用例的唯一正确答案。</pre>

 <p>&nbsp;</p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>0 &lt;= a, b, c &lt;= 100</code></li>
 <li><code>a + b + c &gt; 0</code></li>
 </ul>
 <div><div>Related Topics</div><div><li>贪心</li><li>字符串</li><li>堆（优先队列）</li></div></div><br><div><li>👍 138</li><li>👎 0</li></div>
 */

public class Leetcode1405 {
  public static void main(String[] args) {
    System.out.println(longestDiverseString(2, 2, 1));
  }
  /**
   * 容易想到：每次都取当前剩余次数最多的字符来进行构造（前提是满足「不出现形如 aaa 字符串」的要求）。
   *
   * 具体的，可以使用「优先队列（堆）」来实现上述过程，以 （字符编号, 字符剩余数量） 的二元组形式进行存储，构建以 字符剩余数量 排倒序的「大根堆」：
   *
   * 起始先将 (0, a)、(1, b) 和 (2, c) 进行入堆（其中 123 为字符编号，代指 abc，同时规定只有剩余数量大于 0 才能入堆）；
   * 每次取出堆顶元素（剩余数量最多的字符），尝试参与答案的构造：
   * 不违反连续三个字符相同：则说明当前字符能够追加到当前答案尾部，若追加后还有字符剩余，则更新剩余数量重新入堆；
   * 违反连续三个字符相同：说明该字符无法追加到当前答案尾部，此时尝试从堆中取出剩余次数次大的字符（若当前堆为空，说明没有任何合法字符能够追加，直接 break），若次大字符追加后还有字符剩余，则更新剩余数量重新入堆，同时将此前取的最大字符元祖也重新入堆；
   * 重复步骤 22，直到所有字符均被消耗，或循环提前结束。
   * 该做法的正确性：当 a = b = c \neq 0a=b=c
   * @param a
   * @param b
   * @param c
   * @return
   */
  public static String longestDiverseString(int a, int b, int c) {
    PriorityQueue<int[]> q = new PriorityQueue<>((x,y)->y[1]-x[1]);
    if (a > 0) q.add(new int[]{0, a});
    if (b > 0) q.add(new int[]{1, b});
    if (c > 0) q.add(new int[]{2, c});
    StringBuilder sb = new StringBuilder();
    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int n = sb.length();
      if (n >= 2 && sb.charAt(n - 1) - 'a' == cur[0] && sb.charAt(n - 2) - 'a' == cur[0]) {
        if (q.isEmpty()) break;
        int[] next = q.poll();
        sb.append((char)(next[0] + 'a'));
        if (--next[1] != 0) q.add(next);
        q.add(cur);
      } else {
        sb.append((char)(cur[0] + 'a'));
        if (--cur[1] != 0) q.add(cur);
      }
    }
    return sb.toString();
  }


  public String longestDiverseString2(int a, int b, int c) {

    Map<Character, Integer> map = new HashMap<>();
    if (a != 0) {
      map.put('a', a);
    }
    if (b != 0) {
      map.put('b', b);
    }
    if (c != 0) {
      map.put('c', c);
    }
    Queue<Character> queue = new PriorityQueue<>(Comparator.comparingInt(map::get).reversed());

    for (Character character : map.keySet()) {
      queue.add(character);
    }
    StringBuilder builder = new StringBuilder();
    // 保存前一个
    Character previous_1 = null;
    // 保存前二个
    Character previous_2 = null;

    while (!queue.isEmpty()) {
      Character aChar = queue.poll();
      Character append = aChar;
      // 保存第二个
      // 保存前一个
      if (builder.length() >= 2) {
        previous_1 = builder.charAt(builder.length() - 2);
        previous_2 = builder.charAt(builder.length() - 1);
      }

      if (previous_1 == previous_2 && previous_1 == aChar) {
        append = queue.poll();
        if (map.get(append) == null) {
          return builder.toString();
        } else {
          if (map.get(append) == 1) {
            map.remove(append);
          } else {
            map.put(append, map.get(append) - 1);
            queue.add(append);
          }
        }
      }
      builder.append(append);

      if (append == aChar) {
        Integer integer = map.get(aChar);
        if (--integer == 0) {
          map.remove(aChar);
        } else {
          map.put(aChar, integer);
          queue.add(aChar);
        }
      } else {
        queue.add(aChar);
      }
    }

    return builder.toString();
  }
}

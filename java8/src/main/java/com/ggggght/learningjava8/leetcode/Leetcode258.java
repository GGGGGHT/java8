package com.ggggght.learningjava8.leetcode;
/**
 <p>给定一个非负整数 <code>num</code>，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。</p>

 <p>&nbsp;</p>

 <p><strong>示例 1:</strong></p>

 <pre>
 <strong>输入:</strong> num =<strong> </strong><code>38</code>
 <strong>输出:</strong> 2
 <strong>解释: </strong>各位相加的过程为<strong>：
 </strong>38 --&gt; 3 + 8 --&gt; 11
 11 --&gt; 1 + 1 --&gt; 2
 由于&nbsp;<code>2</code> 是一位数，所以返回 2。
 </pre>

 <p><strong>示例 1:</strong></p>

 <pre>
 <strong>输入:</strong> num =<strong> </strong>0
 <strong>输出:</strong> 0</pre>

 <p>&nbsp;</p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>0 &lt;= num &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
 </ul>

 <p>&nbsp;</p>

 <p><strong>进阶：</strong>你可以不使用循环或者递归，在 <code>O(1)</code> 时间复杂度内解决这个问题吗？</p>
 <div><div>Related Topics</div><div><li>数学</li><li>数论</li><li>模拟</li></div></div><br><div><li>👍 460</li><li>👎 0</li></div>
 */

public class Leetcode258 {
  public int addDigits(int num) {
    return num == 0 ? 0 : num % 9 == 0 ? 9 : num % 9;
  }
}

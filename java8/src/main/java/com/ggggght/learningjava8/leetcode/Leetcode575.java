package com.ggggght.learningjava8.leetcode;

import java.util.HashSet;
/**
 <p>Alice 有 <code>n</code> 枚糖，其中第 <code>i</code> 枚糖的类型为 <code>candyType[i]</code> 。Alice 注意到她的体重正在增长，所以前去拜访了一位医生。</p>

 <p>医生建议 Alice 要少摄入糖分，只吃掉她所有糖的 <code>n / 2</code> 即可（<code>n</code> 是一个偶数）。Alice 非常喜欢这些糖，她想要在遵循医生建议的情况下，尽可能吃到最多不同种类的糖。</p>

 <p>给你一个长度为 <code>n</code> 的整数数组 <code>candyType</code> ，返回： Alice <em>在仅吃掉 <code>n / 2</code> 枚糖的情况下，可以吃到糖的 <strong>最多</strong> 种类数</em>。</p>

 <p>&nbsp;</p>

 <p><strong>示例 1：</strong></p>

 <pre>
 <strong>输入：</strong>candyType = [1,1,2,2,3,3]
 <strong>输出：</strong>3
 <strong>解释：</strong>Alice 只能吃 6 / 2 = 3 枚糖，由于只有 3 种糖，她可以每种吃一枚。
 </pre>

 <p><strong>示例 2：</strong></p>

 <pre>
 <strong>输入：</strong>candyType = [1,1,2,3]
 <strong>输出：</strong>2
 <strong>解释：</strong>Alice 只能吃 4 / 2 = 2 枚糖，不管她选择吃的种类是 [1,2]、[1,3] 还是 [2,3]，她只能吃到两种不同类的糖。
 </pre>

 <p><strong>示例 3：</strong></p>

 <pre>
 <strong>输入：</strong>candyType = [6,6,6,6]
 <strong>输出：</strong>1
 <strong>解释：</strong>Alice 只能吃 4 / 2 = 2 枚糖，尽管她能吃 2 枚，但只能吃到 1 种糖。
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>n == candyType.length</code></li>
 <li><code>2 &lt;= n &lt;= 10<sup>4</sup></code></li>
 <li><code>n</code> 是一个偶数</li>
 <li><code>-10<sup>5</sup> &lt;= candyType[i] &lt;= 10<sup>5</sup></code></li>
 </ul>
 <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li></div></div><br><div><li>👍 200</li><li>👎 0</li></div>
 */

public class Leetcode575 {
  /**
   * 要去重时,多考虑set集合
   * @param candyType
   * @return
   */
  public int distributeCandies(int[] candyType) {
    HashSet< Integer > set = new HashSet < > ();
    for (int candy: candyType) {
      set.add(candy);
    }
    return Math.min(set.size(), candyType.length / 2);

  }

}

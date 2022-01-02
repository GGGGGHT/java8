package com.ggggght.learningjava8.leetcode;

import java.util.ArrayList;
import java.util.List;
/**
 <p>列表 <code>arr</code> 由在范围 <code>[1, n]</code> 中的所有整数组成，并按严格递增排序。请你对 <code>arr</code> 应用下述算法：</p>

 <div class="original__bRMd">
 <div>
 <ul>
 <li>从左到右，删除第一个数字，然后每隔一个数字删除一个，直到到达列表末尾。</li>
 <li>重复上面的步骤，但这次是从右到左。也就是，删除最右侧的数字，然后剩下的数字每隔一个删除一个。</li>
 <li>不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。</li>
 </ul>

 <p>给你整数 <code>n</code> ，返回 <code>arr</code> 最后剩下的数字。</p>

 <p>&nbsp;</p>

 <p><strong>示例 1：</strong></p>

 <pre>
 <strong>输入：</strong>n = 9
 <strong>输出：</strong>6
 <strong>解释：</strong>
 arr = [<strong><em>1</em></strong>, 2, <em><strong>3</strong></em>, 4, <em><strong>5</strong></em>, 6, <em><strong>7</strong></em>, 8, <em><strong>9</strong></em>]
 arr = [2, <em><strong>4</strong></em>, 6, <em><strong>8</strong></em>]
 arr = [<em><strong>2</strong></em>, 6]
 arr = [6]
 </pre>

 <p><strong>示例 2：</strong></p>

 <pre>
 <strong>输入：</strong>n = 1
 <strong>输出：</strong>1
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
 </ul>
 </div>
 </div>
 <div><div>Related Topics</div><div><li>数学</li></div></div><br><div><li>👍 176</li><li>👎 0</li></div>
 */

public class Leetcode390 {
  public static void main(String[] args) {
    for (int i = 1; i < 30; i++) {
      System.out.printf("%d -> %d\n", i, lastRemaining(i));
    }
  }

  public static int lastRemaining(int n) {
    if (n == 1) {
      return 1;
    }

    List<Integer> list = new ArrayList<>();
    for (int i = 2; i <= n; i += 2) {
      list.add(i);
    }

    int i = 0;
    while (list.size() != 1) {
      if (i == 0) {
        i = 1;
        // 反向删除 删除奇数
        for (int m = list.size() - 1; m >= 0; m -= 2) {
          list.remove(m);
        }
      } else {
        i = 0;
        for (int m = 0; m < list.size(); m++) {
          list.remove(m);
        }
      }
    }
    return list.get(0);
  }

  public int lastRemaining2(int n) {
    return n == 1 ? 1 : 2 * (n / 2 + 1 - lastRemaining2(n / 2));
  }
}

// 19
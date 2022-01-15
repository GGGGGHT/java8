package com.ggggght.learningjava8.leetcode;

/**
 * <p>Hercy 想要为购买第一辆车存钱。他 <strong>每天</strong> 都往力扣银行里存钱。</p>
 *
 * <p>最开始，他在周一的时候存入 <code>1</code> 块钱。从周二到周日，他每天都比前一天多存入 <code>1</code> 块钱。在接下来每一个周一，他都会比 <strong>前一个周一</strong> 多存入 <code>1</code> 块钱。<span style=""> </span></p>
 *
 * <p>给你 <code>n</code> ，请你返回在第 <code>n</code> 天结束的时候他在力扣银行总共存了多少块钱。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><b>输入：</b>n = 4
 * <b>输出：</b>10
 * <b>解释：</b>第 4 天后，总额为 1 + 2 + 3 + 4 = 10 。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><b>输入：</b>n = 10
 * <b>输出：</b>37
 * <b>解释：</b>第 10 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4) = 37 。注意到第二个星期一，Hercy 存入 2 块钱。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre><b>输入：</b>n = 20
 * <b>输出：</b>96
 * <b>解释：</b>第 20 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4 + 5 + 6 + 7 + 8) + (3 + 4 + 5 + 6 + 7 + 8) = 96 。
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= n &lt;= 1000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数学</li></div></div><br><div><li>👍 31</li><li>👎 0</li></div>
 */

public class Leetcode1716 {
  /**
   * 模拟
   * @param n
   * @return
   */
  public int totalMoney(int n) {
    int res = 1;
    int count = 0;
    for (int i = 2; i <= n; i++) {
      res += (i % 7 == 0) ? 7 + count : i % 7 + count;
      if (i % 7 == 0) {
        count++;
      }
    }
    return res;
  }
}

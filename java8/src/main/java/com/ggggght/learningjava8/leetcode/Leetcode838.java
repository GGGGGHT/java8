package com.ggggght.learningjava8.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 <p><code>n</code> 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。</p>

 <p>每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。</p>

 <p>如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。</p>

 <p>就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。</p>

 <p>给你一个字符串 <code>dominoes</code> 表示这一行多米诺骨牌的初始状态，其中：</p>

 <ul>
 <li><code>dominoes[i] = 'L'</code>，表示第 <code>i</code> 张多米诺骨牌被推向左侧，</li>
 <li><code>dominoes[i] = 'R'</code>，表示第 <code>i</code> 张多米诺骨牌被推向右侧，</li>
 <li><code>dominoes[i] = '.'</code>，表示没有推动第 <code>i</code> 张多米诺骨牌。</li>
 </ul>

 <p>返回表示最终状态的字符串。</p>
 &nbsp;

 <p><strong>示例 1：</strong></p>

 <pre>
 <strong>输入：</strong>dominoes = "RR.L"
 <strong>输出：</strong>"RR.L"
 <strong>解释：</strong>第一张多米诺骨牌没有给第二张施加额外的力。
 </pre>

 <p><strong>示例 2：</strong></p>
 <img alt="" src="https://s3-lc-upload.s3.amazonaws.com/uploads/2018/05/18/domino.png" style="height: 196px; width: 512px;" />
 <pre>
 <strong>输入：</strong>dominoes = ".L.R...LR..L.."
 <strong>输出：</strong>"LL.RR.LLRRLL.."
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>n == dominoes.length</code></li>
 <li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
 <li><code>dominoes[i]</code> 为 <code>'L'</code>、<code>'R'</code> 或 <code>'.'</code></li>
 </ul>
 <div><div>Related Topics</div><div><li>双指针</li><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 227</li><li>👎 0</li></div>
 */
public class Leetcode838 {
  public static void main(String[] args) {
    System.out.println(pushDominoes("RR.L"));
    System.out.println(pushDominoes(".L.R...LR..L.."));
  }
  /**
   * 推倒骨牌是一个行为传递的过程，可以使用 BFS 来进行模拟。
   *
   * 起始将所有不为 . 的骨牌以 (loc, time, dire)(loc,time,dire) 三元组的形式进行入队，三元组所代表的含义为「位置为 loc的骨牌在 time时刻受到一个方向为 dire的力」，然后进行常规的 BFS 即可。
   *
   * 在受力（入队）时，我们尝试修改骨牌的状态，同时为了解决「一个骨牌同时受到左右推力时，维持站立状态不变」的问题，我们需要在尝试修改骨牌状态后，额外记录下该骨牌的状态修改时间，如果在同一时间内，一块骨牌受力两次（只能是来自左右两个方向的力），需要将该骨牌恢复成竖立状态。
   *
   * @param dominoes
   * @return
   */
  public static String pushDominoes(String dominoes) {
    char[] cs = dominoes.toCharArray();
    int n = cs.length;
    int[] g = new int[n];
    Deque<int[]> d = new ArrayDeque<>();
    for (int i = 0; i < n; i++) {
      if (cs[i] == '.') continue;
      int dire = cs[i] == 'L' ? -1 : 1;
      d.add(new int[]{i, 1, dire});
      g[i] = 1;
    }
    while (!d.isEmpty()) {
      int[] info = d.pollFirst();
      int loc = info[0], time = info[1], dire = info[2];
      int ne = loc + dire;
      if (cs[loc] == '.' || (ne < 0 || ne >= n)) continue;
      if (g[ne] == 0) { // 首次受力
        d.addLast(new int[]{ne, time + 1, dire});
        g[ne] = time + 1;
        cs[ne] = dire == -1 ? 'L' : 'R';
      } else if (g[ne] == time + 1) { // 多次受力
        cs[ne] = '.';
      }
    }
    return String.valueOf(cs);

  }
}

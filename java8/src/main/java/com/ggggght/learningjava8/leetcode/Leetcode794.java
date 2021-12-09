package com.ggggght.learningjava8.leetcode;

import java.util.Arrays;

/**
 * <p>给你一个字符串数组 <code>board</code> 表示井字游戏的棋盘。当且仅当在井字游戏过程中，棋盘有可能达到 <code>board</code> 所显示的状态时，才返回 <code>true</code> 。</p>
 *
 * <p>井字游戏的棋盘是一个 <code>3 x 3</code> 数组，由字符 <code>' '</code>，<code>'X'</code> 和 <code>'O'</code> 组成。字符 <code>' '</code> 代表一个空位。</p>
 *
 * <p>以下是井字游戏的规则：</p>
 *
 * <ul>
 * <li>玩家轮流将字符放入空位（<code>' '</code>）中。</li>
 * <li>玩家 1 总是放字符 <code>'X'</code> ，而玩家 2 总是放字符 <code>'O'</code> 。</li>
 * <li><code>'X'</code> 和 <code>'O'</code> 只允许放置在空位中，不允许对已放有字符的位置进行填充。</li>
 * <li>当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。</li>
 * <li>当所有位置非空时，也算为游戏结束。</li>
 * <li>如果游戏结束，玩家不允许再放置字符。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/05/15/tictactoe1-grid.jpg" style="width: 253px; height: 253px;" />
 * <pre>
 * <strong>输入：</strong>board = ["O  ","   ","   "]
 * <strong>输出：</strong>false
 * <strong>解释：</strong>玩家 1 总是放字符 "X" 。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/05/15/tictactoe2-grid.jpg" style="width: 253px; height: 253px;" />
 * <pre>
 * <strong>输入：</strong>board = ["XOX"," X ","   "]
 * <strong>输出：</strong>false
 * <strong>解释：</strong>玩家应该轮流放字符。</pre>
 *
 * <p><strong>示例 3：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/05/15/tictactoe3-grid.jpg" style="width: 253px; height: 253px;" />
 * <pre>
 * <strong>输入：</strong>board = ["XXX","   ","OOO"]
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p><strong>Example 4:</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/05/15/tictactoe4-grid.jpg" style="width: 253px; height: 253px;" />
 * <pre>
 * <strong>输入：</strong>board = ["XOX","O O","XOX"]
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>board.length == 3</code></li>
 * <li><code>board[i].length == 3</code></li>
 * <li><code>board[i][j]</code> 为 <code>'X'</code>、<code>'O'</code> 或 <code>' '</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>字符串</li></div></div><br><div><li>👍 65</li><li>👎 0</li></div>
 */

public class Leetcode794 {
  public static void main(String[] args) {
    Leetcode794 leetcode794 = new Leetcode794();
    String[] strings = {"O  ","   ","   "};
    System.out.println(leetcode794.validTicTacToe(strings));
  }
  public boolean validTicTacToe(String[] board) {
    // 统计 x和o出现的次数 x=0 o=1
    int xLen = 0;
    int oLen = 0;
    int[][] dp = new int[3][3];
    for (int[] ints : dp) {
      Arrays.fill(ints, -1);
    }
    for (int i = 0; i < board.length; i++) {
      char[] chars = board[i].toCharArray();
      for (int i1 = 0; i1 < chars.length; i1++) {
        //
        char aChar = chars[i1];
        if(aChar == 'O') {
          oLen++;
          dp[i][i1] = 1;
          continue;
        }

        if(aChar == 'X') {
          xLen++;
          dp[i][i1] = 0;
        }
      }
    }
    // 第一个玩家先放x 第二个玩家放o 所以 x的次数可能等于o或者比o多1
    if (xLen < oLen || xLen - oLen > 1) {
      return false;
    }

    // 只需要看第一行和第一列
    // 第一行
    for (int i = 0; i < 3; i++) {
      // 第1行的元素
      int m = dp[0][i];
      if (m == -1) continue;
      // 第一行第一列 即[0,0]位置的元素 需要看三个方向 横向 竖向 斜向
      if (i == 0) {
        // 横向
        if (((m == dp[0][1]) && (m == dp[0][2])) || (m == dp[1][0] && m == dp[2][0]) || (m
            == dp[1][1] && m == dp[2][2])) {
          return m == 0 ? xLen - 1 == oLen : xLen == oLen;
        }
      } else if (i == 2) {
        // [0,2]位置 只需要看竖向与斜向
        if ((m == dp[1][2] && m == dp[2][2]) || (m == dp[1][1] && m == dp[2][0])) {
          return m == 0 ? xLen - 1 == oLen : xLen == oLen;
        }
      } else {
        // 只需要看竖向
        if (m == dp[1][1] && m == dp[2][1]) {
          return m == 0 ? xLen - 1 == oLen : xLen == oLen;
        }
      }
    }

    // 第一列
    for (int i = 1; i < 3; i++) {
      int m = dp[i][0];
      if(m == -1) continue;
      if (m == dp[i][1] && m == dp[i][2]) {
        return m == 0 ? xLen - 1 == oLen : xLen == oLen;
      }
    }

    return true;
  }
}

package com.ggggght.learningjava8.leetcode;

import java.util.Comparator;
import java.util.HashMap;
/**
 <p>给你一个数组 <code>rectangles</code> ，其中 <code>rectangles[i] = [l<sub>i</sub>, w<sub>i</sub>]</code> 表示第 <code>i</code> 个矩形的长度为 <code>l<sub>i</sub></code> 、宽度为 <code>w<sub>i</sub></code> 。</p>

 <p>如果存在 <code>k</code> 同时满足 <code>k <= l<sub>i</sub></code> 和 <code>k <= w<sub>i</sub></code> ，就可以将第 <code>i</code> 个矩形切成边长为 <code>k</code> 的正方形。例如，矩形 <code>[4,6]</code> 可以切成边长最大为 <code>4</code> 的正方形。</p>

 <p>设 <code>maxLen</code> 为可以从矩形数组 <code>rectangles</code> 切分得到的 <strong>最大正方形</strong> 的边长。</p>

 <p>请你统计有多少个矩形能够切出边长为<em> </em><code>maxLen</code> 的正方形，并返回矩形 <strong>数目</strong> 。</p>

 <p> </p>

 <p><strong>示例 1：</strong></p>

 <pre>
 <strong>输入：</strong>rectangles = [[5,8],[3,9],[5,12],[16,5]]
 <strong>输出：</strong>3
 <strong>解释：</strong>能从每个矩形中切出的最大正方形边长分别是 [5,3,5,5] 。
 最大正方形的边长为 5 ，可以由 3 个矩形切分得到。
 </pre>

 <p><strong>示例 2：</strong></p>

 <pre>
 <strong>输入：</strong>rectangles = [[2,3],[3,7],[4,3],[3,7]]
 <strong>输出：</strong>3
 </pre>

 <p> </p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>1 <= rectangles.length <= 1000</code></li>
 <li><code>rectangles[i].length == 2</code></li>
 <li><code>1 <= l<sub>i</sub>, w<sub>i</sub> <= 10<sup>9</sup></code></li>
 <li><code>l<sub>i</sub> != w<sub>i</sub></code></li>
 </ul>
 <div><div>Related Topics</div><div><li>数组</li></div></div><br><div><li>👍 46</li><li>👎 0</li></div>
 */

public class Leetcode1725 {
  public static void main(String[] args) {
    System.out.println(countGoodRectangles(new int[][] {{5, 8}, {3, 9}, {5, 12}, {16, 5}}));
    System.out.println(countGoodRectangles(new int[][] {{2, 3}, {3, 7}, {4, 3}, {3, 7}}));
    System.out.println(countGoodRectangles(new int[][]{{1,2}}));
  }

  /**
   * 映射到map中k为边长  v为可以围出的个数
   * @param rectangles
   * @return
   */
  public static int countGoodRectangles(int[][] rectangles) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int[] rectangle : rectangles) {
      int min = Math.min(rectangle[0], rectangle[1]);
      map.put(min, map.getOrDefault(min, 0) + 1);
    }

    return map.get(map.keySet().stream().max(Comparator.comparingInt(o -> o)));
  }

  // 我们在遍历过程中使用变量 max 维护最大边长，同时使用 ans 记录能够分割出最大边长 max 的矩形数量
  public int countGoodRectangles2(int[][] rectangles) {
    int max = 0, ans = 0;
    for (int[] r : rectangles) {
      int cur = Math.min(r[0], r[1]);
      if (cur == max) ans++;
      else if (cur > max) {
        max = cur; ans = 1;
      }
    }
    return ans;
  }

}

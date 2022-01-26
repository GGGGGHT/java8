package com.ggggght.learningjava8.leetcode;

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares obj = new DetectSquares();
 * obj.add(point);
 * int param_2 = obj.count(point);
 */

import java.util.HashMap;
import java.util.Map;

/**
 * <p>给你一个在 X-Y 平面上的点构成的数据流。设计一个满足下述要求的算法：</p>
 *
 * <ul>
 * <li><strong>添加</strong> 一个在数据流中的新点到某个数据结构中<strong>。</strong>可以添加 <strong>重复</strong> 的点，并会视作不同的点进行处理。</li>
 * <li>给你一个查询点，请你从数据结构中选出三个点，使这三个点和查询点一同构成一个 <strong>面积为正</strong> 的 <strong>轴对齐正方形</strong> ，<strong>统计</strong> 满足该要求的方案数目<strong>。</strong></li>
 * </ul>
 *
 * <p><strong>轴对齐正方形</strong> 是一个正方形，除四条边长度相同外，还满足每条边都与 x-轴 或 y-轴 平行或垂直。</p>
 *
 * <p>实现 <code>DetectSquares</code> 类：</p>
 *
 * <ul>
 * <li><code>DetectSquares()</code> 使用空数据结构初始化对象</li>
 * <li><code>void add(int[] point)</code> 向数据结构添加一个新的点 <code>point = [x, y]</code></li>
 * <li><code>int count(int[] point)</code> 统计按上述方式与点 <code>point = [x, y]</code> 共同构造 <strong>轴对齐正方形</strong> 的方案数。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/09/01/image.png" style="width: 869px; height: 504px;" />
 * <pre>
 * <strong>输入：</strong>
 * ["DetectSquares", "add", "add", "add", "count", "count", "add", "count"]
 * [[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
 * <strong>输出：</strong>
 * [null, null, null, null, 1, 0, null, 2]
 *
 * <strong>解释：</strong>
 * DetectSquares detectSquares = new DetectSquares();
 * detectSquares.add([3, 10]);
 * detectSquares.add([11, 2]);
 * detectSquares.add([3, 2]);
 * detectSquares.count([11, 10]); // 返回 1 。你可以选择：
 * //   - 第一个，第二个，和第三个点
 * detectSquares.count([14, 8]);  // 返回 0 。查询点无法与数据结构中的这些点构成正方形。
 * detectSquares.add([11, 2]);    // 允许添加重复的点。
 * detectSquares.count([11, 10]); // 返回 2 。你可以选择：
 * //   - 第一个，第二个，和第三个点
 * //   - 第一个，第三个，和第四个点
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>point.length == 2</code></li>
 * <li><code>0 &lt;= x, y &lt;= 1000</code></li>
 * <li>调用&nbsp;<code>add</code> 和 <code>count</code> 的 <strong>总次数</strong> 最多为 <code>5000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>设计</li><li>数组</li><li>哈希表</li><li>计数</li></div></div><br><div><li>👍 56</li><li>👎 0</li></div>
 */

public class Leetcode2013 {
  class DetectSquares {
    Map<Integer, Map<Integer, Integer>> row2Col = new HashMap<>();

    public void add(int[] point) {
      int x = point[0], y = point[1];
      Map<Integer, Integer> col2Cnt = row2Col.getOrDefault(x, new HashMap<>());
      col2Cnt.put(y, col2Cnt.getOrDefault(y, 0) + 1);
      row2Col.put(x, col2Cnt);
    }

    public int count(int[] point) {
      int x = point[0], y = point[1];
      int ans = 0;
      Map<Integer, Integer> col2Cnt = row2Col.getOrDefault(x, new HashMap<>());
      for (int ny : col2Cnt.keySet()) {
        if (ny == y) continue;
        int c1 = col2Cnt.get(ny);
        int len = y - ny;
        int[] nums = new int[]{x + len, x - len};
        for (int nx : nums) {
          Map<Integer, Integer> temp = row2Col.getOrDefault(nx, new HashMap<>());
          int c2 = temp.getOrDefault(y, 0), c3 = temp.getOrDefault(ny, 0);
          ans += c1 * c2 * c3;
        }
      }
      return ans;
    }
  }
}

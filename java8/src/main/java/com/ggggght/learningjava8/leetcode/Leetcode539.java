package com.ggggght.learningjava8.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * <p>给定一个 24 小时制（小时:分钟 <strong>"HH:MM"</strong>）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>timePoints = ["23:59","00:00"]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>timePoints = ["00:00","23:59","00:00"]
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>2 &lt;= timePoints.length &lt;= 2 * 10<sup>4</sup></code></li>
 * <li><code>timePoints[i]</code> 格式为 <strong>"HH:MM"</strong></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>数学</li><li>字符串</li><li>排序</li></div></div><br><div><li>👍 154</li><li>👎 0</li></div>
 */
public class Leetcode539 {
  public static void main(String[] args) {
    System.out.println(findMinDifference(List.of("12:12", "00:13")));
    // System.out.println(findMinDifference(List.of("00:00", "23:59", "00:00")));
    // System.out.println(findMinDifference(List.of("01:01", "02:01", "03:00", "03:01")));
    // System.out.println(findMinDifference(List.of("12:01", "00:10", "00:01", "03:01")));
  }

  public static int findMinDifference(List<String> timePoints) {
    int n = timePoints.size() * 2;
    int[] nums = new int[n];
    for (int i = 0, idx = 0; i < n / 2; i++, idx += 2) {
      String[] ss = timePoints.get(i).split(":");
      int h = Integer.parseInt(ss[0]), m = Integer.parseInt(ss[1]);
      nums[idx] = h * 60 + m;
      nums[idx + 1] = nums[idx] + 1440;
    }
    Arrays.sort(nums);
    int ans = nums[1] - nums[0];
    for (int i = 0; i < n - 1; i++) ans = Math.min(ans, nums[i + 1] - nums[i]);
    return ans;
  }
}

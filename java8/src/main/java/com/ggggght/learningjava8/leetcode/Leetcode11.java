package com.ggggght.learningjava8.leetcode;

import java.util.Arrays;

/**
 * <p>给你 <code>n</code> 个非负整数 <code>a<sub>1</sub>，a<sub>2，</sub>...，a</code><sub><code>n</code>，</sub>每个数代表坐标中的一个点&nbsp;<code>(i,&nbsp;a<sub>i</sub>)</code> 。在坐标内画 <code>n</code> 条垂直线，垂直线 <code>i</code>&nbsp;的两个端点分别为&nbsp;<code>(i,&nbsp;a<sub>i</sub>)</code> 和 <code>(i, 0)</code> 。找出其中的两条线，使得它们与&nbsp;<code>x</code>&nbsp;轴共同构成的容器可以容纳最多的水。</p>
 *
 * <p><strong>说明：</strong>你不能倾斜容器。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img alt="" src="https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs.com/aliyun-lc-upload/uploads/2018/07/25/question_11.jpg" style="height: 287px; width: 600px;" /></p>
 *
 * <pre>
 * <strong>输入：</strong>[1,8,6,2,5,4,8,3,7]
 * <strong>输出：</strong>49
 * <strong>解释：</strong>图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为&nbsp;49。</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>height = [1,1]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>height = [4,3,2,1,4]
 * <strong>输出：</strong>16
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>height = [1,2,1]
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>n == height.length</code></li>
 * <li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
 * <li><code>0 &lt;= height[i] &lt;= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>贪心</li><li>数组</li><li>双指针</li></div></div><br><div><li>👍 3018</li><li>👎 0</li></div>
 */

public class Leetcode11 {
  public static void main(String[] args) {
    Leetcode11 leetcode11 = new Leetcode11();
    System.out.println(leetcode11.maxArea2(new int[] {1,1}));
    System.out.println(leetcode11.maxArea2(new int[] {1,8,6,2,5,4,8,3,7}));
    System.out.println(leetcode11.maxArea2(new int[] {4,3,2,1,4}));
    System.out.println(leetcode11.maxArea2(new int[] {1,2,1}));
    System.out.println(leetcode11.maxArea2(new int[] {2,3,4,5,18,17,6}));
  }

  public int maxArea(int[] height) {
    int length = height.length;
    int max = -1;
    for (int i = 0; i < length; i++) {
      for (int j = i + 1; j < length; j++) {
        int tmp = Math.min(height[i], height[j]) * (j - i);
        max = Math.max(max, tmp);
      }
    }
    return max;
  }

  /**
   * 双指针
   * 双指针代表的是 可以作为容器边界的所有位置的范围。在一开始，双指针指向数组的左右边界，表示 数组中所有的位置都可以作为容器的边界，因为我们还没有进行过任何尝试。
   * 在这之后，我们每次将 对应的数字较小的那个指针 往 另一个指针 的方向移动一个位置，就表示我们认为 这个指针不可能再作为容器的边界了。
   *
   * @param height
   * @return
   */
  public int maxArea2(int[] height) {
    int length = height.length;
    int left = 0, right = length - 1;
    int max = -1;
    while (left != right) {
      max = Math.max(max, height[left] < height[right] ? height[left] * (right - left++)
          : height[right] * (right-- - left));
    }

    return max;
  }
}

package com.ggggght.learningjava8.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 <p>给你一个列表&nbsp;<code>nums</code>&nbsp;，里面每一个元素都是一个整数列表。请你依照下面各图的规则，按顺序返回&nbsp;<code>nums</code>&nbsp;中对角线上的整数。</p>

 <p>&nbsp;</p>

 <p><strong>示例 1：</strong></p>

 <p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/04/23/sample_1_1784.png" style="height: 143px; width: 158px;"></strong></p>

 <pre><strong>输入：</strong>nums = [[1,2,3],[4,5,6],[7,8,9]]
 <strong>输出：</strong>[1,4,2,7,5,3,8,6,9]
 </pre>

 <p><strong>示例 2：</strong></p>

 <p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/04/23/sample_2_1784.png" style="height: 177px; width: 230px;"></strong></p>

 <pre><strong>输入：</strong>nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
 <strong>输出：</strong>[1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
 </pre>

 <p><strong>示例 3：</strong></p>

 <pre><strong>输入：</strong>nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
 <strong>输出：</strong>[1,4,2,5,3,8,6,9,7,10,11]
 </pre>

 <p><strong>示例 4：</strong></p>

 <pre><strong>输入：</strong>nums = [[1,2,3,4,5,6]]
 <strong>输出：</strong>[1,2,3,4,5,6]
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>1 &lt;= nums.length &lt;= 10^5</code></li>
 <li><code>1 &lt;= nums[i].length &lt;=&nbsp;10^5</code></li>
 <li><code>1 &lt;= nums[i][j] &lt;= 10^9</code></li>
 <li><code>nums</code>&nbsp;中最多有&nbsp;<code>10^5</code>&nbsp;个数字。</li>
 </ul>
 <div><div>Related Topics</div><div><li>数组</li><li>排序</li><li>堆（优先队列）</li></div></div><br><div><li>👍 54</li><li>👎 0</li></div>
 */

public class Leetcode1424 {
  public static void main(String[] args) {
    System.out.println(Arrays.toString(
        findDiagonalOrder(List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9)))));
    System.out.println(Arrays.toString(findDiagonalOrder(
        List.of(List.of(1, 2, 3, 4, 5), List.of(6, 7), List.of(8), List.of(9, 10, 11),
            List.of(12, 13, 14, 15, 16)))));
    System.out.println(Arrays.toString(findDiagonalOrder(List.of(List.of(1, 2, 3, 4, 5, 6)))));

    System.out.println(Arrays.toString(
        findDiagonalOrder(List.of(List.of(14, 12, 19, 16, 9), List.of(13, 14, 15, 8, 11),
            List.of(11, 13, 1)))));
  }

  /**
   * 根据矩形的特点，设行的标号为i，列的标号为j。则对于每一条对角线而言，i + j的值是唯一的。<br/>
   * <img src="https://pic.leetcode-cn.com/b4425d9def38f3f74a99525dd2cbe2b5257531f307231294dede11eec729f6cf-%E8%81%9A%E5%90%88%E9%94%AE.PNG">
   * @param nums
   * @return
   */
  public static int[] findDiagonalOrder(List<List<Integer>> nums) {
    int len = 0;
    Map<Integer, List<Integer>> map = new LinkedHashMap<>();
    for (int i = 0; i < nums.size(); i++) {
      len += nums.get(i).size(); // 获取最后要返回的数组的长度，即元素个数
      for (int j = 0; j < nums.get(i).size(); j++) {
        if (map.containsKey(i + j)) {
          map.get(i + j).add(nums.get(i).get(j));
        } else {
          List<Integer> list = new ArrayList<>();
          list.add(nums.get(i).get(j));
          map.put(i + j, list);
        }
      }
    }
    int[] ans = new int[len];
    int index = 0;
    for (int key : map.keySet()) { // 遍历map
      List<Integer> list = map.get(key);
      for (int j = list.size() - 1; j >= 0; j--) { // 根据题目的输出要求确定生成数组中元素的顺序
        ans[index] = list.get(j);
        index++;
      }
    }
    return ans;
  }
}
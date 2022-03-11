package com.ggggght.learningjava8.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

/**
 * <p>给你一棵根节点为 <code>0</code> 的&nbsp;<strong>二叉树</strong>&nbsp;，它总共有 <code>n</code>&nbsp;个节点，节点编号为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;。同时给你一个下标从&nbsp;<strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>parents</code>&nbsp;表示这棵树，其中&nbsp;<code>parents[i]</code>&nbsp;是节点 <code>i</code>&nbsp;的父节点。由于节点 <code>0</code>&nbsp;是根，所以&nbsp;<code>parents[0] == -1</code>&nbsp;。</p>
 *
 * <p>一个子树的 <strong>大小</strong>&nbsp;为这个子树内节点的数目。每个节点都有一个与之关联的&nbsp;<strong>分数</strong>&nbsp;。求出某个节点分数的方法是，将这个节点和与它相连的边全部 <strong>删除</strong>&nbsp;，剩余部分是若干个 <strong>非空</strong>&nbsp;子树，这个节点的 <strong>分数</strong>&nbsp;为所有这些子树 <strong>大小的乘积</strong>&nbsp;。</p>
 *
 * <p>请你返回有 <strong>最高得分</strong>&nbsp;节点的 <strong>数目</strong>&nbsp;。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例&nbsp;1:</strong></p>
 *
 * <p><img alt="example-1" src="https://assets.leetcode.com/uploads/2021/10/03/example-1.png" style="width: 604px; height: 266px;"></p>
 *
 * <pre><b>输入：</b>parents = [-1,2,0,2,0]
 * <b>输出：</b>3
 * <strong>解释：</strong>
 * - 节点 0 的分数为：3 * 1 = 3
 * - 节点 1 的分数为：4 = 4
 * - 节点 2 的分数为：1 * 1 * 2 = 2
 * - 节点 3 的分数为：4 = 4
 * - 节点 4 的分数为：4 = 4
 * 最高得分为 4 ，有三个节点得分为 4 （分别是节点 1，3 和 4 ）。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <p><img alt="example-2" src="https://assets.leetcode.com/uploads/2021/10/03/example-2.png" style="width: 95px; height: 143px;"></p>
 *
 * <pre><b>输入：</b>parents = [-1,2,0]
 * <b>输出：</b>2
 * <strong>解释：</strong>
 * - 节点 0 的分数为：2 = 2
 * - 节点 1 的分数为：2 = 2
 * - 节点 2 的分数为：1 * 1 = 1
 * 最高分数为 2 ，有两个节点分数为 2 （分别为节点 0 和 1 ）。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>n == parents.length</code></li>
 * <li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
 * <li><code>parents[0] == -1</code></li>
 * <li>对于&nbsp;<code>i != 0</code>&nbsp;，有&nbsp;<code>0 &lt;= parents[i] &lt;= n - 1</code></li>
 * <li><code>parents</code>&nbsp;表示一棵二叉树。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>数组</li><li>二叉树</li></div></div><br><div><li>👍 36</li><li>👎 0</li></div>
 */

public class Leetcode2049 {

  public static void main(String[] args) {
    System.out.println(
        "countHighestScoreNodes(new int[]{-1,3,3,5,7,6,0,0}) = " + countHighestScoreNodes(
            new int[] {-1, 3, 3, 5, 7, 6, 0, 0}));

    System.out.println("countHighestScoreNodes(new int[] {-1,2,0}) = " + countHighestScoreNodes(
        new int[] {-1, 2, 0}));
    System.out.println(
        "countHighestScoreNodes(new int[] {-1, 2, 0, 2, 0}) = " + countHighestScoreNodes(
            new int[] {-1, 2, 0, 2, 0}));
    System.out.println(
        "countHighestScoreNodes(new int[] {-1,0,3,0,3,1}) = " + countHighestScoreNodes(
            new int[] {-1, 0, 3, 0, 3, 1}));
  }

  /**
   * 使用深度优先遍历统计以每个节点为父节点的节点个数 再依次遍历排除每个元素所能取得的最大的数量 TLE
   * @param parents
   * @return
   */
  public static int countHighestScoreNodes(int[] parents) {
    var map = new HashMap<Integer, List<Integer>>();
    var fathers = new HashMap<Integer, Integer>();
    var len = parents.length;
    var arr = new int[len];
    for (int i = 0; i < len; i++) {
      if (i != 0) {
        fathers.put(i, parents[i]);
        List<Integer> list = map.getOrDefault(parents[i], new ArrayList<Integer>());
        list.add(i);
        map.put(parents[i], list);
      }
      arr[i] = dfs(parents, i) + 1;
    }

    System.out.println(Arrays.toString(arr));
    TreeMap<Integer, Integer> tree = new TreeMap<>((o1, o2) -> o2 - o1);
    for (int i = 0; i < len; i++) {
      List<Integer> integers = map.get(i);
      // 没有叶子节点
      if (integers == null) {
        tree.put(len - 1, tree.getOrDefault(len - 1, 0) + 1);
      } else {
        // 有叶子节点
        // 找到其子节点
        if (i == 0 && integers.size() == 1) {
          tree.put(arr[0] - 1, tree.getOrDefault(arr[0] - 1, 0) + 1);
          continue;
        }
        if(i == 0) {
          int tmp =1 ;
          for (Integer integer : integers) {
           tmp *=   arr[integer];
          }
          tree.put(tmp,tree.getOrDefault(tmp,0) + 1);
          continue;
        }

        int tmp = arr[0] - arr[i];
        for (Integer integer : integers) {
          tmp *= arr[integer];
        }
        tree.put(tmp, tree.getOrDefault(tmp, 0) + 1);
      }
    }
    tree.forEach((k,v) -> System.out.println(k + "->" + v));
    return tree.firstEntry().getValue();
  }

  /**
   * 找以i为父节点的节点总个数
   *
   * @param arr
   * @param i
   * @return
   */
  public static int dfs(int[] arr, int i) {
    int ans = 0;
    for (int i1 = 0; i1 < arr.length; i1++) {
      if (arr[i1] == i) {
        ans += 1;
        ans += dfs(arr, i1);
      }
    }
    return ans;
  }
}

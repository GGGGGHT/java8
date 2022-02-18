package com.ggggght.learningjava8.leetcode;

/**
 <p>有一个无向的 <strong>星型</strong> 图，由 <code>n</code> 个编号从 <code>1</code> 到 <code>n</code> 的节点组成。星型图有一个 <strong>中心</strong> 节点，并且恰有 <code>n - 1</code> 条边将中心节点与其他每个节点连接起来。</p>

 <p>给你一个二维整数数组 <code>edges</code> ，其中 <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> 表示在节点 <code>u<sub>i</sub></code> 和 <code>v<sub>i</sub></code> 之间存在一条边。请你找出并返回 <code>edges</code> 所表示星型图的中心节点。</p>

 <p> </p>

 <p><strong>示例 1：</strong></p>
 <img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/03/14/star_graph.png" style="width: 331px; height: 321px;" />
 <pre>
 <strong>输入：</strong>edges = [[1,2],[2,3],[4,2]]
 <strong>输出：</strong>2
 <strong>解释：</strong>如上图所示，节点 2 与其他每个节点都相连，所以节点 2 是中心节点。
 </pre>

 <p><strong>示例 2：</strong></p>

 <pre>
 <strong>输入：</strong>edges = [[1,2],[5,1],[1,3],[1,4]]
 <strong>输出：</strong>1
 </pre>

 <p> </p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>3 <= n <= 10<sup>5</sup></code></li>
 <li><code>edges.length == n - 1</code></li>
 <li><code>edges[i].length == 2</code></li>
 <li><code>1 <= u<sub>i,</sub> v<sub>i</sub> <= n</code></li>
 <li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
 <li>题目数据给出的 <code>edges</code> 表示一个有效的星型图</li>
 </ul>
 <div><div>Related Topics</div><div><li>图</li></div></div><br><div><li>👍 28</li><li>👎 0</li></div>
 */
public class Leetcode1791 {
  public static void main(String[] args) {
    System.out.println(findCenter(new int[][] {{1, 2}, {2, 3}, {4, 2}}));
    System.out.println(findCenter1(new int[][] {{1, 2}, {2, 3}, {4, 2}}));
    System.out.println(findCenter(new int[][] {{1, 2}, {5, 1}, {1, 3}, {1, 4}}));
    System.out.println(findCenter1(new int[][] {{1, 2}, {5, 1}, {1, 3}, {1, 4}}));
  }

  /**
   * 映射到数组中
   *
   * @param edges
   * @return
   */
  public static int findCenter(int[][] edges) {
    int ans = 0;
    int n = edges.length + 1;
    int[] arr = new int[n + 1];
    for (int[] edge : edges) {
      arr[edge[0]]++;
      arr[edge[1]]++;
      var tmp = arr[edge[0]] > arr[edge[1]] ? edge[0] : edge[1];
      if (arr[tmp] > arr[ans]) {
        ans = tmp;
      }
    }

    return ans;
  }

  /**
   * 由于只有星型图的中心节点的度是 n - 1，其余每个节点的度都是 1，因此只有星型图在所有的边中都出现，其余每个节点分别只在一条边中出现。
   *
   * 根据星型图的上述性质可知，对于星型图中的任意两条边，星型图的中心节点一定同时在这两条边中出现，其余节点一定不会同时在这两条边中出现。因此，可以任选两条边，然后寻找这两条边的公共节点，该节点即为星型图的中心节点。
   *
   *
   * @param edges
   * @return
   */
  public static int findCenter1(int[][] edges) {
    return edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1] ? edges[0][0] : edges[0][1];
  }
}

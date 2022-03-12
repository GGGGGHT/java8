package com.ggggght.learningjava8.leetcode;

import java.util.ArrayList;
import java.util.List;
/**
 <p>给定一个 n&nbsp;叉树的根节点<meta charset="UTF-8" />&nbsp;<code>root</code>&nbsp;，返回 <em>其节点值的<strong> 后序遍历</strong></em> 。</p>

 <p>n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 <code>null</code> 分隔（请参见示例）。</p>

 <p>&nbsp;</p>

 <p><strong>示例 1：</strong></p>

 <p><img src="https://assets.leetcode.com/uploads/2018/10/12/narytreeexample.png" style="height: 193px; width: 300px;" /></p>

 <pre>
 <strong>输入：</strong>root = [1,null,3,2,4,null,5,6]
 <strong>输出：</strong>[5,6,3,2,4,1]
 </pre>

 <p><strong>示例 2：</strong></p>

 <p><img alt="" src="https://assets.leetcode.com/uploads/2019/11/08/sample_4_964.png" style="height: 269px; width: 296px;" /></p>

 <pre>
 <strong>输入：</strong>root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 <strong>输出：</strong>[2,6,14,11,7,3,12,8,4,13,9,10,5,1]
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示：</strong></p>

 <ul>
 <li>节点总数在范围 <code>[0, 10<sup>4</sup>]</code> 内</li>
 <li><code>0 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
 <li>n 叉树的高度小于或等于 <code>1000</code></li>
 </ul>

 <p>&nbsp;</p>

 <p><strong>进阶：</strong>递归法很简单，你可以使用迭代法完成此题吗?</p>
 <div><div>Related Topics</div><div><li>栈</li><li>树</li><li>深度优先搜索</li></div></div><br><div><li>👍 211</li><li>👎 0</li></div>
 */
public class Leetcode590 {
  public List<Integer> postorder(NodeTree root) {
    List<Integer> ans = new ArrayList<>();
    if (root == null) return ans;

   recursive(root,ans);
    return ans;
  }
  public void recursive(NodeTree node,List<Integer> list) {
    if (node.children != null) {
      for (NodeTree child : node.children) {
        recursive(child,list);
      }
    }

    list.add(node.val);
  }
}

class NodeTree {
  public int val;
  public List<NodeTree> children;

  public NodeTree() {
  }

  public NodeTree(int _val) {
    val = _val;
  }

  public NodeTree(int _val, List<NodeTree> _children) {
    val = _val;
    children = _children;
  }
}
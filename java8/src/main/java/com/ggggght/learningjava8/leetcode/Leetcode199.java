package com.ggggght.learningjava8.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
/**
 <p>给定一个二叉树的 <strong>根节点</strong> <code>root</code>，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。</p>

 <p> </p>

 <p><strong>示例 1:</strong></p>

 <p><img src="https://assets.leetcode.com/uploads/2021/02/14/tree.jpg" style="width: 270px; " /></p>

 <pre>
 <strong>输入:</strong> [1,2,3,null,5,null,4]
 <strong>输出:</strong> [1,3,4]
 </pre>

 <p><strong>示例 2:</strong></p>

 <pre>
 <strong>输入:</strong> [1,null,3]
 <strong>输出:</strong> [1,3]
 </pre>

 <p><strong>示例 3:</strong></p>

 <pre>
 <strong>输入:</strong> []
 <strong>输出:</strong> []
 </pre>

 <p> </p>

 <p><strong>提示:</strong></p>

 <ul>
 <li>二叉树的节点个数的范围是 <code>[0,100]</code></li>
 <li><meta charset="UTF-8" /><code>-100 <= Node.val <= 100</code> </li>
 </ul>
 <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 587</li><li>👎 0</li></div>
 */

public class Leetcode199 {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    // root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    // root.left.right = new TreeNode(5);
    // root.right.right = new TreeNode(4);

    System.out.println(rightSideView(root));
  }
  public static List<Integer> rightSideView(TreeNode root) {
    if(root == null) return Collections.emptyList();

    List<Integer> res = new ArrayList<>();
    // 记录当前层最后一个
    TreeNode curLast = root;
    // 记录下一层最后一个
    TreeNode nextLast = root.right != null ? root.right : root.left;
    // 记录头节点
    // res.add(root.val);
    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      TreeNode poll = queue.poll();

      if (poll.left != null) {
        queue.add(poll.left);
        nextLast = poll.left;
      }
      if (poll.right != null) {
        queue.add(poll.right);
        nextLast = poll.right;
      }

      if(poll == curLast) {
        res.add(curLast.val);
        curLast = nextLast;
      }
    }
    return res;
  }

}

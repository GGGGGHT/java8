package com.ggggght.learningjava8.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
/**
 <p>如果一棵二叉树满足下述几个条件，则可以称为 <strong>奇偶树</strong> ：</p>

 <ul>
 <li>二叉树根节点所在层下标为 <code>0</code> ，根的子节点所在层下标为 <code>1</code> ，根的孙节点所在层下标为 <code>2</code> ，依此类推。</li>
 <li><strong>偶数下标</strong> 层上的所有节点的值都是 <strong>奇</strong> 整数，从左到右按顺序 <strong>严格递增</strong></li>
 <li><strong>奇数下标</strong> 层上的所有节点的值都是 <strong>偶</strong> 整数，从左到右按顺序 <strong>严格递减</strong></li>
 </ul>

 <p>给你二叉树的根节点，如果二叉树为 <strong>奇偶树 </strong>，则返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

 <p> </p>

 <p><strong>示例 1：</strong></p>

 <p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/10/04/sample_1_1966.png" style="height: 229px; width: 362px;" /></strong></p>

 <pre>
 <strong>输入：</strong>root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
 <strong>输出：</strong>true
 <strong>解释：</strong>每一层的节点值分别是：
 0 层：[1]
 1 层：[10,4]
 2 层：[3,7,9]
 3 层：[12,8,6,2]
 由于 0 层和 2 层上的节点值都是奇数且严格递增，而 1 层和 3 层上的节点值都是偶数且严格递减，因此这是一棵奇偶树。
 </pre>

 <p><strong>示例 2：</strong></p>

 <p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/10/04/sample_2_1966.png" style="height: 167px; width: 363px;" /></strong></p>

 <pre>
 <strong>输入：</strong>root = [5,4,2,3,3,7]
 <strong>输出：</strong>false
 <strong>解释：</strong>每一层的节点值分别是：
 0 层：[5]
 1 层：[4,2]
 2 层：[3,3,7]
 2 层上的节点值不满足严格递增的条件，所以这不是一棵奇偶树。
 </pre>

 <p><strong>示例 3：</strong></p>

 <p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/10/04/sample_1_333_1966.png" style="height: 167px; width: 363px;" /></p>

 <pre>
 <strong>输入：</strong>root = [5,9,1,3,5,7]
 <strong>输出：</strong>false
 <strong>解释：</strong>1 层上的节点值应为偶数。
 </pre>

 <p><strong>示例 4：</strong></p>

 <pre>
 <strong>输入：</strong>root = [1]
 <strong>输出：</strong>true
 </pre>

 <p><strong>示例 5：</strong></p>

 <pre>
 <strong>输入：</strong>root = [11,8,6,1,3,9,11,30,20,18,16,12,10,4,2,17]
 <strong>输出：</strong>true
 </pre>

 <p> </p>

 <p><strong>提示：</strong></p>

 <ul>
 <li>树中节点数在范围 <code>[1, 10<sup>5</sup>]</code> 内</li>
 <li><code>1 <= Node.val <= 10<sup>6</sup></code></li>
 </ul>
 <div><div>Related Topics</div><div><li>树</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 54</li><li>👎 0</li></div>
 */

public class Leetcode1609 {
  public static void main(String[] args) {
    /*TreeNode head = new TreeNode(1);
    head.left = new TreeNode(10);
    head.right = new TreeNode(4);
    head.left.left = new TreeNode(3);
    head.left.left.left = new TreeNode(12);
    head.left.left.right = new TreeNode(8);
    head.right.left = new TreeNode(7);
    head.right.left.left= new TreeNode(6);
    head.right.right = new TreeNode(9);
    head.right.right.right = new TreeNode(2);*/
    // [1,10,4,3,null,7,9,12,8,6,null,null,2]
    // TreeNode head = new TreeNode(1);
    // head.left = new TreeNode(10);
    // head.right = new TreeNode(4);
    // head.left.left = new TreeNode(3);
    // head.left.left.left = new TreeNode(12);
    // head.left.left.right = new TreeNode(8);
    // head.right.left = new TreeNode(7);
    // head.right.right = new TreeNode(9);
    // head.right.left.left = new TreeNode(6);
    // head.right.right.right = new TreeNode(2);
    // System.out.println(isEvenOddTree(head));
    //
    // TreeNode h = new TreeNode(5);
    // h.left = new TreeNode(4);
    // h.right = new TreeNode(2);
    // h.left.left = new TreeNode(3);
    // h.left.right = new TreeNode(3);
    // h.right.left = new TreeNode(7);
    // System.out.println(isEvenOddTree(h));
    // [2,12,8,5,9,null,null,18,16]

    TreeNode a = new TreeNode(11);
    a.left = new TreeNode(18);
    a.right = new TreeNode(14);
    a.left.left = new TreeNode(3);
    a.left.right = new TreeNode(7);
    a.left.right.right = new TreeNode(18);
    a.left.right.right.right = new TreeNode(6);
    System.out.println(isEvenOddTree2(a));
  }

  public static boolean isEvenOddTree(TreeNode root) {
    TreeNode curLast = root;
    TreeNode nextLast = root.right != null ? root.right : root.left;

    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.add(root);
    // 1代表奇数层 奇数层值要递减并且都为偶数 0代表偶数层 偶数层都为奇数并递增
    int level = 0;
    //
    int startNum = 0;
    // 用来标识是否是每一层的第一个节点
    boolean isFirst = false;
    while (!queue.isEmpty()) {
      TreeNode tmp = queue.poll();
      if (tmp.left != null) {
        queue.add(tmp.left);
        nextLast = tmp.left;
      }

      if (tmp.right != null) {
        queue.add(tmp.right);
        nextLast = tmp.right;
      }

      if (tmp == curLast) {
        curLast = nextLast;
        // 偶数层
        if ((level & 1) == 0) {
          if ((tmp.val & 1) != 1 || (!isFirst && tmp.val <= startNum)) {
            return false;
          }
        } else {
          // 奇数层
          if ((tmp.val & 1) != 0 || (!isFirst && tmp.val >= startNum)) {
            return false;
          }
        }

        isFirst = true;
        if (!queue.isEmpty()) {

          startNum = queue.peek().val;
          level++;
        }
      } else {
        // 偶数层
        if ((level & 1) == 0) {
          if ((tmp.val & 1) != 1 || (!isFirst && tmp.val <= startNum)) {
            return false;
          }
        } else {
          // 奇数层
          if ((tmp.val & 1) != 0 || (!isFirst && tmp.val >= startNum)) {
            return false;
          }
        }

        startNum = tmp.val;
        isFirst = false;
      }
    }

    System.out.println("current level = " + level);
    return true;
  }

  /**
   * 三叶姐解法 使用队列层序遍历 使用size记录该层的有几个元素
   * @param root
   * @return
   */
  public static boolean isEvenOddTree2(TreeNode root) {
    Deque<TreeNode> d = new ArrayDeque<>();
    boolean flag = true;
    d.addLast(root);
    while (!d.isEmpty()) {
      int size = d.size(), prev = flag ? 0 : 0x3f3f3f3f;
       while (size-- > 0) {
        TreeNode node = d.pollFirst();
        int cur = node.val;
        if (flag && (cur % 2 == 0 || cur <= prev)) return false;
        if (!flag && (cur % 2 != 0 || cur >= prev)) return false;
        prev = cur;
        if (node.left != null) d.addLast(node.left);
        if (node.right != null) d.addLast(node.right);
      }
      flag = !flag;
    }
    return true;
  }

}

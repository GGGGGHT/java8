package com.ggggght.learningjava8.leetcode;
/**
 <p>你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。</p>

 <p>空节点则用一对空括号 &quot;()&quot; 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。</p>

 <p><strong>示例 1:</strong></p>

 <pre>
 <strong>输入:</strong> 二叉树: [1,2,3,4]
 1
 /   \
 2     3
 /
 4

 <strong>输出:</strong> &quot;1(2(4))(3)&quot;

 <strong>解释:</strong> 原本将是&ldquo;1(2(4)())(3())&rdquo;，
 在你省略所有不必要的空括号对之后，
 它将是&ldquo;1(2(4))(3)&rdquo;。
 </pre>

 <p><strong>示例 2:</strong></p>

 <pre>
 <strong>输入:</strong> 二叉树: [1,2,3,null,4]
 1
 /   \
 2     3
 \
 4

 <strong>输出:</strong> &quot;1(2()(4))(3)&quot;

 <strong>解释:</strong> 和第一个示例相似，
 除了我们不能省略第一个对括号来中断输入和输出之间的一对一映射关系。
 </pre>
 <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>字符串</li><li>二叉树</li></div></div><br><div><li>👍 275</li><li>👎 0</li></div>
 */
public class Leetcode606 {
  public static void main(String[] args) {
    Leetcode606 leetcode606 = new Leetcode606();
    TreeNode head = new TreeNode(1);
    // head.left = new TreeNode(2);
    // head.right = new TreeNode(3);
    // head.left.left = new TreeNode(4);
    String s = leetcode606.tree2str(head);
    System.out.println(s);
  }

  public String tree2str(TreeNode root) {
    StringBuilder builder = new StringBuilder();

    if (root != null) recursive(root, builder);
    return builder.toString();
  }

  public void recursive(TreeNode root, StringBuilder builder) {
    if (root == null) {
      return;
    }

    builder.append(root.val);
    if (root.left != null) {
      builder.append("(");
      recursive(root.left, builder);
      builder.append(")");
    }

    if (root.left == null && root.right != null) {
      builder.append("()");
    }
    if (root.right != null) {
      builder.append("(");
      recursive(root.right, builder);
      builder.append(")");
    }
  }
}

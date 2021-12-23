package com.ggggght.learningjava8.leetcode;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Leetcode124 {
  public static void main(String[] args) {

    TreeNode head = new TreeNode(10);
    head.left = new TreeNode(9);
    head.right = new TreeNode(20);
    head.right.left = new TreeNode(15);
    head.right.right = new TreeNode(7);
    ArrayList<Integer> integers = new ArrayList<>();
    toList(head, integers);
    System.out.println(integers);
    int size = integers.size();
    int i = size - 1;
    while (integers.get(i) == null) {
      i--;
    }

    int left, right, father;
    // 奇数 为left
    int max = 0;
    if ((i & 1) != 0) {
      left = integers.get(i);
      father = integers.get((i - 1) / 2);
      right = integers.get(i + 1);
    } else {
      right = integers.get(i);
      father = integers.get((i - 2) / 2);
      left = integers.get(i - 1);
    }

    System.out.println("left = " + left);
    System.out.println("right = " + right);
    System.out.println("father = " + father);
    max = left + right + father;
    // 保存以指定节点为头节点的子树的和
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 0);
    while (--i > 0) {
      if (integers.get(i) == null) {
        continue;
      }

      if ((i & 1) != 0) {
        left = integers.get(i) == null ? 0 : integers.get(i);
        father = integers.get((i - 1) / 2);
        right = integers.get(i + 1) == null ? 0 : integers.get(i + 1);
      } else {
        right = integers.get(i) == null ? 0 : integers.get(i);
        ;
        father = integers.get((i - 2) / 2);
        left = integers.get(i - 1) == null ? 0 : integers.get(i - 1);
      }

      map.put(father, right + father + left);
      Integer rightSum = map.getOrDefault(right, integers.get(right));
      Integer leftSum = map.getOrDefault(left, integers.get(left));
      // 如果没找到则是叶子节点 他的值为自己的val
      // 向上扩展
      int curLeft = integers.get(2 * i + 1) == null ? 0 : integers.get(2 * i + 1);
      int curRight = integers.get(2 * i + 2) == null ? 0 : integers.get(2 * i + 2);
      int needRemove = Math.min(curLeft, curRight);
      if (father > 0 && father > needRemove) {
        max = max - needRemove + father;
      }
    }
  }

  //   0   1  2    3     4     5   6   7     8   9     10
  // [-10, 9, 20, null, null, 15, 7, null, null, null, null]
  // 4+1 2n+2

  private static void toList(TreeNode head, List<Integer> list) {
    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.add(head);
    list.add(head.val);
    while (!queue.isEmpty()) {
      TreeNode tmp = queue.poll();

      if (tmp.left != null) {
        queue.add(tmp.left);
        list.add(tmp.left.val);
      } else {
        list.add(null);
      }

      if (tmp.right != null) {
        queue.add(tmp.right);
        list.add(tmp.right.val);
      } else {
        list.add(null);
      }
    }
  }
}

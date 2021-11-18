package com.ggggght.learningjava8.binarysorttree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 75685
 */
@SuppressWarnings("all")
public class BinarySortTreeDemo {

	public static void main(String[] args) {
		int[] arr = { 7, 3, 10, 12, 5, 1, 2, 9 };
		final BinarySortTree binarySortTree = new BinarySortTree();
		for (int i : arr) {
			binarySortTree.add(new Node(i, null, null));
		}

		binarySortTree.infixOrder();
		System.out.println();
		binarySortTree.preOrder();
		/*
		 * System.out.println(); binarySortTree.remove(2); binarySortTree.remove(5);
		 * binarySortTree.remove(9); binarySortTree.remove(12); binarySortTree.remove(7);
		 * binarySortTree.remove(3); binarySortTree.remove(1); binarySortTree.remove(10);
		 * binarySortTree.infixOrder();
		 */
	}

}

class BinarySortTree {

	private Node root;

	public void add(Node node) {
		if (null == root) {
			root = node;
			return;
		}

		root.add(node);
	}

	public Node remove(int val) {
		if (null == root) {
			return null;
		}

		return root.remove(val);
	}

	public void preOrder() {
		if (null == root) {
			return;
		}
		root.preOrder();
	}

	public void infixOrder() {
		if (null == root) {
			System.out.println("null root");
			return;
		}

		root.infixOrder();
	}

}

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
class Node {

	int val;

	Node left;

	Node right;

	/**
	 * 添加结点的方式 递归的形式添加结点，注意需要满足二叉排序树的要求
	 * @param node
	 */
	public void add(Node node) {
		if (null == node) {
			return;
		}

		final int nodeVal = node.getVal();
		// 需要往右插入
		if (nodeVal > this.val) {
			// 如果当前的右子结点为空
			if (null == this.getRight()) {
				this.setRight(node);
			}
			else {
				this.right.add(node);
			}
		}
		else { // 往左插入
			if (null == this.getLeft()) {
				this.setLeft(node);
			}
			else {
				this.left.add(node);
			}
		}
	}

	public void infixOrder() {
		if (null != this.getLeft()) {
			this.left.infixOrder();
		}

		System.out.print(val + "\t");

		if (null != this.getRight()) {
			this.getRight().infixOrder();
		}
	}

	@Override
	public String toString() {
		return "Node{" + "val=" + val + '}';
	}

	public Node remove(int val) {
		return remove(val, this);
	}

	/**
	 * @param node 需要移除的节点
	 * @param par 当前节点的父级节点
	 * @return
	 */
	private Node remove(int val, Node par) {
		if (this.val > val) {
			if (null == this.getLeft())
				return null;
			return this.left.remove(val, this);
		}
		else if (this.val < val) {
			if (null == this.getRight())
				return null;
			return this.right.remove(val, this);
		}
		else { // this.val == removeVal
				// 当前节点已经是根结点
			if (this == par && null == this.getRight() && null == this.getRight()) {
				// root = null;
				return null;
			}

			// 当前的节点是叶子节点
			if (null == this.getRight() && null == this.getLeft()) {
				if (par.getVal() > this.val) {
					par.setLeft(null);
				}
				else {
					par.setRight(null);
				}
				return this;
			}

			// 左子结点为空 右子结点不为空 或 左子结点不为空 右子结点为空
			if ((null == this.getLeft() && null != this.getRight())
					|| (null == this.getRight() && null != this.getLeft())) {
				// 先判断需要将节点拼到左还是右
				if (this.getVal() > par.val) {
					// 判断哪边是不为空的
					if (null == this.getLeft()) {
						par.setRight(this.getRight());
					}
					else {
						par.setRight(this.getLeft());
					}
					return this;
				}
				else {
					// 判断哪边是不为空的
					if (null == this.getLeft()) {
						par.setLeft(this.getRight());
					}
					else {
						par.setLeft(this.getLeft());
					}
					return this;
				}
			}

			// 左右都不为空
			if (null != this.getLeft() && null != this.getRight()) {
				// 需要找到左子树中最大的或右子树中最小的节点
				Node tmp = this.getRight();
				// 去找右子树的最小结点 循环过后 tmp中保存的即是当前右子树中最小的结点
				while (null != tmp.getLeft()) {
					tmp = tmp.getLeft();
				}
				remove(tmp.getVal());
				this.setVal(tmp.getVal());
				return this;
			}
		}
		return null;
	}

	public void preOrder() {
		System.out.print(val + "\t");

		if (null != this.getLeft()) {
			this.getLeft().preOrder();
		}

		if (null != this.getRight()) {
			this.getRight().preOrder();
		}
	}

}
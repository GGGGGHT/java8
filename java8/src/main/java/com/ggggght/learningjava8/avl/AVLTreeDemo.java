package com.ggggght.learningjava8.avl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("all")
public class AVLTreeDemo {
	public static void main(String[] args) {
		//int[] arr = {4, 3, 6, 5, 7, 8};
		//int[] arr = {10, 11, 8, 9, 7, 6};
		int[] arr = {10, 11, 7, 6, 8, 9};
		AVLTree avlTree = new AVLTree();
		for (int i : arr) {
			avlTree.add(new Node(i, null, null));
		}
		
		avlTree.infixOrder();
		System.out.println();
		avlTree.preOrder();
		System.out.println("未做平衡处理前树的高度：" + avlTree.getRoot().height());
		System.out.println("未做平衡处理前左子树高度：" + avlTree.getRoot().leftHeight());
		System.out.println("未做平衡处理前右子树高度：" + avlTree.getRoot().rightHeight());
		System.out.println(avlTree.getRoot());
	}
}

class AVLTree {
	private Node root;
	
	public Node search(int val) {
		if (null == root) {
			return null;
		}
		
		return root.search(val);
	}
	
	public Node searchParent(int val) {
		if (null == root) {
			return null;
		}
		
		return root.searchParent(val);
	}
	
	public void add(Node node) {
		if (null == root) {
			root = node;
			return;
		}
		
		root.add(node);
	}
	
	public void infixOrder() {
		if (null == root) {
			return;
		}
		
		root.infixOrder();
	}
	
	public void preOrder() {
		if (null == root) {
			return;
		}
		
		root.preOrder();
	}
	
	public Node getRoot() {
		return root;
	}
	
	public void setRoot(Node root) {
		this.root = root;
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
	 * 添加结点的方式
	 * 递归的形式添加结点，注意需要满足二叉排序树的要求
	 * 添加与删除时需要判断是否需要进行平衡
	 *
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
			} else {
				this.right.add(node);
			}
		} else { // 往左插入
			if (null == this.getLeft()) {
				this.setLeft(node);
			} else {
				this.left.add(node);
			}
		}
		
		// 右子树高度 － 左子树高度 > 1 需要进行左旋转
		if (rightHeight() - leftHeight() > 1) {
			if (null != right && right.leftHeight() > right.rightHeight()) {
				rightRotate();
			}
			
			leftRotate();
			
			return;
		}
		
		// 左子树高度 － 右子树高度 > 1 需要进行右旋转
		if (leftHeight() - rightHeight() > 1) {
			if (null != left && left.rightHeight() > left.leftHeight()) {
				left.leftRotate();
			}
			rightRotate();
		}
	}
	
	public void preOrder() {
		System.out.print(val + "\t");
		
		if (null != this.getLeft()) {
			this.left.infixOrder();
		}
		
		if (null != this.getRight()) {
			this.getRight().infixOrder();
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
	
	public Node remove(int val) {
		return remove(val, this);
	}
	
	/**
	 * @param node 需要移除的节点
	 * @param par  当前节点的父级节点
	 * @return
	 */
	private Node remove(int val, Node par) {
		if (this.val > val) {
			if (null == this.getLeft()) return null;
			return this.left.remove(val, this);
		} else if (this.val < val) {
			if (null == this.getRight()) return null;
			return this.right.remove(val, this);
		} else { // this.val == removeVal
			// 当前节点已经是根结点
			if (this == par && null == this.getRight() && null == this.getRight()) {
				// root = null;
				return null;
			}
			
			// 当前的节点是叶子节点
			if (null == this.getRight() && null == this.getLeft()) {
				if (par.getVal() > this.val) {
					par.setLeft(null);
				} else {
					par.setRight(null);
				}
				return this;
			}
			
			// 左子结点为空 右子结点不为空 或 左子结点不为空 右子结点为空
			if ((null == this.getLeft() && null != this.getRight()) ||
					(null == this.getRight() && null != this.getLeft())) {
				// 先判断需要将节点拼到左还是右
				if (this.getVal() > par.val) {
					// 判断哪边是不为空的
					if (null == this.getLeft()) {
						par.setRight(this.getRight());
					} else {
						par.setRight(this.getLeft());
					}
					return this;
				} else {
					// 判断哪边是不为空的
					if (null == this.getLeft()) {
						par.setLeft(this.getRight());
					} else {
						par.setLeft(this.getLeft());
					}
					return this;
				}
			}
			
			// 左右都不为空
			if (null != this.getLeft() && null != this.getRight()) {
				// 需要找到左子树中最大的或右子树中最小的节点
				Node tmp = this.getRight();
				//去找右子树的最小结点 循环过后 tmp中保存的即是当前右子树中最小的结点
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
	
	public int height() {
		return Math.max(null == left ? 0 : left.height(), null == right ? 0 : right.height()) + 1;
	}
	
	public int leftHeight() {
		if (null == left) {
			return 0;
		}
		
		return left.height();
	}
	
	public int rightHeight() {
		if (null == right) {
			return 0;
		}
		
		return right.height();
	}
	
	public Node search(int val) {
		if (val == this.val) {
			return this;
		}
		
		if (val < this.val) {
			if (null == this.left) return null;
			return this.left.search(val);
		}
		
		if (val > this.val) {
			if (null == this.right) return null;
			return this.right.search(val);
		}
		
		return null;
	}
	
	public Node searchParent(int val) {
		if ((null != this.left && val == this.left.val) || (null != this.right && val == this.right.val)) {
			return this;
		} else {
			if (val < this.val && this.left != null) {
				return this.left.searchParent(val);
			} else if (val >= this.val && null != this.right) {
				return this.right.searchParent(val);
			} else {
				return null;
			}
		}
	}
	
	@Override
	public String toString() {
		return "Node{" +
				"val=" + val +
				'}';
	}
	
	/**
	 * 左旋转
	 */
	private void leftRotate() {
		Node newNode = new Node(val, null, null);
		newNode.left = left;
		newNode.right = right.left;
		val = right.val;
		right = right.right;
		left = newNode;
	}
	
	/**
	 * 右旋转
	 */
	private void rightRotate() {
		Node newNode = new Node(val, null, null);
		newNode.left = left.right;
		newNode.right = right;
		val = left.val;
		right = newNode;
		left = left.left;
	}
}
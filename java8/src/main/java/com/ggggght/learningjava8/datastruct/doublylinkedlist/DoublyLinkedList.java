package com.ggggght.learningjava8.datastruct.doublylinkedlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ght
 * @Desc 双向链表
 * @date 2020-01-04 10:43 AM
 */

@SuppressWarnings("all")
public class DoublyLinkedList {

	/* 初始化头结点 */
	private static HeroNode2 head = new HeroNode2(0, "", "");

	/**
	 * 判断当前链表是否为空
	 * @return
	 */
	public static boolean isEmpty() {
		return head.next == null;
	}

	/**
	 * 用来遍历链表
	 */
	public static void show() {
		if (isEmpty()) {
			return;
		}

		HeroNode2 tmp = head;
		while (hasNext(tmp)) {
			System.out.println(tmp + "--->");
			tmp = tmp.next;
		}
		System.out.println(tmp);
	}

	/**
	 * 判断当前节点是否存在下一个节点
	 * @param node
	 * @return
	 */
	private static boolean hasNext(HeroNode2 node) {
		return !(null == node || node.next == null);
	}

	/**
	 * 添加到双向链表的尾部
	 */
	public void addTail(HeroNode2 node) {
		HeroNode2 tmp = head;
		while (null != tmp.next) {
			tmp = tmp.next;
		}

		// 形成一个双向链表
		tmp.next = node;
		node.pre = tmp;
	}

	/**
	 * 将节点添加到链表的指定位置 即根据编号来添加
	 * @param node
	 */
	public static void addTo(HeroNode2 node) {
		// 如果为空则直接插入
		while (isEmpty()) {
			head.next = node;
			node.pre = head;
			return;
		}

		HeroNode2 tmp = head.next;
		while (null != tmp.next) {
			// 前一个节点小于node.no 后一个节点大于tmp.no
			// 就将node插入tmp.next
			if (tmp.no > node.no && tmp.pre.no < node.no) {
				tmp.pre.next = node;
				tmp.pre = node;
				node.pre = tmp.pre;
				node.next = tmp;
				return;
			}
			else if (tmp.no == node.no) {
				System.out.println("已存在该节点,不做修改");
				return;
			}
			tmp = tmp.next;
		}

		if (tmp.no > node.no) {
			node.pre = tmp.pre;
			node.next = tmp;
			tmp.pre.next = node;
			tmp.pre = node;
		}
		else {
			tmp.next = node;
			node.pre = tmp;
		}
		return;
	}

	/**
	 * 根据no来修改节点的信息
	 * @param args
	 */
	public void update(HeroNode2 node) {
		if (isEmpty()) {
			throw new RuntimeException("没有节点,无法修改");
		}

		HeroNode2 tmp = head;
		while (true) {
			if (tmp == null) {
				break;
			}

			if (tmp.next.no == node.no) {
				if (node.next != null) {
					node.next = tmp.next.next;
					node.next.pre = node;
				}
				node.pre = tmp;
				tmp.next = node;
				return;
			}
			tmp = tmp.next;
		}
		throw new RuntimeException("没有节点...,无法修改");
	}

	/**
	 * 从双向链表中删除一个节点 实现自我删除
	 * @param args
	 */
	public void remove(HeroNode2 node) {
		if (isEmpty()) {
			throw new RuntimeException("空链表 无法删除");
		}

		HeroNode2 tmp = head.next;
		boolean flag = false;

		while (true) {
			if (tmp == null) {
				break;
			}

			if (tmp == node) {
				flag = true;
				break;
			}
			tmp = tmp.next;
		}

		if (flag) {
			tmp.pre.next = tmp.next;
			// 如何删除的不是最后一个节点这样做是可以的
			if (tmp.next != null) {
				tmp.next.pre = tmp.pre;
			}
			node = null;
		}
	}

	public static void main(String[] args) {
		HeroNode2 heroNode1 = new HeroNode2(6, "zs", "");
		HeroNode2 heroNode2 = new HeroNode2(2, "ls", "");
		HeroNode2 heroNode3 = new HeroNode2(7, "ww", "");
		HeroNode2 heroNode4 = new HeroNode2(3, "zl", "");
		HeroNode2 heroNode5 = new HeroNode2(5, "tq", "");
		DoublyLinkedList list = new DoublyLinkedList();
		addTo(heroNode1);
		addTo(heroNode2);
		addTo(heroNode3);
		addTo(heroNode4);
		addTo(heroNode5);
		System.out.println("---添加完成后---");
		show();
		list.remove(heroNode3);
		System.out.println("---删除完成后---");
		show();
		list.update(new HeroNode2(5, "老五", "钻石王老五"));
		System.out.println("---更新完成后---");
		show();
	}

}

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
class HeroNode2 {

	public int no;

	public String name;

	public String nickName;

	/* 指向下一个结点的指针默认为NULL */
	public HeroNode2 next;

	/* 指向上一个结点的指针默认为NULL */
	public HeroNode2 pre;

	/* 用来构造一个头结点 */
	public HeroNode2(int no, String name, String nickName) {
		this.no = no;
		this.name = name;
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return "HeroNode2{" + "no=" + no + ", name='" + name + '\'' + ", nickName='" + nickName + '\'' + '}';
	}

}

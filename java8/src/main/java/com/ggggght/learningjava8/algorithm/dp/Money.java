package com.ggggght.learningjava8.algorithm.dp;

import cn.hutool.crypto.digest.mac.MacEngine;
import org.springframework.cglib.core.Block;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 给定数组arr arr中所有的值都为正数且不重复，每个值代表一种面值的货币，每种货币可以使用任意张， 再给定一个整数aim，代表要找的钱数，求组成aim的最少货币数。
 */
public class Money {

	static Thread t1 = null, t2 = null;

	public static void main(String[] args) {

		// t1 = new Thread(() -> {
		// for (int i = 0; i < 26; i++) {
		// System.out.print(i);
		// LockSupport.unpark(t2);
		// LockSupport.park();
		// }
		// });
		//
		// t2 = new Thread(() -> {
		// for (int i = 0; i < 26; i++) {
		// LockSupport.park();
		// System.out.print((char)((int)'a' + i));
		// LockSupport.unpark(t1);
		// }
		// });
		//
		// t1.start();
		// t2.start();
		new Money().test();
	}

	void test() {
		BlockingQueue<Integer> i = new ArrayBlockingQueue<>(1);
		BlockingQueue<Character> c = new ArrayBlockingQueue<>(1);

		new Thread(() -> {
			for (int h = 0; h < 26; h++) {
				try {
					System.out.print(i.take());
					c.add((char) ((int) 'a' + h));
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		new Thread(() -> {
			for (int h = 0; h < 26; h++) {
				try {
					System.out.print(c.take());
					i.add(h);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}).start();
	}

	public int minCoins(int[] arr, int aim) {
		if (arr == null || arr.length <= 0 || aim < 0)
			return 0;

		return process(arr, 0, aim);
	}

	/**
	 * @param arr 货币数组
	 * @param rest
	 * @return
	 */
	public int process(int[] arr, int i, int rest) {
		if (i == arr.length) {
			return rest == 0 ? 0 : -1;
		}

		int res = -1;
		for (int k = 0; k * arr[i] <= rest; k++) {
			System.out.printf("第%d次循环，");
			int next = process(arr, i + 1, rest - arr[i] * k);
			if (next != -1) {
				res = res == -1 ? next + k : Math.min(res, next + k);
			}

		}

		return res;
	}

}

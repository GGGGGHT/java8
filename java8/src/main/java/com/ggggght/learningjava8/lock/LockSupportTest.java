package com.ggggght.learningjava8.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wildfly.common.lock.Locks;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@SuppressWarnings("all")
public class LockSupportTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(LockSupportTest.class);
	
	public static void main(String[] args) {
		// useParkBlockThread();
		// useUnparkNotifyThread();
		// doubleParkThread();
		notifyAnother();
	}
	
	
	// 使用park来阻塞线程
	public static void useParkBlockThread() {
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println(i);
				
				if (i == 5) {
					LockSupport.park();
				}
				
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
	}
	
	// 使用unpark来唤醒被阻塞的线程  unpark可以先于park被调用
	public static void useUnparkNotifyThread() {
		Thread thread = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println(i);
				
				if (i == 5) {
					LockSupport.park();
				}
				
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		thread.start();
		LockSupport.unpark(thread);
	}
	
	//  如果一个线程处于等待状态,连续调用两次park()方法,就会使该线程永远无法被唤醒
	public static void doubleParkThread() {
		Thread thread = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println(i);
				
				if (i == 5) {
					LockSupport.park();
				}
				
				if (i == 8) {
					LockSupport.park();
				}
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		thread.start();
		LockSupport.unpark(thread);
	}
	
	// 启动两个线程 一个线程向容器中加元素 当该容器中的元素为5时 通知另一个线程
	public static void notifyAnother() {
		List list = new ArrayList(10);
		
		Thread thread2 = new Thread(() -> {
			LockSupport.park();
	
			System.out.println("list中元素已到达5");
			System.out.println(list);
		});
		
		Thread thread1 = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				list.add(new Object());
				
				if (list.size() == 5) {
					LockSupport.unpark(thread2);
				}
				
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		thread1.start();
		thread2.start();
	}
}

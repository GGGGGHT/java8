package com.ggggght.learningjava8.ThreadPool;

/**
 * @author ght
 * @Desc 学习synchronized关键字的使用
 * @date 2020-04-14 11:23 synchronized的用法 1. 普通同步方法,锁的是当前实例对象 2. 静态同步方法,锁的是当前类的class对象 3.
 * 同步方法块,锁的是括号里的对象
 *
 * 对于同步方法,JVM采用ACC_SYNCHRONIZED标志符来实现同步 对于同步代码块,采用monitorenter和monitorexit来实现同步
 *
 * 总结: 并发编程中通过同步互斥访问临界资源来解决线程安全问题，Java中常用synchronized标记同步块达到加锁的目的。
 * synchronized用法有两种，修饰方法和修饰同步代码块。
 * synchronized的实现原理：每一个Java对象都会关联一个Monitor，通过Monitor对线程的操作实现synchronized对象锁。
 * 并发编程中synchronized可以保证原子性、可见性、有序性。
 *
 * ---------------------------------------------------------------------------------------------------
 * synchronzied的优化: 问题: 为什么需要对synchronzied优化? 答: 因为java的线程是映射到操作系统的线程之上的,所以对一个线程的挂起或者是唤醒
 * 都需要从用户态映射到内核态.状态转换需要花费很多时间. 所以当频繁的通过sync去实现同步会严重的影响到程序的执行效率.因此jdk对sync进行了种种优化
 *
 * 自旋锁和适应自旋锁: 多数情况下.线程持有锁的时间都不会很长.为了这一段很短的时间频繁的阻塞或唤醒线程是非常不值得的,所以引入了自旋锁.
 *
 * 自旋锁: 当锁被占用时，当前想要获取锁的线程不会被立即挂起，而是做几个空循环，看持有锁的线程是否会很快释放锁。
 * 在经过若干次循环后，如果得到锁，就顺利进入临界区；如果还不能获得锁，那就会将线程在操作系统层面挂起。
 *
 * 自旋锁和阻塞的区别: 主要区别在于:是否放弃CPU的执行时间.
 * 阻塞放弃了CPU时间，进入了等待区，等待被唤醒。响应慢。自旋锁一直占用CPU时间，时刻检查共享资源是否可以被访问，所以响应速度更快。
 * 如果持有锁的线程很快就释放了锁,那么自旋的效率就非常高.但如果持有锁的线程占用锁的时间较长,等待锁的线程自旋一定次数还是拿不到锁而被阻塞,
 * 那么自旋就白的浪费了CPU的资源.所以自旋的次数直接决定了自旋锁的性能.JDK自旋的默认次数为10次,可以通过参数 -XX:PreBlockSpin来进行调整
 *
 * 自适应自旋锁: 自适应自旋锁指的是自旋的次数不再是固定的.它是由前一次在同一个锁上的自旋时间及锁的拥有者的状态来决定.线程如果自旋成功了.那么下次自旋的
 * 次数会更加多.因为虚拟机认为既然上次成功了.那么此次自旋也很有可能会再次成功.那么它就会允许自旋等待持续的次数更多.
 * 如果对于某个锁,很少有自旋能够成功的.那么在以后要获取这个锁的时候自旋的次数会减少甚至活省略掉自旋过程,以免浪费处理器资源.
 *
 * 锁消除: 如果JVM检测到某段代码不可能存在共享竞争,JVM会对这段代码的同步锁进行锁消除.
 *
 * ----------------------------------------------------------------------------------------------------------------
 * synchronzied锁一共有四种状态: - 无锁 - 偏向锁 - 轻量级锁 - 重量级锁
 *
 * 偏向锁: 多数情况下锁不仅不存在多线程竞争,而且总是由同一个线程多次获取,所以引入偏向锁让线程获得锁的代价更低.偏向锁认为环境中不存在
 * 竞争情况，锁只被一个线程持有，一旦有不同的线程获取或竞争锁对象，偏向锁就升级为轻量级锁。偏向锁在无多线程竞争的情况下可以减少不必须 要的轻量级锁执行路径。
 *
 * 轻量级锁: 在大多数情况下同步块并不会出现竞争情况，大部分情况是不同线程交替持有锁，所以引入轻量级锁可以减少重量级锁对线程的阻塞带来的开销。
 * 轻量级锁认为环境中线程几乎没有对锁对象的竞争，即使有竞争也只需要稍微等待（自旋）下就可以获取锁，但是自旋次数有限制，如果超过该次数，则会升级为重量级锁。
 *
 * 重量级锁: 监视器锁Monitor
 */

@SuppressWarnings("all")
public class SynchorizedTest {

	private static int count;

	// 1.实例方法
	public synchronized void lockMethod(int value) {
		count += value;
	}

	// 2.实例方法中的同步块 (等价于1)
	public void lockBlock(int value) {
		synchronized (this) {
			count += value;
		}
	}

	// 3.静态方法
	public static synchronized void staticLockMethod(int value) {
		count += value;
	}

	// 4.静态方法中的同步块 (等价于3)
	public static void staticLockBlock(int value) {
		// 问题: 当在静态方法中使用同步代码块时synchronized(SynchorizedTest.class)可否替换为synchronized(this)
		// 答: 不可以 因为在静态方法可以不通过对象来调用.而synchroized锁的必须是对象 所以此处不可以替换为this
		// 问题: SynchorizedTest这个类是否是单例的
		// 答: 如果是在同一个ClassLoader空间那它一定是单例.不是同一个类加载器就不是了，不同的类加载器互相之间也不能访问。
		synchronized (SynchorizedTest.class) {
			count += value;
		}
	}

	public static void main(String[] args) {
	}

}

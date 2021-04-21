package com.ggggght.learningjava8.jmx;

/**
 * @desc: com.ggggght.learningjava8.jmx
 * @date: 2021/4/21 16:03
 * @author: ggggght
 */
public class Hello implements HelloMBean {
	@Override
	public void sayHello() {
		System.out.println("hello, world");
	}

	@Override
	public int add(int x, int y) {
		return x + y;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getCacheSize() {
		return this.cacheSize;
	}

	@Override
	public synchronized void setCacheSize(int size) {
		this.cacheSize = size;
		System.out.println("Cache size now " + this.cacheSize);
	}

	private final String name = "Reginald";
	private int cacheSize = DEFAULT_CACHE_SIZE;
	private static final int
			DEFAULT_CACHE_SIZE = 200;
}

package com.ggggght.learningjava8.jdk9_new_feature;

import lombok.Data;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <a href='https://openjdk.java.net/jeps/193'>jep193</a>
 */
public class Use_VarHandle {
	static final VarHandle VH_FOO_FIELD_I;
	static final VarHandle OBJ_HANDLE;

	static {
		try {
			VH_FOO_FIELD_I = MethodHandles.lookup().
			                              in(Foo.class).
			                              findStaticVarHandle(Foo.class, "i", int.class);
			OBJ_HANDLE = MethodHandles.lookup().unreflectVarHandle(Foo.class.getDeclaredField("obj"));
		} catch (Exception e) {
			throw new Error(e);
		}
	}

	public static void main(String[] args) {
		// Object res = VH_FOO_FIELD_I.getAndSet(2);
		// System.out.println("Foo.getI() = " + Foo.getI());
		// String[] sa = {"hello", "world"};
		// System.out.println(Arrays.toString(sa));
		// VarHandle avh = MethodHandles.arrayElementVarHandle(String[].class);
		// boolean r = avh.compareAndSet(sa, 0, "hello", "new");
		// System.out.println(r);
		// System.out.println(Arrays.toString(sa));
		//
		// Foo foo = new Foo();
		// foo.setObj(new String("hello"));
		// System.out.println("OBJ_HANDLE.get(new Foo()) = " + OBJ_HANDLE.get(foo));

		HandleTest a = new HandleTest();
		HandleTest b = new HandleTest();
		HandleTest c = new HandleTest();
		new Thread(() -> {
			while (a.getX() == 0){
			}
			VarHandle.loadLoadFence();
			System.out.println(a.getX());
			System.out.println(b.getX());
			System.out.println(c.getX());
		}, "Thread B").start();

		new Thread(() -> {
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			a.setX(1);
			a.setY(2);
			b.setX(1);
			b.setY(2);
			c.setX(1);
			c.setY(2);
			VarHandle.releaseFence();
		}, "Thread A").start();


	}
}

class Foo {
	static int i;
	Object obj;

	public static int getI() {
		return i;
	}

	public static void setI(int i) {
		Foo.i = i;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}

@Data
class HandleTest {
	int x;
	int y;

}
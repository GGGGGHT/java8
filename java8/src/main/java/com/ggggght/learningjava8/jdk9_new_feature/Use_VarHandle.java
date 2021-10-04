package com.ggggght.learningjava8.jdk9_new_feature;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.Arrays;

/**
 * <a href='https://openjdk.java.net/jeps/193'>jep193</a>
 */
public class Use_VarHandle {
	static final VarHandle VH_FOO_FIELD_I;

	static {
		try {
			VH_FOO_FIELD_I = MethodHandles.lookup().
			                              in(Foo.class).
			                              findVarHandle(Foo.class, "i", int.class);
		} catch (Exception e) {
			throw new Error(e);
		}
	}

	public static void main(String[] args) {
		Object res = VH_FOO_FIELD_I.getAndSet(2);
		System.out.println(res);

		String[] sa = {"hello", "world"};
		System.out.println(Arrays.toString(sa));
		VarHandle avh = MethodHandles.arrayElementVarHandle(String[].class);
		boolean r = avh.compareAndSet(sa, 0, "hello", "new");
		System.out.println(r);
		System.out.println(Arrays.toString(sa));
	}
}

class Foo {
	int i = 0;
}
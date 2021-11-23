package com.ggggght.learningjava8.guava;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import org.junit.jupiter.api.Test;

/**
 * @Desc: 使用guava处理空对象
 */
@SuppressWarnings("all")
public class NullObject {

	/**
	 * @method: boolean isPresent() 当optional中包含非null实例时 返回true
	 * @method: T get() 返回实例 必须是Present 如果是absent 则抛出IllegalStateException
	 * @method: T or(T) 返回Present所代表的值 如果为null 则返回默认值
	 * @method: T orNull() 返回null或Present所代表的值
	 * @method: Set<T> asSet() 如果不为null返回一个包含此Optional中的实例的不可变单例集，否则返回一个空的不可变集。
	 */
	@Test
	public void optionTest() {
		Optional<Integer> possible = Optional.of(5);
		System.out.println("possible.isPresent() = " + possible.isPresent());
		System.out.println("possible.get() = " + possible.get());
		// Optional<Object> o = possible.of(null); // not supported null value if value is
		// null throw exception
		Optional<Integer> ops = Optional.fromNullable(null);
		System.out.println("ops.isPresent() = " + ops.isPresent());
		// System.out.println("ops.get() = " + ops.get()); // throw IllegalStateException
		System.out.println("ops.or(2) = " + ops.or(2)); // return 2
		System.out.println("ops.orNull() = " + ops.orNull()); // return null
		System.out.println("ops.asSet() = " + ops.asSet()); // return []
		System.out.println("Strings.isNullOrEmpty(\"\") = " + Strings.isNullOrEmpty(""));
	}

}

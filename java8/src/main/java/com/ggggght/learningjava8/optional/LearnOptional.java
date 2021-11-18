package com.ggggght.learningjava8.optional;

import com.ggggght.learningjava8.po.User;
import org.junit.Test;

import java.util.Optional;

/**
 * @Desc
 * @author ght
 * @date 2019-12-20 12:17 PM Optional 容器类的常用方法 Optional.of(T t) : 创建一个 Optional 实例
 * Optional.empty() : 创建一个空的 Optional 实例 Optional.ofNullable(T t):若 t 不为 null,创建 Optional
 * 实例,否则创建空实例 isPresent() : 判断是否包含值 orElse(T t) : 如果调用对象包含值，返回该值，否则返回t orElseGet(Supplier
 * s) :如果调用对象包含值，返回该值，否则返回 s 获取的值 map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回
 * Optional.empty() flatMap(Function mapper):与 map 类似，要求返回值必须是Optiona
 */

@SuppressWarnings("all")
public class LearnOptional {

	/**
	 * @target 用来创建optional对象
	 */
	@Test
	public void createOptionalTest() {
		// 不允许传空值 如传递空值会抛NullPointerException
		Optional<User> op = Optional.of(new User());
		System.out.println(op.get());
		// 允许传空值
		Optional<Object> op1 = Optional.ofNullable(null);
		// 构建一个空的optional对象
		Optional<Object> op2 = Optional.empty();
	}

	/**
	 * @target 用来测试isPresent()方法 当optional对象不为空时isPresent()返回为true 否则为false
	 */
	@Test
	public void isPresentTest() {
		Optional<User> user = Optional.of(new User());

		// 此时会输出 User(name=null, age=null, sex=null)
		if (user.isPresent()) {
			System.out.println(user.get());
		}
	}

	/**
	 * @target 测试orElse()方法 当optional不为空时返回optional中的对象否则返回orElse()中给定的对象
	 */
	@Test
	public void orElseTest() {
		Optional<User> op = Optional.ofNullable(null);

		User user = op.orElse(new User());
		// 会输出User(name=null, age=null, sex=null) 因为op中为空 所以会返回新创建的对象
		System.out.println(user);
	}

	/**
	 * @target 测试orElseGet()方法 orElseGet()可以接收一个Supplier参数的函数 意味着我们可以通过该方法生成我们想要的对象
	 */
	public void orElseGetTest() {
		Optional<User> op = Optional.ofNullable(null);
		User user = op.orElseGet(() -> {
			return new User("zs", 10, "男");
		});
		System.out.println(user);
	}

	/**
	 * @target 测试map
	 */
	@Test
	public void mapTest() {
		Optional<User> user = Optional.ofNullable(new User("zs", 10, "男"));
		// 获取user的name
		Optional<String> name = user.map(User::getName);
		System.out.println(name.get());
	}

	/**
	 * @target 测试flatmap
	 */
	@Test
	public void faltMapTest() {
		Optional<User> user = Optional.ofNullable(new User("zs", 10, "男"));
		// 获取user的name
		Optional<String> s = user.flatMap((u) -> Optional.of(u.getName()));
		System.out.println(s.get());
	}

}

package com.ggggght.learningjava8.guava;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * @Desc: 使用guava中不可变的集合
 */
@SuppressWarnings("all")
public class ImmutableCollection {

	/**
	 * @Desc: 创建不可变集合的几种方式 - 使用copyOf ImmutableSet.copyOf(set) - 使用of
	 * ImmutableSet.of("a",1,22) - 使用builder
	 */
	@Test
	public void createImmutable() {
		// HashSet<Integer> jdkSet = new HashSet<>();
		// jdkSet.add(1);
		// jdkSet.add(2);
		// jdkSet.add(3);
		// ImmutableSet<Integer> guavaSet1 = ImmutableSet.copyOf(jdkSet);
		// ImmutableSet<Integer> guavaSet2 = ImmutableSet.of(1, 2, 3);
		// ImmutableSet<Integer> guavaSet3 = ImmutableSet.<Integer>builder().add(1, 2,
		// 3).build();
		// List<String> list = Lists.newArrayList("ali", "pdd", "pdd");
		Set<Person1> set = new HashSet<>();
		Person1 zs = new Person1() {
			{
				setName("zs");
			}
		};
		set.add(zs);
		System.out.println("set.size() = " + set.size());
		set.forEach(System.out::println);
		zs.setName("ls");
		zs.hashCode();
		set.add(zs);
		System.out.println("set.size() = " + set.size());
		set.forEach(System.out::println);
	}

}

class Person1 {

	String name;

	// Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Person1 person1 = (Person1) o;
		return Objects.equals(name, person1.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public String toString() {
		return "Person1{" + "name='" + name + '\'' + '}';
	}

}

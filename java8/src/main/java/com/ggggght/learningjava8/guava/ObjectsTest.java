package com.ggggght.learningjava8.guava;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;
import org.junit.jupiter.api.Test;

import static com.google.common.base.Objects.equal;

public class ObjectsTest {

	@Test
	public void objectsMethodTest() {
		System.out.println("equal(\"a\",\"a\") = " + equal("a", "a"));
		Preconditions.checkState(equal(java.util.Objects.hash("haha"), Objects.hashCode("haha")));
		System.out.println("MoreObjects.toStringHelper(this).add(\"c\", \"1\").toString() = "
				+ MoreObjects.toStringHelper(this).add("c", "1").toString());
		// compare two person first compare with age then name
	}

}

class Person implements Comparable<Person> {

	Integer age;

	String name;

	@Override
	public int compareTo(Person other) {
		return ComparisonChain.start().compare(this.age, other.age).compare(this.name, other.name).result();
	}

}

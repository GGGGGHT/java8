package com.ggggght.learningjava8.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
public class User implements Comparable {

	String name;

	Integer age;

	String sex;

	public User(Integer age) {
		this.age = age;
	}

	@Override
	public int compareTo(Object o) {
		return this.age < ((User) o).getAge() ? 1 : -1;
	}

}
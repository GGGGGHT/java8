package com.ggggght.learningjava8.po;




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

	public User() {
	}

	public User(String name, Integer age, String sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "User{" + "name='" + name + '\'' + ", age=" + age + ", sex='" + sex + '\'' + '}';
	}
}
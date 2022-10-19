package com.ggggght.learningjava8.spel;

import java.util.*;

public class Society {

	private String name;
	
	public static String Advisors = "advisors";
	public static String President = "president";
	
	private List<Inventor> members = new ArrayList<Inventor>();
	private Map officers = new HashMap();

	public List getMembers() {
		return members;
	}

	public Map getOfficers() {
		return officers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMember(String name)
	{
		for (Inventor inventor : members) {
			if (inventor.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	@Override public String toString() {
		return "Society{" +
			"name='" + name + '\'' +
			", members=" + members +
			", officers=" + officers +
			'}';
	}
}
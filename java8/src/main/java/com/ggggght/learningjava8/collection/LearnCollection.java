package com.ggggght.learningjava8.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.junit.jupiter.api.Test;

/**
 * @author ght
 * @Desc 学习容器相关的内容 包括Collection.Map
 * @date 2019-12-28 11:07 AM Collection / | \ List Set Queue
 */

@SuppressWarnings("all")
public class LearnCollection {

	public static void main(String[] args) {
		// System.out.println(romanToInt("MCMXCIV"));
		// String[] arr = {"flower", "flow", "flight"};
		// Optional<String> min = Arrays.stream(arr).min((s1, s2) -> s1.length() -
		// s2.length());
		// min.orElse()
		System.out.println(minRemoveToMakeValid("())()((("));
	}

	public static int romanToInt(String s) {
		Map<Character, Integer> map = new HashMap<>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		int res = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'I' && i + 1 < s.length()) {
				if (s.charAt(i + 1) == 'V') {
					res += 4;
					i++;
				}
				else if (s.charAt(i + 1) == 'X') {
					res += 9;
					i++;
				}
				else {
					res += map.get(s.charAt(i));
				}
			}
			else if (s.charAt(i) == 'X' && i + 1 < s.length()) {
				if (s.charAt(i + 1) == 'L') {
					res += 40;
					i++;
				}
				else if (s.charAt(i + 1) == 'C') {
					res += 90;
					i++;
				}
				else {
					res += map.get(s.charAt(i));
				}
			}
			else if (s.charAt(i) == 'C' && i + 1 < s.length()) {
				if (s.charAt(i + 1) == 'D') {
					res += 400;
					i++;
				}
				else if (s.charAt(i + 1) == 'M') {
					res += 900;
					i++;
				}
				else {
					res += map.get(s.charAt(i));
				}
			}
			else {
				res += map.get(s.charAt(i));
			}
		}
		return res;
	}

	public String longestCommonPrefix(String[] strs) {
		return "";
	}

	@Test
	public void collectionToArray() {
		String[] strs = { "a", "b", "c" };
		final List<String> list = Arrays.asList(strs);
		// list.add("ght");
		strs[0] = "x";
		list.forEach(System.out::println);
		// String[] arr = list.toArray(new String[0]);
	}

	@Test
	public void remove() {
		List<String> list = new ArrayList<>(3);
		list.add("1");
		list.add("2");
		list.add("3");

		/*
		 * final Iterator<String> iterator = list.iterator(); while (iterator.hasNext()) {
		 * String item = iterator.next(); if ("1".equals(item)) { iterator.remove(); } }
		 *
		 * list.forEach(System.out::println);
		 */
		/*
		 * for (String s : list) { if (s.equals("1")) { list.remove(s); }
		 *
		 * }
		 *
		 * list.forEach(System.out::println);
		 */
		Stack<Character> stack = new Stack();
		final Character peek = stack.peek();
		System.out.println('a' == peek);

	}

	public static String minRemoveToMakeValid(String s) {
		Stack<Character> stack = new Stack<>();
		StringBuilder builder = new StringBuilder();
		for (char c : s.toCharArray()) {
			// 如果是(则入栈
			if (c == '(') {
				stack.push(c);
				builder.append('*');
				continue;
			}
			else if (c == ')') {
				// 如果是）则判断当前的栈顶是否是（
				Character t = ' ';
				if (!stack.empty()) {
					// stack.pop();
					builder.append('/');
					continue;
				}
				else if (t == ')') {
					continue;
				}
			}
			else { // 如果是字母则直接跳过
				builder.append(c);
				continue;
			}
		}

		String str = builder.toString();
		System.out.println(str);
		str = str.replaceAll("\\/", ")");
		for (int i = 0; i < stack.size(); i++) {
			str = str.replaceFirst("\\*", "(");
		}
		str = str.replaceAll("\\*", "");
		return str;
	}

}

package com.ggggght.learningjava8;

import java.util.Arrays;
import java.util.Objects;

public class KMPSolution {

	public static void main(String[] args) {
		System.out.println(res("aaa"));
		// System.out.println(strStr("BBC ABCDAB ABCDABDABDE", "ABCDABD"));
		// System.out.println(subStr("BBC ABCDAB ABCDABDABDE", "ABCDABD"));
		// System.out.println(strStr("", "A"));
		// System.out.println(subStr("", "A"));
	}

	public static int strStr(String haystack, String needle) {
		int i = 0, j = 0, n = haystack.length(), m = needle.length();

		if (m == 0)
			return 0;
		int[] lps = next(needle);

		System.out.println("lps:" + Arrays.toString(lps));
		while (i < n) {
			if (haystack.charAt(i) == needle.charAt(j)) {
				i++;
				j++;
				// found the needle in haystack!
				if (j == m)
					return i - m;
			}
			else if (j > 0) {
				j = lps[j - 1];
			}
			else {
				i++;
			}
		}

		return -1;
	}

	public static int subStr(String source, String patten) {
		char[] strArr = source.toCharArray();
		char[] patArr = patten.toCharArray();
		int strLen = strArr.length;
		int patLen = patArr.length;
		int i = 0, j = 0;
		while (i < strLen && j < patLen) {
			if (strArr[i] == patArr[j]) {
				i++;
				j++;
			}
			else {
				i = i - j + 1;
				j = 0;
			}
		}

		if (j == patLen) {
			return i - j;
		}
		return -1;
	}

	// KMP get the longest prefix and suffix
	// 此处的目的是找到部分匹配值
	// A B C D A B D
	// 0 0 0 0 1 2 0
	// ABCDABD
	// 0000120
	private static int[] getLPS(String s, int n) {
		int i = 1, len = 0;
		int[] lps = new int[n];

		while (i < n) {
			if (s.charAt(i) == s.charAt(len)) {
				lps[i++] = ++len;
			}
			else if (len == 0) {
				lps[i++] = 0;
			}
			else {
				len = lps[len - 1];
			}
		}

		return lps;
	}

	/**
	 * 获取最长的子数组
	 * @param source
	 * @return
	 */
	private static int[] next(String source) {
		int[] res = new int[source.length()];
		// a b c a b c
		// 0 1 2 3 4 5
		// - 0
		final char[] chars = source.toCharArray();
		res[0] = 0;

		int i = 1, j = 0;

		while (i < res.length) {
			if (chars[i] == chars[j]) {
				res[i++] = ++j;
			}
			else if (j == 0) {
				res[i++] = 0;
			}
			else {
				j = res[j - 1];
			}
		}

		return res;
	}

	public static int res(String str) {
		int res = 0;
		int len;
		if (str == null || (len = str.length()) == 0)
			return 0;

		char[] chars = str.toCharArray();
		for (int i = 0; i < 2 * len - 1; i++) {
			int left = i / 2, right = i % 2 + left;
			while (left >= 0 && right < len && chars[left] == chars[right]) {
				left--;
				right++;
				res++;
			}

		}

		return res;
	}

}

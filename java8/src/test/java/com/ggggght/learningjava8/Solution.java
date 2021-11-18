package com.ggggght.learningjava8;

import java.util.ArrayList;
import java.util.List;

class Solution {

	public static void main(String[] args) {
		int[] nums = { 9, 9, 0, 9, 0 };
		// System.out.println(Arrays.toString(new Solution().plusOne(nums)));
		// System.out.println(new Solution().generateParenthesis(4).size());
		System.out.println(new Solution().addBinary("1", "111")); // 11 1 1o0
	}

	public int[] plusOne(int[] digits) {
		int len = digits.length;
		// 考虑进位
		int carry = 0;

		for (int i = len - 1; i >= 0; i--) {
			if (digits[i] + 1 == 10) {
				digits[i] = 0;
				carry += 1;
			}
			else {
				digits[i] += 1;
				break;
			}
		}
		if (carry == len) {
			int[] res = new int[len + 1];
			res[0] = 1;
			return res;
		}

		return digits;
	}

	public List<String> generateParenthesis(int n) {
		List<String> ans = new ArrayList<String>();
		backtrack(ans, new StringBuilder(), 0, 0, n);
		return ans;
	}

	public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
		if (cur.length() == max * 2) {
			ans.add(cur.toString());
			return;
		}
		if (open < max) {
			cur.append('(');
			backtrack(ans, cur, open + 1, close, max);
			cur.deleteCharAt(cur.length() - 1);
		}
		if (close < open) {
			cur.append(')');
			backtrack(ans, cur, open, close + 1, max);
			cur.deleteCharAt(cur.length() - 1);
		}
	}

	public String addBinary(String a, String b) {

		if (a.length() == 0)
			return b;
		if (b.length() == 0)
			return a;

		int aLen = a.length();
		int bLen = b.length();
		int minL = Integer.min(aLen, bLen);
		int min = a.length() > b.length() ? 0 : 1;
		int carry = 0;
		StringBuilder builder = new StringBuilder();
		for (int i = 1; i <= minL; i++) {
			// 从最后往前加
			int tmp = a.charAt(a.length() - i) + b.charAt(b.length() - i);
			if (tmp == 98) {
				carry += 1;
				builder.append(0);
			}
			else {
				if (carry == 1) {
					if (tmp == 97) {
						builder.append(0);
					}
					else {
						carry -= 1;
						builder.append(1);
					}
				}
				else {
					builder.append(tmp == 97 ? 1 : 0);
				}
			}
		}

		if (carry != 0) {
			if (min == 1 && bLen - minL == 0) {
				builder.append(1);
			}
			else {
				builder.append(test(min == 0 ? a.substring(0, aLen - minL) : b.substring(0, bLen - minL), "1"));
			}
		}

		if (min == 1) {
			for (int i = bLen - minL - 1; i >= 0; i--) {
				builder.append(b.charAt(i));
			}
		}
		else {
			for (int i = aLen - minL - 1; i >= 0; i--) {
				builder.append(a.charAt(i));
			}
		}
		return builder.reverse().toString();
	}

	private StringBuilder test(String a, String b) {
		StringBuilder builder = new StringBuilder();

		int aLen = a.length();
		int bLen = b.length();
		int minL = Integer.min(aLen, bLen);
		int carry = 0;
		for (int i = 1; i <= minL; i++) {
			// 从最后往前加
			int tmp = a.charAt(a.length() - i) + b.charAt(b.length() - i);
			if (tmp == 98) {
				carry += 1;
				builder.append(0);
			}
			else {
				if (carry == 1) {
					if (tmp == 97) {
						builder.append(0);
					}
					else {
						carry -= 1;
						builder.append(1);
					}
				}
				else {
					builder.append(tmp == 97 ? 1 : 0);
				}
			}
		}

		int min = a.length() > b.length() ? 0 : 1;
		while (--carry != 0) {
			if (min == 1 && bLen - minL == 0) {
				builder.append(1);
			}
			else {
				builder.append(test(min == 0 ? a.substring(0, aLen - minL) : b.substring(0, bLen - minL), "1"));
			}
		}
		return builder;
	}

}
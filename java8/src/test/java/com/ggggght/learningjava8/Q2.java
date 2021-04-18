package com.ggggght.learningjava8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@SuppressWarnings("all")
public class Q2 {
	private static final int CHARLENGTH = 15;
	private static final int ANUM = 97;
	private static final int ZNUM = 122;
	private static final int _0SUM = 48;
	private static final int _9SUM = 57;
	
	/**
	 * 某产品的⽤户注册邀请码为⼀串有⼩写字⺟和数字组成的字符串，字符串⻓度为16。当⽤户数据邀
	 * 请码的时候，系统需要对邀请码做有效性验证，假设验证规则如下：
	 * <p>
	 * 3、将奇数位总和加上偶数位总和，结果可以被10整除；
	 * 4、⼩写字⺟对应数值，可由下⾯键值对确定；
	 * [(a,1), (b,2), (c,3)…,(i,9), (j,1), (k, 2)…]，亦即，按字⺟顺序，1-9循环。
	 * 输⼊：输⼊16位字符串，表示邀请码
	 * 输出：输出“ok”或者“error”
	 */
	public static void main(String[] args) {
	
	}
	
	private static boolean check(String str) {
		if (str.length() != 16) {
			return false;
		}
		
		final int lastCharIndex = findLastChar(str);
		final int lastNumIndex = findLastNum(str);
		// System.out.printf("%d\t%d", lastNumIndex, lastCharIndex);
		// 1、 从序列号最后⼀位字符开始，逆向将奇数位(1、3、5等等)相加；
		// 2、从序列号最后⼀位数字开始，逆向将偶数位数字，先乘以2（如果乘积为两位数，则将其减去 -9，再求和；
		int tmp = 1;
		int res = 0;
		for (int i = lastCharIndex; i >= 0; i--) {
			final char curChar = str.charAt(i);
			
			int curCharCode = isChar(curChar) ? getCharCode(curChar) : getNumCode(curChar);
			if ((tmp++ & 1) == 1) {
				res += curCharCode;
			}
		}
		tmp = 1;
		for (int i = lastNumIndex; i >= 0; i--) {
			final char curChar = str.charAt(i);
			int curCharCode = isChar(curChar) ? getCharCode(curChar) : getNumCode(curChar);
			if ((tmp++ & 1) == 0) {
				res += curCharCode;
			}
		}
		
		return res % 10 == 0;
	}
	
	/**
	 * 获取数字所对应的数值
	 *
	 * @param curChar
	 * @return
	 */
	private static int getNumCode(char curChar) {
		return curChar - _0SUM >= 5 ? 2 * ((int) curChar) - 1 : (int) curChar;
	}
	
	/**
	 * 返回当前的字符串所对应的数值
	 *
	 * @param charAt
	 * @return
	 */
	private static int getCharCode(char curChar) {
		return ((curChar - ANUM) % 9) + 1;
	}
	
	/**
	 * 找到最后一个数字索的引位置
	 *
	 * @param str
	 * @return
	 */
	private static int findLastNum(String str) {
		// 防止全是字符的情况
		int res = -1;
		for (int i = CHARLENGTH; i >= 0; i--) {
			char curChar = str.charAt(i);
			if (curChar >= _0SUM && curChar <= _9SUM) {
				res = i;
				break;
			}
		}
		return res;
	}
	
	/**
	 * 找到最后一个字符的索引位置
	 *
	 * @param str
	 * @return
	 */
	private static int findLastChar(String str) {
		// 防止全是数字的情况
		int res = -1;
		for (int i = CHARLENGTH; i >= 0; i--) {
			char curChar = str.charAt(i);
			if (curChar >= ANUM && curChar <= ZNUM) {
				res = i;
				break;
			}
		}
		return res;
	}
	
	/**
	 * 判断当前的元素是否是字符
	 *
	 * @param c
	 * @return
	 */
	private static boolean isChar(char c) {
		return c >= ANUM && c <= ZNUM;
	}
}

package com.ggggght.learningjava8.collection;

import java.util.*;

/**
 * @author ght
 * @Desc 学习容器相关的内容 包括Collection.Map
 * @date 2019-12-28 11:07 AM
 * Collection
 * /    |   \
 * List  Set  Queue
 */

@SuppressWarnings("all")
public class LearnCollection {
    public static void main(String[] args) {
        // System.out.println(romanToInt("MCMXCIV"));
        String[] arr = {"flower", "flow", "flight"};
        Optional<String> min = Arrays.stream(arr).min((s1, s2) -> s1.length() - s2.length());
        // min.orElse()

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
                } else if (s.charAt(i + 1) == 'X') {
                    res += 9;
                    i++;
                }else {
                    res += map.get(s.charAt(i));
                }
            } else if (s.charAt(i) == 'X' && i + 1 < s.length()) {
                if (s.charAt(i + 1) == 'L') {
                    res += 40;
                    i++;
                } else if (s.charAt(i + 1) == 'C') {
                    res += 90;
                    i++;
                }else {
                    res += map.get(s.charAt(i));
                }
            } else if (s.charAt(i) == 'C' && i + 1 < s.length()) {
                if (s.charAt(i + 1) == 'D') {
                    res += 400;
                    i++;
                } else if (s.charAt(i + 1) == 'M') {
                    res += 900;
                    i++;
                }else {
                    res += map.get(s.charAt(i));
                }
            }else
                res += map.get(s.charAt(i));
        }
        return res;
    }

    public String longestCommonPrefix(String[] strs) {
        return "";
    }
}

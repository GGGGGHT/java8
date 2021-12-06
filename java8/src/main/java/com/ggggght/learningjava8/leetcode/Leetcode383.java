package com.ggggght.learningjava8.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 为了不在赎金信中暴露字迹，从杂志上搜索各个需要的字母，组成单词来表达意思。
 * <p>
 * 给你一个赎金信 (ransomNote) 字符串和一个杂志(magazine)字符串，判断 ransomNote 能不能由 magazines 里面的字符
 * 构成。
 * <p>
 * 如果可以构成，返回 true ；否则返回 false 。
 * <p>
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：ransomNote = "a", magazine = "b"
 * 输出：false
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：ransomNote = "aa", magazine = "ab"
 * 输出：false
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：ransomNote = "aa", magazine = "aab"
 * 输出：true
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= ransomNote.length, magazine.length <= 10⁵
 * ransomNote 和 magazine 由小写英文字母组成
 * <p>
 * Related Topics 哈希表 字符串 计数 👍 220 👎
 */
public class Leetcode383 {
  public static void main(String[] args) {
    Leetcode383 leetcode383 = new Leetcode383();
    System.out.println(leetcode383.canConstruct2("a", "b"));
  }

  /**
   * 使用哈希表
   * @param ransomNote
   * @param magazine
   * @return
   */
  public boolean canConstruct(String ransomNote, String magazine) {
    if(ransomNote.length() == 0) return true;
    if(magazine.length() == 0) return false;

    char[] chars = magazine.toCharArray();
    Map<Character, Integer> map = new HashMap<>();
    for (char aChar : chars) {
      map.put(aChar, map.getOrDefault(aChar, 0) + 1);
    }

    char[] rans = ransomNote.toCharArray();
    for (char ran : rans) {
      Integer integer = map.get(ran);
      if (integer == null) return false;
      else {
        integer -= 1;
        if (integer == 0) {
          map.remove(ran);
        } else {
          map.put(ran, integer);
        }
      }
    }

    return true;
  }

  /**
   * 使用数组
   * @param ransomNote
   * @param magazine
   * @return
   */
  public boolean canConstruct2(String ransomNote, String magazine) {
    int[] arr = new int[26];
    char[] chars = magazine.toCharArray();
    for (char aChar : chars) {
      arr[aChar - 'a']++;
    }

    char[] rans = ransomNote.toCharArray();
    for (char ran : rans) {
      if(--arr[ran-'a'] < 0) return false;
    }

    return true;
  }
}

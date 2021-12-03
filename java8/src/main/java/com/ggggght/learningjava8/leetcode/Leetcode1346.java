package com.ggggght.learningjava8.leetcode;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 给你一个整数数组 arr，请你检查是否存在两个整数 N 和 M，满足 N 是 M 的两倍（即，N = 2 * M）。
 * <p>
 * 更正式地，检查是否存在两个下标 i 和 j 满足：
 * <p>
 * i != j
 * 0 <= i, j < arr.length
 * arr[i] == 2 * arr[j]
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [10,2,5,3]
 * 输出：true
 * 解释：N = 10 是 M = 5 的两倍，即 10 = 2 * 5 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：arr = [7,1,14,11]
 * 输出：true
 * 解释：N = 14 是 M = 7 的两倍，即 14 = 2 * 7 。
 * <p>
 * 示例 3：
 * <p>
 * 输入：arr = [3,1,7,11]
 * 输出：false
 * 解释：在该情况下不存在 N 和 M 满足 N = 2 * M 。
 * <p>
 * 提示：
 * 2 <= arr.length <= 500
 * -10^3 <= arr[i] <= 10^3
 * <p>
 * Related Topics 数组 哈希表 双指针 二分查找 排序 👍 44 👎
 */
public class Leetcode1346 {
  public static void main(String[] args) {
    Leetcode1346 solution = new Leetcode1346();
    var arr = new int[] {0, 0};
    System.out.println(solution.checkIfExist(arr));
  }

  public boolean checkIfExist(int[] arr) {
    var len = arr.length;
    Arrays.sort(arr);
    var left = 0;
    while (left <= len - 1) {
      for (int j = len - 1; j >= 0; j--) {
        if (arr[left] * 2 == arr[j] && left != j) {
          return true;
        }
      }
      left++;
    }
    return false;
  }

  /**
   * 一次遍历  使用set
   * 奇数只用添加 2 * i 因为任意一个数乘2不可能是奇数
   * 偶数需要添加2*i 和 i/2 因为i 有可能也会是别的数乘2得来的
   * @param arr
   * @return
   */
  public boolean checkIfExists2(int[] arr) {
    HashSet<Integer> set = new HashSet<Integer>();
    for (int i : arr) {
      if (set.contains(i)) return true;
      set.add(i * 2);
      if (i % 2 == 0) set.add(i / 2);
    }
    return false;
  }
}

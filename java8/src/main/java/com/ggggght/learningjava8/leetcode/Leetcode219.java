package com.ggggght.learningjava8.leetcode;

import java.util.HashMap;

public class Leetcode219 {
  public static void main(String[] args) {
    System.out.println(containsNearbyDuplicate2(new int[] {1, 0, 1, 1}, 1));
  }

  /**
   * 暴力解 无法AC
   *
   * @param nums
   * @param k
   * @return
   */
  public boolean containsNearbyDuplicate(int[] nums, int k) {
    int len;
    if ((len = nums.length) == 1) return false;

    for (int i = 0; i < len; i++) {
      int t = nums[i];
      for (int j = i + 1; j < len; j++) {
        if (nums[j] == t && Math.abs(i - j) <= k) {
          return true;
        }
      }
    }
    return false;
  }

  public static boolean containsNearbyDuplicate2(int[] nums, int k) {
    HashMap<Integer, Integer> num2Idx = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (num2Idx.containsKey(nums[i])) {
        Integer integer = num2Idx.get(nums[i]);
        if (Math.abs(integer - i) <= k) {
          return true;
        } else {
          num2Idx.put(nums[i], i);
        }
      } else {
        num2Idx.put(nums[i], i);
      }
    }
    return false;
  }
}

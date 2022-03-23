package com.ggggght.learningjava8.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode1630 {
  public static void main(String[] args) {
    // System.out.println(checkArithmeticSubarrays(new int[] {4, 6, 5, 9, 3, 7}, new int[] {0, 0, 2},
    //     new int[] {2, 3, 5}));

    System.out.println(checkSubArrays(new int[] {-12,-9,-3,-12,-6,15,20,-25,-20,-15,-10}, new int[] {0,0,1,6,4,8,7},
        new int[] {1,4,4,9,7,9,10}));
  }
  public static List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
    List<Boolean> ans = new ArrayList<>(l.length);
    for (int i = 0; i < l.length; i++) {
      int left = l[i], right = r[i];
      int[] ints = Arrays.copyOfRange(nums, left, right+1);
      Arrays.sort(ints);
      System.out.println(Arrays.toString(ints));
      int t = ints[ints.length - 1] - ints[ints.length - 2];
      boolean b = true;
      for (int i1 = ints.length - 2; i1 > 0; i1--) {
       if(ints[i1] - ints[i1-1] != t) {
         b = false;
         break;
       }
      }
      ans.add(b);
    }
    return ans;
  }

  /**
   * 思路上是一个子数组的其他元素都减去子数组中的最小值, 得到数组D,
   * 如果子数组为等差序列, 那么数组D的最小值d为D的共有因子, D的所有元素都除以d, 就能够构成1, 2, 3, 4,...
   * 如果不能整除或者不能构成1, 2, 3, 4, 那么, 就不是等差序列.
   * 注意, 有一个特例需要判断: 当d = 0的时候, 不能作为分母.
   * 当d = 0的时候, 只需要判断子序列是否是一个常数, 即可.
   *
   * @param nums
   * @param l
   * @param r
   * @return
   */
  public static List<Boolean> checkSubArrays(int[] nums, int[] l, int[] r) {
    int len = l.length;
    List<Boolean> ans = new ArrayList<>(len);
    for (int i = 0; i < len; i++) {
      int left = l[i], right = r[i];
      int size = right - left +1;
      if(size == 2) {
        ans.add(true);
        continue;
      }

      // num1 是left -> right中最小的数 num2是次小的数
      int num1 = Integer.MAX_VALUE, num2 = Integer.MAX_VALUE;
      for (int j = left; j <= right; ++j) {
        if (nums[j] < num1 && nums[j] < num2) {
          num2 = num1;
          num1 = nums[j];
        } else if (nums[j] >= num1 && nums[j] < num2) {
          num2 = nums[j];
        }
      }

      int minD = num2 - num1;
      boolean visited[] = new boolean[size];
      // memset(visited, 0, sizeof(visited));
      boolean isTrue = true;
      for (int j = left; j <= right; ++j) {
        int tmp = nums[j] - num1;
        // minD can be 0
        // when minD is not 0 ->
        // "tmp % minD": tmp should be multiple of minD
        // "tmp / minD >= size": multiple not equal range from 0 to size - 1
        // "visited[tmp / minD]": has the same number when minD is not 0
        if ((minD == 0 && tmp != 0) || (minD != 0 && (tmp % minD != 0 || tmp / minD >= size || visited[tmp / minD]))) {
          isTrue = false;
          break;
        }
        if (minD != 0) {
          visited[tmp / minD] = true;
        }
      }

      ans.add(isTrue);
    }

    return ans;
  }
}



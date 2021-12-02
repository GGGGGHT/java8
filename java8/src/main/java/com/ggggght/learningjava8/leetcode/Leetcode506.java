package com.ggggght.learningjava8.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都 互不相同 。
 * <p>
 * 运动员将根据得分 决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，依此类推。运动员的名次决定了他们的获奖情况：
 * <p>
 * <p>
 * 名次第 1 的运动员获金牌 "Gold Medal" 。
 * 名次第 2 的运动员获银牌 "Silver Medal" 。
 * 名次第 3 的运动员获铜牌 "Bronze Medal" 。
 * 从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。
 * <p>
 * <p>
 * 使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。
 * <p>
 * 示例 1：
 * <p>
 * 输入：score = [5,4,3,2,1]
 * 输出：["Gold Medal","Silver Medal","Bronze Medal","4","5"]
 * 解释：名次为 [1ˢᵗ, 2ⁿᵈ, 3ʳᵈ, 4ᵗʰ, 5ᵗʰ] 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：score = [10,3,8,9,4]
 * 输出：["Gold Medal","5","Bronze Medal","Silver Medal","4"]
 * 解释：名次为 [1ˢᵗ, 5ᵗʰ, 3ʳᵈ, 2ⁿᵈ, 4ᵗʰ] 。
 * <p>
 * 提示：
 * <p>
 * n == score.length
 * 1 <= n <= 10⁴
 * 0 <= score[i] <= 10⁶
 * score 中的所有值 互不相同
 * <p>
 * Related Topics 数组 排序 堆（优先队列） 👍 138 👎
 */
public class Leetcode506 {
  public static void main(String[] args) {
    Leetcode506 solution = new Leetcode506();
    var arr = new int[] {10,3,8,9,4};
    String[] res = solution.findRelativeRanks(arr);
    System.out.println(Arrays.toString(res));
  }

  public String[] findRelativeRanks(int[] score) {
    int len = score.length;
    String[] res = new String[len];
    int[] tmp = new int[len];
    for (int i = 0; i < score.length; i++) {
      int t = 0;
      for (int k : score) {
        if (score[i] > k) {
          t++;
        }
      }
      tmp[i] = t;
    }

    System.out.println(Arrays.toString(tmp));
    for (int i = 0; i < tmp.length; i++) {
      if (tmp[i] == len - 1) {
        res[i] = "Gold Medal";
      } else if (tmp[i] == len - 2) {
        res[i] = "Silver Medal";
      } else if (tmp[i] == len - 3) {
        res[i] = "Bronze Medal";
      } else {
        res[i] = (len - tmp[i]) + "";
      }
    }
    return res;
  }
}

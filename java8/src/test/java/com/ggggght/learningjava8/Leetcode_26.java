package com.ggggght.learningjava8;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc: com.ggggght.learningjava8
 * @date: 2021/3/23 14:17
 * @author: ggggght
 */
public class Leetcode_26 {
	public static void main(String[] args) {
		Leetcode_26 _26 = new Leetcode_26();
		int[] nums = new int[]{};
		System.out.println(_26.removeDuplicates(nums));
	}

	public int removeDuplicates(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		int i = 0, j = 0, len = nums.length;
		//输入：nums = [0,0,1,1,1,2,2,3,3,4]
		while (i < len) {
			while (j < len  && nums[i] == nums[j]) {
				j++;
			}
			if (j == len) break;
			nums[++i] = nums[j];
		}

		return i + 1;
	}

	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>(nums.length);
		for (int i = 0,j = nums.length; i < j; i++) {
			int res = target - nums[i];
			if (map.containsKey(res)) {
				return new int[] {map.get(res),i};
			}

			map.put(nums[i], i);
		}

		return new int[]{-1, -1};
	}
}

package com.ggggght.learningjava8;

import org.junit.Test;

import java.util.*;

/**
 * @desc: com.ggggght.learningjava8
 * @date: 2021/3/23 14:17
 * @author: ggggght
 */
public class Leetcode_26 {
	public static void main(String[] args) throws Exception{
		System.out.println("hello");
		Thread.sleep(1000000000000L);
		/*final ArrayList<List<String>> list = new ArrayList<>();
		list.add(Arrays.asList("London", "New York"));
		list.add(Arrays.asList("New York","Lima"));
		list.add(Arrays.asList("Lima","Sao Paulo"));
// [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
// 		System.out.println(destCity(list));
		final int[] nums = {-10, -3, 0, 5, 9};
		System.out.println(sortedArrayToBST(nums));*/
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
	
	@Test
	public void t() {
	
	}
	public static String destCity(List<List<String>> paths) {
		if(paths.size() == 1) {
			return paths.get(0).get(1);
		}
		
		Map<String,Integer> map = new HashMap<>(paths.size() * 2);
		
		paths.forEach(l -> {
			l.forEach(v -> {
				if (map.containsKey(v)) {
					map.put(v, map.get(v) + 1);
				} else {
					map.put(v, 1);
				}
			});
		});
		
		for (Map.Entry<String, Integer> en : map.entrySet()) {
			if (en.getValue() == 1) {
				return en.getKey();
			}
		}
		return "";
	}
	
	public static int calculate(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		
		int res = 0;
		final Stack<Integer> aStack = new Stack<>();
		final Stack<Integer> bStack = new Stack<>();
		aStack.push(1);
		bStack.push(0);
		final char[] chars = s.toCharArray();
		for (char aChar : chars) {
			if(aChar == 'A') {
				// x = 2 * x + y;
				aStack.push(2 * aStack.peek() + bStack.peek());
			}
			
			if (aChar == 'B') {
				// y = 2 * y + x
				bStack.push(2 * bStack.peek() + aStack.peek());
			}
		}
		
		return aStack.peek() + bStack.peek();
	}
	
	public static int balancedStringSplit(String s) {
		if(s.length() <= 1) {
			return 0;
		}
		
		int res = 0;
		final int len = s.length();
		for (int l = 0; l <= len - 1; l++) {
			for (int r = l + 1; r <= len - 1; r++) {
				// l -> r
				res += flag(s.substring(l, r)) ? 1 : 0;
			}
		}
		
		return res;
	}
	
	private static boolean flag(String str) {
		// 字符串长度为奇数
		if ((str.length() & 1) != 0) {
			return false;
		}
		
		int l = 0;
		int r = 0;
		for (char c : str.toCharArray()) {
			if (c == 'L') {
				l++;
				continue;
			}
			
			if (c == 'R') {
				r++;
			}
		}
		return l == r;
	}
	
	public static TreeNode sortedArrayToBST(int[] nums) {
		return helper(nums,0,nums.length -1);
	}
	
	private static TreeNode helper(int[] nums,  int left,int right) {
		if(left == right) {
			return null;
		}
		
		int mid = ((right - left) >> 1) + left;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = helper(nums,0,mid -1 );
		root.right = helper(nums,mid+1,nums.length);
		
		return root;
	}
}

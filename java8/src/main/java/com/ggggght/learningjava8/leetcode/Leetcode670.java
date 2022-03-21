package com.ggggght.learningjava8.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 <p>给定一个非负整数，你<strong>至多</strong>可以交换一次数字中的任意两位。返回你能得到的最大值。</p>

 <p><strong>示例 1 :</strong></p>

 <pre>
 <strong>输入:</strong> 2736
 <strong>输出:</strong> 7236
 <strong>解释:</strong> 交换数字2和数字7。
 </pre>

 <p><strong>示例 2 :</strong></p>

 <pre>
 <strong>输入:</strong> 9973
 <strong>输出:</strong> 9973
 <strong>解释:</strong> 不需要交换。
 </pre>

 <p><strong>注意:</strong></p>

 <ol>
 <li>给定数字的范围是&nbsp;[0, 10<sup>8</sup>]</li>
 </ol>
 <div><div>Related Topics</div><div><li>贪心</li><li>数学</li></div></div><br><div><li>👍 228</li><li>👎 0</li></div>
 */
public class Leetcode670 {
  public static void main(String[] args) {
    // System.out.println("maximumSwap(123325) = " + maximumSwap(123325));
    // System.out.println("maximumSwap(2736) = " + maximumSwap(2736));
    // System.out.println("maximumSwap(9973) = " + maximumSwap(9973));
    // System.out.println("maximumSwap(9937) = " + maximumSwap(9937));
    // System.out.println("maximumSwap(1993) = " + maximumSwap(1993));
    // System.out.println("maximumSwap(120) = " + maximumSwap(19931105));
    // System.out.println("maximumSwap(120) = " + maximumSwap(120));
    System.out.println("maximumSwap(98368) = " + maximumSwap2(98368));
  }
  /**
   *   把第一个小数和他后面的大数交换
   */
  public static int maximumSwap(int num) {
    String s = String.valueOf(num);
    int len = s.length();
    int[] arr = new int[len];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = s.charAt(i) - '0';
    }

    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < arr.length; i++) {
      stack.clear();
      int cur = arr[i];
      for (int i1 = i+1; i1 < arr.length; i1++) {
        if (arr[i1] > cur) {
          if (stack.isEmpty()) {
            stack.push(i1);
          } else {
            if (arr[i1] >= arr[stack.peek()]) {
              stack.push(i1);
            }
          }
        }
      }

      if (!stack.isEmpty()) {
        // 交换
        Integer maxIdx = stack.pop();
        arr[maxIdx] = arr[maxIdx] ^ arr[i];
        arr[i] = arr[maxIdx] ^ arr[i];
        arr[maxIdx] = arr[maxIdx] ^ arr[i];

        int res = 0;
        for (int i1 : arr) {
          res = res * 10 + i1;
        }

        return res;
      }
    }

   return num;
  }

  public static int maximumSwap2(int num) {
    // [9, 8, 3, 6, 8]
    //      0  44   4   4
    char[] chars = Integer.toString(num).toCharArray();
    int maxIdx = chars.length - 1;
    int[] maxArr = new int[chars.length];
    for (int i = chars.length - 1; i >= 0  ; i--) {
      if (chars[i] > chars[maxIdx]) {
        maxIdx = i;
      }
      maxArr[i] = maxIdx;
    }

    System.out.println(Arrays.toString(maxArr));
    for (int i = 0; i < chars.length; i++) {
      if (chars[maxArr[i]] != chars[i]) {
        char temp = chars[maxArr[i]];
        chars[maxArr[i]] = chars[i];
        chars[i] = temp;
        break;
      }
    }
    return Integer.parseInt(new String(chars));
  }
}

package com.ggggght.learningjava8.leetcode;

public class Leetcode507 {
  public static void main(String[] args) {
    System.out.println(checkPerfectNumber2(28));
    System.out.println(checkPerfectNumber(6));
    System.out.println(checkPerfectNumber(496));
    System.out.println(checkPerfectNumber(8128));
    System.out.println(checkPerfectNumber(2));
    System.out.println(checkPerfectNumber(1));

  }
  public static boolean checkPerfectNumber(int num) {
    if(num == 1) return false;

    int res = 1;
    int mid = num >>> 1;
    int i = 2;
    while (i <= mid) {
      if (num % i == 0) {
        res += i;
      }

      i++;
    }
    return res == num;
  }

  public static boolean checkPerfectNumber2(int num) {
    if (num == 1) return false;
    int ans = 1;
    for (int i = 2; i <= num / i; i++) {
      if (num % i == 0) ans += i + num / i;
    }
    return ans == num;
  }


}

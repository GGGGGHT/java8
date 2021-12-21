package com.ggggght.learningjava8.leetcode;

public class Leetcode1154 {
  public static void main(String[] args) {
    System.out.println("dayOfYear(\"2019-01-09\") = " + dayOfYear("2019-01-09"));
    System.out.println("dayOfYear(\"2019-02-10\") = " + dayOfYear("2019-02-10"));
    System.out.println("dayOfYear(\"2003-03-01\") = " + dayOfYear("2003-03-01"));
    System.out.println("dayOfYear(\"2004-03-01\") = " + dayOfYear("2004-03-01"));
  }

  public static int dayOfYear(String date) {
    String[] split = date.split("-");
    int[] months = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int years = Integer.parseInt(split[0]);
    if (years % 4 == 0 || years % 100 == 0) {
      months[1] = 29;
    }

    int res = 0;
    for (int i = 0; i < Integer.parseInt(split[1]) - 1; i++) {
      res += months[i];
    }

    return res + Integer.parseInt(split[2]);
  }


  static int[] nums = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  static int[] f = new int[13];
  static {
    for (int i = 1; i <= 12; i++) f[i] = f[i - 1] + nums[i - 1];
  }

  /**
   * 使用模拟
   * @param date
   * @return
   */
  public int dayOfYear1(String date) {
    String[] ss = date.split("-");
    int y = Integer.parseInt(ss[0]), m = Integer.parseInt(ss[1]), d = Integer.parseInt(ss[2]);
    boolean isLeap = (y % 4 == 0 && y % 100 != 0) || y % 400 == 0;
    int ans = m > 2 && isLeap ? f[m - 1] + 1 : f[m - 1];
    return ans + d;
  }
}

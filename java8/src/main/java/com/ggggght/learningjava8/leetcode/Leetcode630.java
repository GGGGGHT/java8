package com.ggggght.learningjava8.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Leetcode630 {
  public static void main(String[] args) {
    // var courses = new int[][] {{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}};
    var courses = new int[][] {{5, 5}, {4, 6}, {2, 6}};
    Leetcode630 leetcode630 = new Leetcode630();
    System.out.println(leetcode630.scheduleCourse(courses));
  }

  public int scheduleCourse(int[][] courses) {
    Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
    for (int[] cours : courses) {
      System.out.println(Arrays.toString(cours));
    }
    PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
    int sum = 0;
    for (int[] c : courses) {
      int d = c[0], e = c[1];
      sum += d;
      q.add(d);
      if (sum > e) sum -= q.poll();
    }
    return q.size();
  }
}

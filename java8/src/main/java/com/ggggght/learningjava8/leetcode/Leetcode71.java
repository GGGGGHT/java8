package com.ggggght.learningjava8.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Leetcode71 {
  public static void main(String[] args) {
    System.out.println(simplifyPath1("/../"));
    System.out.println(simplifyPath1("/home/"));
    System.out.println(simplifyPath1("/home//foo/"));
    System.out.println(simplifyPath1("/a/../../b/../c//.//"));
  }

  /**
   * 不知所云
   * @param path
   * @return
   */
  @Deprecated
  public static String simplifyPath(String path) {
    char[] chars = path.toCharArray();
    StringBuilder builder = new StringBuilder();
    LinkedList<String> strings = new LinkedList<>();
    for (int i = 0; i < chars.length; i++) {


      while (i < chars.length - 1 && chars[i] == '/') {
        if (i == chars.length - 1 && chars[i] == '/') {
          break;
        }
        builder.append("/");
        i++;
        while (i < chars.length && chars[i] != '/' && chars[i] != '.') {
          builder.append(chars[i++]);
        }
        if (i < chars.length - 1 && chars[i] == '/' && chars[i+1] == '/') i++;
        strings.addLast(builder.toString());
        builder.setLength(0);
      }

      if (i < chars.length - 1 && chars[i] == '.') {
        while (i < chars.length && chars[i] == '.') {
          builder.append('.');
          i++;
        }

        if (builder.length() == 2 && strings.getLast().equals("/")) {
          if (strings.size() >= 2) {
            strings.removeLast();
          }
          strings.removeLast();
        }

        if (builder.length() > 2) {
          strings.addLast(builder.toString());
        }

        if (builder.length() == 1 && strings.getLast().equals("/")) {
          strings.removeLast();
        }
        builder.setLength(0);
        i--;
      }

    }

    while (!strings.isEmpty()) {
      builder.append(strings.removeFirst());
    }
    ;
    String res = builder.toString();
    return res.equals("") ? "/" : res;
  }

  /**
   * 通过队列来模拟 分类讨论
   * 1. 如果路径有效 则入队列 <br/>
   * 2. 如果路径为 .. 则弹出队尾的元素 </br/>
   * 3. 如果路径为 . 则不做处理 <br/>
   * @param path
   * @return
   */
  public static String simplifyPath1(String path) {
    Deque<String> d = new ArrayDeque<>();
    int n = path.length();
    for (int i = 1; i < n; ) {
      if (path.charAt(i) == '/') {
        ++i;
        continue;
      }
      int j = i + 1;
      while (j < n && path.charAt(j) != '/') j++;
      String item = path.substring(i, j);
      if (item.equals("..")) {
        if (!d.isEmpty()) d.pollLast();
      } else if (!item.equals(".")) {
        d.addLast(item);
      }
      i = j;
    }
    StringBuilder sb = new StringBuilder();
    while (!d.isEmpty()) sb.append("/").append(d.pollFirst());
    return sb.length() == 0 ? "/" : sb.toString();
  }

}

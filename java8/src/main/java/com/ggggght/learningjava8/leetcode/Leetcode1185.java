package com.ggggght.learningjava8.leetcode;

/**
 <p>给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。</p>

 <p>输入为三个整数：<code>day</code>、<code>month</code> 和&nbsp;<code>year</code>，分别表示日、月、年。</p>

 <p>您返回的结果必须是这几个值中的一个&nbsp;<code>{&quot;Sunday&quot;, &quot;Monday&quot;, &quot;Tuesday&quot;, &quot;Wednesday&quot;, &quot;Thursday&quot;, &quot;Friday&quot;, &quot;Saturday&quot;}</code>。</p>

 <p>&nbsp;</p>

 <p><strong>示例 1：</strong></p>

 <pre><strong>输入：</strong>day = 31, month = 8, year = 2019
 <strong>输出：</strong>&quot;Saturday&quot;
 </pre>

 <p><strong>示例 2：</strong></p>

 <pre><strong>输入：</strong>day = 18, month = 7, year = 1999
 <strong>输出：</strong>&quot;Sunday&quot;
 </pre>

 <p><strong>示例 3：</strong></p>

 <pre><strong>输入：</strong>day = 15, month = 8, year = 1993
 <strong>输出：</strong>&quot;Sunday&quot;
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示：</strong></p>

 <ul>
 <li>给出的日期一定是在&nbsp;<code>1971</code> 到&nbsp;<code>2100</code>&nbsp;年之间的有效日期。</li>
 </ul>
 <div><div>Related Topics</div><div><li>数学</li></div></div><br><div><li>👍 92</li><li>👎 0</li></div>
 */

public class Leetcode1185 {
  static String[] ss = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
  static int[] nums = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  public String dayOfTheWeek(int day, int month, int year) {
    int ans = 4;
    for (int i = 1971; i < year; i++) {
      boolean isLeap = (i % 4 == 0 && i % 100 != 0) || i % 400 == 0;
      ans += isLeap ? 366 : 365;
    }
    for (int i = 1; i < month; i++) {
      ans += nums[i - 1];
      if (i == 2 && ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)) ans++;
    }
    ans += day;
    return ss[ans % 7];
  }
}

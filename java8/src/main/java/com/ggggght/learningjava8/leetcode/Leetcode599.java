package com.ggggght.learningjava8.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 <p>假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。</p>

 <p>你需要帮助他们用<strong>最少的索引和</strong>找出他们<strong>共同喜爱的餐厅</strong>。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设答案总是存在。</p>

 <p>&nbsp;</p>

 <p><strong>示例 1:</strong></p>

 <pre>
 <strong>输入: </strong>list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
 <strong>输出:</strong> ["Shogun"]
 <strong>解释:</strong> 他们唯一共同喜爱的餐厅是“Shogun”。
 </pre>

 <p><strong>示例 2:</strong></p>

 <pre>
 <strong>输入:</strong>list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["KFC", "Shogun", "Burger King"]
 <strong>输出:</strong> ["Shogun"]
 <strong>解释:</strong> 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示:</strong></p>

 <ul>
 <li><code>1 &lt;= list1.length, list2.length &lt;= 1000</code></li>
 <li><code>1 &lt;= list1[i].length, list2[i].length &lt;= 30</code>&nbsp;</li>
 <li><code>list1[i]</code> 和 <code>list2[i]</code> 由空格<meta charset="UTF-8" />&nbsp;<code>' '</code>&nbsp;和英文字母组成。</li>
 <li><code>list1</code> 的所有字符串都是 <strong>唯一</strong> 的。</li>
 <li><code>list2</code> 中的所有字符串都是 <strong>唯一</strong> 的。</li>
 </ul>
 <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 167</li><li>👎 0</li></div>
 */
public class Leetcode599 {
  public static void main(String[] args) {

    System.out.println(
        Arrays.toString(findRestaurant(new String[] {"Shogun", "Tapioca Express", "Burger King", "KFC"},
            new String[] {"KFC", "Shogun", "Burger King"})));
  }
  public static String[] findRestaurant(String[] list1, String[] list2) {
    Map<String, Integer> map1 = new HashMap<>();
    for (String s : list1) {
      map1.put(s, 1);
    }

    for (String s : list2) {
      if (map1.containsKey(s)) {
        map1.put(s, 2);
      }
    }


    int min = Integer.MAX_VALUE;
    List<String> ans = new ArrayList<>();
    for (Map.Entry<String, Integer> entrySet : map1.entrySet()) {
      if (entrySet.getValue() != 2) {
        continue;
      }
      String key = entrySet.getKey();
      int _1 = 0;
      for (int i = 0; i < list1.length; i++) {
        if (key.equals(list1[i])) {
          _1 = i;
          break;
        }
      }
      int _2 = 0;
      for (int i = 0; i < list2.length; i++) {
        if (key.equals(list2[i])) {
          _2 = i;
          break;
        }
      }

      if(min > _1 + _2) {
        ans.clear();
        min = _1 + _2;
        ans.add(key);
        continue;
      }

      if(min == _1 + _2) {
        ans.add(key);
      }


    }

    return ans.toArray(new String[0]);
  }

  public String[] findRestaurant1(String[] list1, String[] list2) {
    int n = list1.length, m = list2.length;
    Map<String, Integer> map = new HashMap<>();
    for (int i = 0; i < n; i++) map.put(list1[i], i);
    List<String> ans = new ArrayList<>();
    int min = 3000;
    for (int i = 0; i < m; i++) {
      String s = list2[i];
      if (!map.containsKey(s)) continue;
      if (i + map.get(s) < min) {
        ans.clear();
        min = i + map.get(s);
        ans.add(s);
      } else if (i + map.get(s) == min) {
        ans.add(s);
      }
    }
    return ans.toArray(new String[0]);
  }
}

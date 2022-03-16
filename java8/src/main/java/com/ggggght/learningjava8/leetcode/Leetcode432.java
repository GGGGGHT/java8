package com.ggggght.learningjava8.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
/**
 <p>请你设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串。</p>

 <p>实现 <code>AllOne</code> 类：</p>

 <ul>
 <li><code>AllOne()</code> 初始化数据结构的对象。</li>
 <li><code>inc(String key)</code> 字符串 <code>key</code> 的计数增加 <code>1</code> 。如果数据结构中尚不存在 <code>key</code> ，那么插入计数为 <code>1</code> 的 <code>key</code> 。</li>
 <li><code>dec(String key)</code> 字符串 <code>key</code> 的计数减少 <code>1</code> 。如果 <code>key</code> 的计数在减少后为 <code>0</code> ，那么需要将这个 <code>key</code> 从数据结构中删除。测试用例保证：在减少计数前，<code>key</code> 存在于数据结构中。</li>
 <li><code>getMaxKey()</code> 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 <code>""</code> 。</li>
 <li><code>getMinKey()</code> 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 <code>""</code> 。</li>
 </ul>

 <p>&nbsp;</p>

 <p><strong>示例：</strong></p>

 <pre>
 <strong>输入</strong>
 ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
 [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
 <strong>输出</strong>
 [null, null, null, "hello", "hello", null, "hello", "leet"]

 <strong>解释</strong>
 AllOne allOne = new AllOne();
 allOne.inc("hello");
 allOne.inc("hello");
 allOne.getMaxKey(); // 返回 "hello"
 allOne.getMinKey(); // 返回 "hello"
 allOne.inc("leet");
 allOne.getMaxKey(); // 返回 "hello"
 allOne.getMinKey(); // 返回 "leet"
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>1 &lt;= key.length &lt;= 10</code></li>
 <li><code>key</code> 由小写英文字母组成</li>
 <li>测试用例保证：在每次调用 <code>dec</code> 时，数据结构中总存在 <code>key</code></li>
 <li>最多调用 <code>inc</code>、<code>dec</code>、<code>getMaxKey</code> 和 <code>getMinKey</code> 方法 <code>5 * 10<sup>4</sup></code> 次</li>
 </ul>
 <div><div>Related Topics</div><div><li>设计</li><li>哈希表</li><li>链表</li><li>双向链表</li></div></div><br><div><li>👍 198</li><li>👎 0</li></div>
 */
public class Leetcode432 {
  public static void main(String[] args) {
    AllOne allOne = new AllOne();
    allOne.inc("a");
    allOne.inc("b");
    allOne.inc("b");
    allOne.inc("b");
    allOne.inc("b");
    allOne.inc("b");
    allOne.dec("b");
    allOne.dec("b");
    System.out.println(allOne.getMaxKey()); // 返回 "hello"
    System.out.println(allOne.getMinKey()); // 返回 "hello"
    // allOne.inc("leet");
    // System.out.println(allOne.getMaxKey()); // 返回 "hello"
    // System.out.println(allOne.getMinKey()); // 返回 "leet"

  }
}

class AllOne {
  Map<String, Integer> hashmap;
  TreeMap<Integer, TreeSet<String>> treeMap;

  public AllOne() {
    hashmap = new HashMap<>();
    treeMap = new TreeMap<>(((o1, o2) -> o2 - o1));
  }

  public void inc(String key) {
    if (hashmap.containsKey(key)) {
      Integer old = hashmap.get(key);
      int newV = old + 1;
      hashmap.put(key, newV);
      TreeSet<String> oldSet = treeMap.get(old);
      oldSet.remove(key);
      if(oldSet.size() == 0) treeMap.remove(old);
      TreeSet<String> newSet = treeMap.getOrDefault(newV, new TreeSet<>());
      newSet.add(key);
      treeMap.put(newV, newSet);
    } else {
      hashmap.put(key, 1);
      TreeSet<String> newSet = treeMap.getOrDefault(1, new TreeSet<>());
      newSet.add(key);
      treeMap.put(1, newSet);
    }
  }

  public void dec(String key) {
    Integer old = hashmap.get(key);
    if (old == 1) {
      hashmap.remove(key);
    } else {
      hashmap.put(key, old - 1);
      TreeSet<String> newSet = treeMap.getOrDefault(old - 1, new TreeSet<>());
      newSet.add(key);
      treeMap.put(old - 1, newSet);
    }
    TreeSet<String> oldSet = treeMap.get(old);
    oldSet.remove(key);
    if(oldSet.size() == 0) treeMap.remove(old);
  }

  public String getMaxKey() {
    return treeMap.firstEntry() == null || treeMap.firstEntry().getValue() == null ? "" : treeMap.firstEntry().getValue().first();
  }

  public String getMinKey() {
    return treeMap.lastEntry() == null || treeMap.lastEntry().getValue() == null ? "" : treeMap.lastEntry().getValue().first();
  }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */

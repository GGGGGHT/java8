package com.ggggght.learningjava8.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
/**
 <p>给定一个保存员工信息的数据结构，它包含了员工 <strong>唯一的 id </strong>，<strong>重要度 </strong>和 <strong>直系下属的 id </strong>。</p>

 <p>比如，员工 1 是员工 2 的领导，员工 2 是员工 3 的领导。他们相应的重要度为 15 , 10 , 5 。那么员工 1 的数据结构是 [1, 15, [2]] ，员工 2的 数据结构是 [2, 10, [3]] ，员工 3 的数据结构是 [3, 5, []] 。注意虽然员工 3 也是员工 1 的一个下属，但是由于 <strong>并不是直系</strong> 下属，因此没有体现在员工 1 的数据结构中。</p>

 <p>现在输入一个公司的所有员工信息，以及单个员工 id ，返回这个员工和他所有下属的重要度之和。</p>

 <p> </p>

 <p><strong>示例：</strong></p>

 <pre>
 <strong>输入：</strong>[[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
 <strong>输出：</strong>11
 <strong>解释：</strong>
 员工 1 自身的重要度是 5 ，他有两个直系下属 2 和 3 ，而且 2 和 3 的重要度均为 3 。因此员工 1 的总重要度是 5 + 3 + 3 = 11 。
 </pre>

 <p> </p>

 <p><strong>提示：</strong></p>

 <ul>
 <li>一个员工最多有一个<strong> 直系 </strong>领导，但是可以有多个 <strong>直系 </strong>下属</li>
 <li>员工数量不超过 2000 。</li>
 </ul>
 <div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>哈希表</li></div></div><br><div><li>👍 251</li><li>👎 0</li></div>
 */

public class Leetcode690 {
  public static void main(String[] args) {
    Employee employee1 = new Employee();
    employee1.id = 1;
    employee1.importance = 5;
    employee1.subordinates = List.of(2, 3);
    Employee employee2 = new Employee();
    employee2.id = 2;
    employee2.importance = 3;
    employee2.subordinates = new ArrayList<>();
    Employee employee3 = new Employee();
    employee3.id = 3;
    employee3.importance = 3;
    employee3.subordinates = new ArrayList<>();

    List<Employee> employee11 = List.of(employee1, employee2, employee3);
    System.out.println(new Leetcode690().getImportance(employee11,1));
  }

  HashMap<Integer, List<Integer>> id2Subordinates;
  HashMap<Integer, List<Integer>> id2Subord;

  /**
   * 使用dfs 
   * @param employees
   * @param id
   * @return
   */
  public int getImportance(List<Employee> employees, int id) {
    HashMap<Integer, Integer> id2Importance = new HashMap<>();
    id2Subordinates = new HashMap<>();
    for (Employee employee : employees) {
      id2Importance.put(employee.id, employee.importance);
      id2Subordinates.put(employee.id, employee.subordinates);
    }

    if (id2Importance.get(id) == null) return 0;
    id2Subord = new HashMap<>();
    // 保存所有的下属
    for (Employee employee : employees) {
      ArrayList<Integer> subIds = new ArrayList<>();
      dfs(employee.id, subIds);
      id2Subord.put(employee.id, subIds);
    }
    List<Integer> list = id2Subord.get(id);
    if (list.size() == 0) return id2Importance.get(id);

    int res = id2Importance.get(id);
    for (Integer integer : list) {
      res += id2Importance.get(integer);
    }
    return res;
  }

  public void dfs(int id, List<Integer> subIds) {
    List<Integer> list = id2Subordinates.get(id);
    subIds.addAll(list);

    for (Integer integer : list) {
      dfs(integer,subIds);
    }
  }
}

class Employee {
  public int id;
  public int importance;
  public List<Integer> subordinates;
};
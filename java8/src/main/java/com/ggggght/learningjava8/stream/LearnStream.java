package com.ggggght.learningjava8.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @Desc 学习stream的一些常用的方法
 * @auther ght
 * @date 2019-12-20 12:17 PM
 *
 * Java 8引入了全新的Stream API。这里的Stream和I/O流不同，它更像具有Iterable的集合类，但行为和集合类又有所不同。
 *
 * stream是对集合对象功能的增强，它专注于对集合对象进行各种非常便利、高效的聚合操作，或者大批量数据操作。
 *
 * 只要给出需要对其包含的元素执行什么操作，比如 “过滤掉长度大于 10 的字符串”、“获取每个字符串的首字母”等，Stream 会隐式地在内部进行遍历，做出相应的数据转换。
 */

@SuppressWarnings("all")
public class LearnStream {
    private static List<User> userList = null;

    @Before
    public void doSomeThingBefore() {
        userList = new ArrayList<>(5);
        userList.add(new User("zs", 19, "男"));
        userList.add(new User("ls", 20, "女"));
        userList.add(new User("ww", 21, "男"));
        userList.add(new User("cl", 22, "女"));
        userList.add(new User("lq", 23, "男"));
    }

    /**
     * 用来创建stream的几种方式
     */
    @Test
    public void createTest() {
        Stream.of("1", "2", "3");
    }

    /**
     * @target:
     *  过滤出userList中所有的男性成员并输出
     */
    @Test
    public void filterTest() {
        userList.stream().filter(user -> Objects.equals("男", user.getSex())).forEach(System.out::println);
    }

    /**
     * @target:
     *  1. 过滤出userList中所有大于20岁以上的男性做为一个集合
     *  2. 遍历集合并输出
     */
    @Test
    public void filterTest1() {
        List<User> filterList = userList.stream().filter(user -> Objects.equals(user.getSex(), "男") && user.age > 20).collect(toList());
        // for (Iterator<User> iterator = filterList.iterator(); iterator.hasNext();) {
        //     System.out.println(iterator.next());
        // }
        Iterator<User> iterator = filterList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * @target:
     *  1. 获取出用户所有的名字做为一个集合
     *  2. 输出所有的成员的名字
     */
    @Test
    public void mapTest() {
        // 以下三种方式等价
        // List<String> collect = userList.stream().map(user -> user.getName()).collect(toList());
        // List<String> collect = userList.stream().map(User::getName).collect(toList());
        List collect = userList.stream().map(user -> {
            return user.getName();
        }).collect(toList());
        System.out.println(collect);
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
class User {
    String name;
    Integer age;
    String sex;
}
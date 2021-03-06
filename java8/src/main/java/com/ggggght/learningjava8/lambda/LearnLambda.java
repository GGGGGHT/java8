package com.ggggght.learningjava8.lambda;

import com.ggggght.learningjava8.po.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Function;

/**
 * @author ght
 * @Desc 学习lambda
 * @date 2019-12-21 3:44 PM
 *
 * lambda:表达式的基础语法 Java8中引入了' -> ' 的操作符 该操作符称为lambda操作符
 * 该操作符将表达式分为两个部分
 * 1. 左侧部分 代表的是表达式的参数列表
 * 2. 右侧部分 代表的是表达式中需要执行的功能 即lambda体
 *
 * 主要有三种语法格式:
 * 1. 对象::实例方法名
 * 2. 类::静态方法名
 * 3. 类::实例方法名
 *
 * 匿名类和Lambda表达式中的this和super的含义:
 * 匿名类: this代表的是类自身
 * lambda: 代表的是包含类
 *
 * 匿名类可以屏蔽包含类的变量 即可以有重名变量
 * lambda不可以
 */

@SuppressWarnings("all")
public class LearnLambda {
    private static List<User> userList = null;

    /**
     * @target: 为userList填充数据
     */
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
     * @target 无lambda表达式之前 创建一个TreeSet的方式 需要传递给TreeSet一个实现Comparator接口的匿名函数
     */
    @Test
    public void oldCreateTreeSetTest() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        TreeSet<Integer> set = new TreeSet<>(com);
    }

    /**
     * @target 使用lambda表达式之后创建TreeSet的方式
     */
    @Test
    public void newCreateTreeSetTest() {
        TreeSet<Integer> set = new TreeSet<>((o1, o2) -> Integer.compare(o1, o2));
    }

    @Test
    public void mapTest() {
        // Map<Integer, Integer> map = new HashMap<>();
        // map.put(1, 1);
        // map.put(2, 2);
        // map.put(3, 3);
        // Function<String, String> fun = s -> s.intern();
        // userList.forEach(System.out::print);

        // Function<Integer, User> fun = User::new;
        // System.out.println(fun.apply(10));
    }

    @Test
    public void test() {
        Function<Integer, String[]> fun = (x) -> new String[x];
        String[] str = fun.apply(2);
        Function<Integer, String[]> fun1 = String[]::new;
        String[] str2 = fun1.apply(2);
        System.out.println(str.length);
        System.out.println(str2.length);
    }

    @Test
    public void thisTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this.getClass().getSimpleName() = " + this.getClass().getName());
                System.out.println("hello");
            }
        }).start();
        new Thread(() -> {
            System.out.println("this.getClass().getSimpleName() = " + this.getClass().getSimpleName());
            System.out.println("world");
        }).start();
    }

    @Test
    public void variableTest() {
        int a = 10;
        Runnable r = () -> {
            // int a = 2; error
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                int a = 3;
            }
        };
    }

    public void doSomething(Runnable r) {
        r.run();
    }

    public void doSomething(Task task) {
        task.execute();
    }

    public void doSome() {
        doSomething((Task) () -> {
            System.out.println("a");
        });
    }

    interface Task {
        void execute();
    }
}

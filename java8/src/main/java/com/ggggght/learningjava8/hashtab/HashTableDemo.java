package com.ggggght.learningjava8.hashtab;


import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author ght
 * @Desc
 * @date 2020-05-18 12:40
 */

@SuppressWarnings("all")
public class HashTableDemo {
    public static void main(String[] args) {
        /*HashTable table = new HashTable(7);
        table.put(new User(1,"zs"));
        table.put(new User(2,"ls"));
        table.put(new User(3,"ww"));
        table.put(new User(4,"zl"));
        table.put(new User(5,"hh"));
        table.put(new User(6,"zz"));
        table.put(new User(7,"ss"));
        table.put(new User(8,"tt"));
        table.list();
        Optional<User> user = table.get(4);
        System.out.println(user.orElseGet(() -> new User(10, "tt")));*/
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        // System.out.println(user.isPresent());
    }
}

class HashTable {
    /**
     * 构建一个hashTable
     */
    private UserLinkedList[] table;
    private int size;
    public HashTable(int size) {
        table = new UserLinkedList[size];
        this.size = size;
    }

    /**
     * 获取当前的对象在hash表中所存的位置
     *
     * @param id
     * @return
     */
    public int getHashSlot(int id) {
        return id % size;
    }

    public void put(User user) {
        int index = getHashSlot(user.getId());
        // 第一次添加
        if (Objects.isNull(table[index])) {
            table[index] = new UserLinkedList();
        }
        table[index].add(user);
    }

    /**
     * 根据id去获取User
     *
     * @param id
     * @return
     */
    public Optional<User> get(int id) {
        int index = getHashSlot(id);
        UserLinkedList userLinkedList = table[index];
        if (Objects.isNull(userLinkedList)) {
            return Optional.empty();
        }
        User cur = userLinkedList.getHead();
        while (cur != null) {
            if (cur.getId() == id) {
                return Optional.of(cur);
            }
            cur = cur.getNext();
        }
        return Optional.empty();
    }

    public void list() {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                System.out.printf("当前的索引为: %d \r\n" , i);
                table[i].list();
            }
        }
    }
}


class UserLinkedList {
    /**
     * 头结点
     */
    private User head;
    private User tail;
    private User next;

    public User getNext() {
        return next;
    }

    public void setNext(User next) {
        this.next = next;
    }

    public User getHead() {
        return head;
    }

    public void setHead(User head) {
        this.head = head;
    }

    public User getTail() {
        return tail;
    }

    public void setTail(User tail) {
        this.tail = tail;
    }

    public void add(User user) {
        // 如果是第一次添加
        if (head == null) {
            head = user;
            // 维护一下尾节点 方便下一次添加
            tail = user;
            return;
        }

        tail.setNext(user);
        tail = user;
    }

    public void list() {
        if (Objects.isNull(head)) {
            System.out.println("链表为空");
            return;
        }

        User cur = head;
        while (!Objects.isNull(cur)) {
            System.out.printf("User Name: %s --> User Id: %d \t", cur.getName(), cur.getId());
            cur = cur.getNext();
        }
        System.out.println();
    }
}

class User {
    private Integer id;
    private String name;

    public User getNext() {
        return next;
    }

    public void setNext(User next) {
        this.next = next;
    }

    private User next;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", next=" + next +
                '}';
    }
}

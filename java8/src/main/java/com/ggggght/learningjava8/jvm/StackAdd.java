package com.ggggght.learningjava8.jvm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("all")
public class StackAdd {
  private static final Logger LOGGER = LoggerFactory.getLogger(StackAdd.class);
    public int baseValue = 4095;

    public static void main(String[] args) {
        StackAdd adder = new StackAdd();
        int i = 123;
        int j = 456;
        int r = adder.add(i, j);

        System.out.println(r);
    }

    private int add(int i, int j) {
        int tmp = i + j;
        tmp += baseValue;

        return tmp;
    }
}

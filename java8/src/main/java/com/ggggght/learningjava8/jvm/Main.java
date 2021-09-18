package com.ggggght.learningjava8.jvm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("all")
public class Main {
  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
       // String str = "flying ggggght";
        // String str =  new String("flying ggggght");

        // System.out.println(str);
        for (int i = 0; i < 10; i++) {
            foo(i);
        }
    }

    private static void foo(int i) {
        if ((i & 1) == 0) {
            System.out.println("hello world");
        }
       // System.out.println(i+1000000);
    }
}

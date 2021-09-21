package com.ggggght.learningjava8.jvm;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("all")
public class Main {
  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args)
      throws ExecutionException, InterruptedException, IOException {
    System.in.read();

    for (int i = 0; i < 10; i++) {
      new Thread(() -> {
        System.out.println(Thread.currentThread().getName() + " hello world");
      }).start();
    }
    // String str = "flying ggggght";
    //String str = new String("flying ggggght");
    //
    //System.out.println(str);
    // for (int i = 0; i < 10; i++) {
    // foo(i);
    //}
    //CompletableFuture.runAsync(Main::getLine).get();
  }

  private static void foo(int i) {
    if ((i & 1) == 0) {
      System.out.println("hello world");
    }
    // System.out.println(i+1000000);
  }

  private static void getLine() {
    try {
      CompletableFuture.runAsync(Main::getLine2).get();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void getLine2() {
    System.out.println("Line 2");
  }
}

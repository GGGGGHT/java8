package com.ggggght.learningjava8.junit;

public class Test {
  public static void main(String[] args) {
    Module module = Test.class.getModule();
    String name = module.getName();

    System.out.println(name);
  }
}

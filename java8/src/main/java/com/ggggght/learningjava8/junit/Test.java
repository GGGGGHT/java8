package com.ggggght.learningjava8.junit;

import java.util.Objects;

public class Test {
  public static void main(String[] args) {
    Module module = Test.class.getModule();
    var actual = module.getName();
    var expected = "learningjava";
    if (Objects.equals(actual, expected)) {
      return;
    }

    throw new AssertionError("""
        Wrong name!
                
        excepted: %s
          actual: %s
        """.formatted(expected, actual));
  }
}

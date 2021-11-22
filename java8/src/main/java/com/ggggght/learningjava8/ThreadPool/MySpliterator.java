package com.ggggght.learningjava8.ThreadPool;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.management.ServiceNotFoundException;

/**
 * @author admin
 */
public class MySpliterator {
  public static int countWordsIteratively(String s) {
    int counter = 0;
    boolean lastSpace = true;
    for (char c : s.toCharArray()) {
      if (Character.isWhitespace(c)) {
        lastSpace = true;
      } else {
        if (lastSpace) counter++;
        lastSpace = false;
      }
    }

    return counter;
  }

  static final String SENTENCE = " Nel mezzo del cammin di nostra vita ";

  public static void main(String[] args) {
    System.out.println("countWordsIteratively(SENTENCE) = " + countWordsIteratively(SENTENCE));
    Stream<Character> stream =
        IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
  }
}

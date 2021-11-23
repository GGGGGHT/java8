package com.ggggght.learningjava8.ThreadPool;

import java.util.Spliterator;
import java.util.function.Consumer;
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
        if (lastSpace) {
          counter++;
        }
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

class WordCounterSpliterator implements Spliterator<Character> {
  private final String str;
  private int currentChar = 0;

  public WordCounterSpliterator(String str) {
    this.str = str;
  }

  @Override public boolean tryAdvance(Consumer<? super Character> action) {
    action.accept(str.charAt(currentChar++));
    return currentChar < str.length();
  }

  @Override public Spliterator<Character> trySplit() {
    int currentSize = str.length() - currentChar;
    if (currentSize < 10) {
      return null;
    }

    for (int splitPos = currentSize / 2 + currentChar; splitPos < str.length(); ++splitPos) {
      WordCounterSpliterator spliterator =
          new WordCounterSpliterator(str.substring(currentSize, splitPos));

      currentChar = splitPos;
      return spliterator;
    }
    return null;
  }

  @Override public long estimateSize() {
    return str.length() - currentChar;
  }

  @Override public int characteristics() {
    return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
  }
}

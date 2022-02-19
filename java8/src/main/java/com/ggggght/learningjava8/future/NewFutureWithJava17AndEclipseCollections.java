package com.ggggght.learningjava8.future;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.eclipse.collections.api.bag.primitive.CharBag;
import org.eclipse.collections.api.bag.primitive.MutableCharBag;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.tuple.primitive.CharIntPair;
import org.eclipse.collections.impl.factory.Strings;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.RunnerException;

public class NewFutureWithJava17AndEclipseCollections {
  private final String haiku = """
            Breaking Through                  Pavement                  Wakin' with Bacon        Homeward Found
            ----------------                  --------                  -----------------        --------------
            The wall disappears               Beautiful pavement!       Wakin' with Bacon        House is where I am
            As soon as you break through the  Imperfect path before me  On a Saturday morning    Home is where I want to be
            Intimidation                      Thank you for the ride    Life’s little pleasures  Both may be the same
                        
            Winter Slip and Slide              Simple Nothings                With Deepest Regrets
            ---------------------              ---------------                --------------------
            Run up the ladder                  A simple flower                With deepest regrets
            Swoosh down the slide in the snow  Petals shine vibrant and pure  That which you have yet to write
            Winter slip and slide              Stares into the void           At death, won't be wrote
                        
            Caffeinated Coding Rituals  Finding Solace               Curious Cat                Eleven
            --------------------------  --------------               -----------                ------
            I arrange my desk,          Floating marshmallows        I see something move       This is how many
            refactor some ugly code,    Cocoa brewed hot underneath  What it is, I am not sure  Haiku I write before I
            and drink my coffee.        Comfort in a cup             Should I pounce or not?    Write a new tech blog.
            """;

  public static void main(String[] args) throws RunnerException {
    NewFutureWithJava17AndEclipseCollections future =
        new NewFutureWithJava17AndEclipseCollections();

    System.out.println(future.distinct_letters_Java17());
    System.out.println(future.distinct_letters_EC());
    System.out.println(future.top_letters_EC());
    System.out.println(future.top_letters_JS_V1());
  }

  @Test
  public void topLettersEclipseCollections()
  {

    CharBag chars = Strings.asChars(this.haiku)
        .select(Character::isAlphabetic)
        .collectChar(Character::toLowerCase)
        .toBag();

    ListIterable<CharIntPair> top3 = chars.topOccurrences(3);

    Assertions.assertEquals(PrimitiveTuples.pair('e', 94), top3.get(0));
    Assertions.assertEquals(PrimitiveTuples.pair('t', 65), top3.get(1));
    Assertions.assertEquals(PrimitiveTuples.pair('i', 62), top3.get(2));
  }

  /**
   * 使用Eclipse Collection
   *
   * @return agbvsdhqzx
   */
  @Benchmark public Object distinct_letters_EC() {
    return Strings.asChars(this.haiku)
        .select(Character::isAlphabetic)
        .collectChar(Character::toLowerCase)
        .distinct()
        .toString();
  }

  /**
   * 使用Java 17 API
   *
   * @return agbvsdhqzx
   */
  @Benchmark public Object distinct_letters_Java17() {
    return this.haiku.chars()
        .filter(Character::isAlphabetic)
        .map(Character::toLowerCase)
        .distinct()
        .mapToObj(Character::toString)
        .collect(Collectors.joining());
  }

  public Object top_letters_EC() {
    MutableCharBag chars = Strings.asChars(this.haiku)
        .select(Character::isAlphabetic)
        .collectChar(Character::toLowerCase)
        .toBag();

    return chars.topOccurrences(3);
  }

  public Object top_letters_JS_V1() {
    var collect = this.haiku.chars()
        .filter(Character::isAlphabetic)
        .map(Character::toLowerCase)
        .mapToObj(Letter::new)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    // List<Map.Entry<String, Long>> mostFrequentLetters = collect.entrySet()
    //     .stream()
    //     .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
    //     .limit(3)
    //     .toList();
    //
    // Map<Long, List<String>> collect1 = collect.entrySet()
    //     .stream()
    //     .collect(Collectors.groupingBy(Map.Entry::getValue,
    //         Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
    // collect1.forEach((key, value) -> System.out.println(key + "->" + value));
    return collect;
  }
}


record Letter(int codePoint){

  Letter(int codePoint) {
    this.codePoint = codePoint;
  }
}

package com.ggggght.learningjava8.future;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.eclipse.collections.api.bag.primitive.MutableCharBag;
import org.eclipse.collections.impl.factory.Strings;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.RunnerException;

public class NewFutureWithJava17AndEclipseCollections {
  String haiku = "Aa25gbvsdahb37_2135qZx";

  public static void main(String[] args) throws RunnerException {
    NewFutureWithJava17AndEclipseCollections future =
        new NewFutureWithJava17AndEclipseCollections();

    System.out.println(future.distinct_letters_Java17());
    System.out.println(future.distinct_letters_EC());
    System.out.println(future.top_letters_EC());
    System.out.println(future.top_letters_JS_V1());
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
    Map<String, Long> collect = this.haiku.chars()
        .filter(Character::isAlphabetic)
        .map(Character::toLowerCase)
        .mapToObj(Character::toString)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    List<Map.Entry<String, Long>> mostFrequentLetters = collect.entrySet()
        .stream()
        .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
        .limit(3)
        .toList();

    Map<Long, List<String>> collect1 = collect.entrySet()
        .stream()
        .collect(Collectors.groupingBy(Map.Entry::getValue,
            Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
    collect1.forEach((key, value) -> System.out.println(key + "->" + value));
    return mostFrequentLetters;
  }
}

package khu.rbecs.functional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main3 {
    // StreamAPI

    public static void main(String... args) {
        // terminal:
        // reduce
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        int sum = integerStream
//                .parallel()
//                .reduce(0, (acc, el) -> acc + el);
//                .reduce(1, (acc, el) -> acc * el);
//                .reduce(Integer.MIN_VALUE, (acc, el) -> Math.max(acc, el));
                .reduce(Integer.MAX_VALUE, (acc, el) -> Math.min(acc, el));
        String res = Stream.of(1, 2, 3, 4, 5, 6)
                .parallel()
                .reduce("", (acc, el) -> acc + el, String::concat);
        System.out.println("sum = " + sum);
        System.out.println("res = " + res);
    }

    public static void main8(String... args) {
        // terminal:
        // forEach(), findFirst/findAny
        // toList
        var list = Stream.of("Alice")
                .collect(Collectors.toList());
//                .toList();
        System.out.println(list);
        list.add("Bob");
        System.out.println(list);
    }

    public static void main7(String... args) {
        // distinct/sorted
        var rnd = new Random();
        rnd.ints(1, 7)
//                .filter(i -> i > 0)
                .distinct()
                .limit(5)
                .sorted()
                .forEach(System.out::println);
    }

    public static void main6(String... args) {
        // filter/map
        IntStream.rangeClosed(1, 4)
                .flatMap(i -> {
                    if (i % 3 == 2) {
                        return null;
                    } else {
                        return IntStream.rangeClosed(1, i);
                    }
                })
                .forEach(System.out::println);
    }


    public static void main5(String... args) {
        IntStream.range(0, 5).forEach(i ->
                System.out.println(i + "^2 = " + (i * i)));
        IntStream.rangeClosed(0, 5).forEach(i ->
                System.out.println(i + "^2 = " + (i * i)));
        System.out.println("***");
        IntStream.rangeClosed(0, 10)
                .mapToDouble(n -> 0.1 * n)
                .forEach(System.out::println);
        System.out.println("***");
        //                .limit(11)
        DoubleStream.iterate(0, d -> d <= 1.05, d -> d + 0.1)
                .forEach(System.out::println);
    }

    public static void main4(String... args) {
        Stream.iterate(1, n -> n * 2)
                .takeWhile(n -> n < 1_000)
                .forEach(System.out::println);
        System.out.println("***");
        Stream.iterate(1, n -> n < 1_000, n -> n * 3)
                .forEach(System.out::println);
    }

    public static void main3(String... args) {
        // sources
        var rnd = new Random();
        Stream.generate(() -> rnd.nextInt(100))
                .limit(10)
                .takeWhile(n -> n < 98)
                .skip(5)
                .dropWhile(n -> n < 30)
                .findFirst()
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("No number found")
                );
    }


    public static void main2(String... args) {
        Stream.of("Alice", "Bob", "Charlie", "John")
                .filter(name -> name.length() > 3)
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    public static void main1(String... args) {
        // sources
        var names = List.of("Alice", "Bob", "Charlie");
        Stream<String> stream1 = names.stream();
        // intermediate operations
        // filter/map
        Stream<String> stream2 = stream1.map(String::toUpperCase);
//        Stream<Integer> stream3 = stream2.map(String::length);
        Stream<String> stream3 = stream2
                .filter(name -> name.length() > 3);
        // terminal operation:
        // reduce
        List<String> list = stream3.toList();
        System.out.println(list);
    }
}

package khu.rbecs.functional;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.*;

public class Main2 {
    public static void main(String... args) {
        Predicate<String> faceControl = s -> s.length() <= 3;
        Supplier<String> nameSupplier = () -> List.of("Bob", "Alice", "Joe").get(new Random().nextInt(3));
        Consumer<String> greeter = System.out::println;
        Function<String, Integer> length1 = String::length;
        ToIntFunction<String> length2 = String::length;

        UnaryOperator<String> upperCase = String::toUpperCase;
        BinaryOperator<String> concat = String::concat;
        BinaryOperator<Integer> add = (a, b) -> a + b;
        IntBinaryOperator add1 = (a, b) -> a + b;
        BinaryOperator<Integer> add2 = Integer::sum;
        IntBinaryOperator add3 = Integer::sum;
    }
}

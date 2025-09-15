package khu.rbecs.functional;

import java.util.List;

public class Main1 {
    public static void main(String... args) {
        var names  = List.of("Joe", "Alice", "Bob");
        names.stream()
                .filter(name -> name.length() <= 3)
                .forEach(System.out::println);
    }
}

@FunctionalInterface
interface I1 {
    void foo();
//    void foo1();
    String toString();
    int hashCode();
    boolean equals(Object obj);

    default void bar() {
        System.out.println("bar");
    }

    private void bar1() {
        System.out.println("bar");
    }
    static void baz() {}
    private static void baz1() {}
}

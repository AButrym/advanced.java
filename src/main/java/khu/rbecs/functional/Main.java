package khu.rbecs.functional;

import java.util.Map;
import java.util.Scanner;

import static java.util.Map.entry;

public class Main {
    static Map<String, Calculator> ops = Map.ofEntries(
            entry("+", (a, b) -> a + b),
            entry("-", (a, b) -> a - b),
            entry("*", (a, b) -> a * b),
            entry("/", (a, b) -> a / b)
    );

    public static void main(String... args) {
        var scan = new Scanner(System.in);
        System.out.print("Enter two numbers with operator in between: ");
        int a = scan.nextInt();
        String op = scan.next();
        int b = scan.nextInt();
        System.out.printf("%d %s %d = %d%n",
                a, op, b, calcRes(a, b, op));
    }

    static int calcRes(int a, int b, String op) {
        return ops.get(op).calc(a, b);
    }

    public static void main2(String... args) {
        Calculator add = (a, b) -> a + b;
        Calculator add1 = Main::add1;
        CalculatorD add2 = Main::add1;
        Calculator sub = (a, b) -> a - b;
        Calculator mult = (a, b) -> a * b;
        Calculator div = (a, b) -> a / b;

        int sum1 = doCalc(10, 20, new Calculator() {
            @Override
            public int calc(int a, int b) {
                return a + b;
            }
        });
        int sum2 = doCalc(10, 20, (a, b) -> a + b);
        int sum3 = doCalc(10, 20, add);
        int sum4 = doCalc(10, 20, (a, b) -> add1(a, b));
        int sum5 = doCalc(10, 20, Main::add1);
        System.out.println("sum = " + sum2);
    }

    static int add1(int a, int b) {
        return a + b;
    }
    static double add1(double a, double b) {
        return a + b;
    }

    static int doCalc(int a, int b, Calculator calc) {
        return calc.calc(a, b);
    }

    public static void main1(String... args) {
        // 1. lambda functions
        // 2. method references
        // 3. functional interfaces, SAM - Single Abstract Method
        // 4. standard library interfaces
        Runnable r = () -> {};
        r.run();
        I fun1 = (title, name) -> "Hello, " + title + " " + name;
        I fun2 = (title, name) -> {
            System.out.println("invoking fun2");
            return "Hi, " + title + " " + name;
        };
        System.out.println(fun1.greet("Mr.", "John"));
        System.out.println(fun2.greet("Ms.", "John"));
    }
}

interface I {
    String greet(String title, String name);
}

@FunctionalInterface
interface Calculator {
    int calc(int a, int b);
}

interface CalculatorD {
    double calc(double a, double b);
}

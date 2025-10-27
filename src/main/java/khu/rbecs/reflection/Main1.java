package khu.rbecs.reflection;

import java.lang.reflect.Method;

public class Main1 {
    public static void main(String... args) throws Exception {
//        B b = new B();
        System.out.println("Start");
        Class<?> clazz1 = B.class;
        System.out.println("after B.class access");
        Class<?> clazz2 = Class.forName("khu.rbecs.reflection.B");
        System.out.println("after Class.forName");

        var ctor1 = clazz2.getDeclaredConstructor();
        var ctor2 = clazz2.getDeclaredConstructor(String.class);
        ctor1.setAccessible(true);
        ctor2.setAccessible(true);

        B b1 = (B) ctor1.newInstance();
        B b2 = (B) ctor2.newInstance("hi");

        Method foo = clazz2.getDeclaredMethod("foo");
        foo.setAccessible(true);
        foo.invoke(b2); // b2.foo()

        System.out.println("End");
    }
}

class B {
    static {
        System.out.println("static block of B");
    }
    private int i = 12;
    private B() {
        System.out.println("B()");
    }
    private B(String s) {
        System.out.println("B(" + s + ")");
    }
    private void foo() {
        System.out.println("foo i = " + i);
    }
}
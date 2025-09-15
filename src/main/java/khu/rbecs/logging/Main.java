package khu.rbecs.logging;


import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String... args) {
        C c = new C()
                .setName("Joe")
                .setAge(20);
        System.out.println(c);
        System.out.println("age = " + c.getAge() + " name = " + c.getName());
    }

    public static void main2(String... args) {
        var name = "Hello World";
        System.out.println(name);
        System.out.println(utf8ToString(name.getBytes()));
        System.out.println(utf8ToString1(name.getBytes()));
    }

    @SneakyThrows
    public static String utf8ToString1(byte[] bytes) {
        return new String(bytes, "UTF-8");
    }

    public static String utf8ToString(byte[] bytes) {
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // never should get here
            return "";
        }
    }

    public static void main1(String[] args) {
        A a = new A("Joe", "Doe");
        A a1 = new A("Alice");
        A a2 = new A();
        System.out.println(a.getFirstName());
        System.out.println(a.getLastName());
        a.setLastName("Smith");
        System.out.println(a.getLastName());
        System.out.println(a);
        a.foo("Hello");
//        a.foo(null);

        B b = new B("Joe", "Doe");
        System.out.println(b);
        b = new B(b.firstName(), "Smith");
        System.out.println(b);
        b = b.withLastName("SMITH");
        System.out.println(b);
        b = B.builder()
                .firstName("Joe")
                .lastName("Doe").build();
        System.out.println(b);
    }
}

@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
class A {
    @Setter
    @NonNull
    String firstName;

    @Setter
    @ToString.Exclude
    String lastName;

    void foo(@NonNull String par) {
        System.out.println("par = " + par);
    }
}

@Builder
@With
record B(String firstName, String lastName) {
}

@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(/*fluent = true, */chain = true)
@Getter @Setter
class C {
   String name;
   int age;
}
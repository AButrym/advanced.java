package khu.rbecs.annotations;

import lombok.Getter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@MyAnnotation({"MyAnnotation", "adfgafsg"})
public class Main1 {
    @MyAnnotation(value = "MyAnnotation")
    public static void main(@MyAnnotation(value = "MyAnnotation") String... args) {
        @MyAnnotation(value = "MyAnnotation") int i = 19;
    }
}

record MyRecord(@MyAnnotation(value = {"MyAnnotation", "agfs"}, name = "A") String name) {}

@MyAnnotation(value = "MyAnnotation",
        c = MyAnnotation.class,
        a2 = @Annotation1(""),
        o1 = 2
)
@Target({ElementType.ANNOTATION_TYPE,
        ElementType.TYPE,
        ElementType.METHOD,
        ElementType.PARAMETER,
        ElementType.LOCAL_VARIABLE,
        ElementType.RECORD_COMPONENT,
})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String[] value();
    String name() default "default";
    int o() default 1;
    int[] o1() default {1, 2, 3};
    E e() default E.E1;
    Class<?> c() default String.class;
    Annotation1 a2() default @Annotation1("a2");
    Annotation1[] a1() default {};
}

@interface Annotation1 {
    String value();
}

enum E {E1;}

class A {
    void foo() {
        System.out.println("foo");
    }
}

@Getter
class B extends A {
    @Override
    void foo() {
        System.out.println("bar");
    }
}
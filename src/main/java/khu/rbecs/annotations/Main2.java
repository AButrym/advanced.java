package khu.rbecs.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnn1 {
    String value();
}

@Repeatable(MyAnn.MyAnnAgg.class)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnn {
    String value();

    @Retention(RetentionPolicy.RUNTIME)
    @interface MyAnnAgg {
        MyAnn[] value();
    }
}

public class Main2 {
    public static void main(String... args) {
        Class<?> clazz1 = A1.class;
        A1 a = new A1();
        Class<?> clazz2 = a.getClass();
        Annotation[] anns = clazz1.getDeclaredAnnotations();
        for (Annotation ann : anns) {
            System.out.println(ann.annotationType().getSimpleName() + " = " + ann.toString());
        }
        MyAnn1 myAnn1s = clazz2.getDeclaredAnnotation(MyAnn1.class);
//        clazz2.isAnnotationPresent(MyAnn1.class);
        if (myAnn1s != null) {
            System.out.println("MyAnn1 = " + myAnn1s.value());
        }
    }
}

@MyAnn1("MyAnn1Class")
@MyAnn(value = "MyAnnClass1")
@MyAnn(value = "MyAnnClass2")
class A1 {
    private int i = 12;

    @MyAnn(value = "MyAnnMethod")
    public void method1() {
        System.out.println("method1 i = " + i);
    }
}

package simple;

import annotations.PrintHashesBefore;
import annotations.Show;

/**
 * Класс для проверки работы Reflection API
 */
public class MyClass {
    static {
        System.out.println("Static initialization");
    }

    @PrintHashesBefore(5)
    @Show(name = "Поле1", spaces = 2)
    public int publicIntField;
    @Show(spaces = 10)
    public String publicStringField;
    @Show("value")
    int packageLocalIntField = 0;

    private int privateIntField = 100;

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.method1();
    }

    public String method1() {
        //String s;
        //System.out.println("s = " + s);
        System.out.println("publicStringField = " + publicStringField);
        System.out.println("MyClass.method1");
        return "MyClass.method1";
    }

    public String method2(int x) {
        System.out.println("MyClass.method2: x = " + x);
        return "MyClass.method2";
    }

    public String method3() {
        System.out.println("MyClass.method3");
        return "MyClass.method3";
    }
}

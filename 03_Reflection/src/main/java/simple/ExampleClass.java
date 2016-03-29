package simple;

/**
 * Пример класса для загрузки по имени класса
 */
public class ExampleClass {
    static {
        System.out.println("Class loaded");
    }

    public ExampleClass() {
        System.out.println("Constructor ExampleClass");
    }

    public void myMethod() {
        System.out.println("ExampleClass.myMethod");
    }
}

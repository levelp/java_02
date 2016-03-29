package fields;

import simple.MyClass;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Ввывод всех полей класса
 */
public class ShowAllFields {
    public static void main(String[] args) {
        System.out.println("=== Значения по-умочанию ===");
        showAllFields(new MyClass());

        System.out.println("=== Модифицировали поля ===");
        MyClass myClass = new MyClass();
        myClass.publicIntField = 10;
        myClass.publicStringField = "Test";

        showAllFields(myClass);
    }

    private static void showAllFields(Object obj) {
        // Получаем метаинформацию о классе
        Class c = obj.getClass();
        // Получаем все поля объявленные в классе
        // кроме унаследованных
        for (Field field : c.getDeclaredFields()) {
            int modifiers = 0;
            try {
                modifiers = field.getModifiers();
                if ((modifiers & Modifier.PRIVATE) != 0) {
                    System.out.print("private ");
                    field.setAccessible(true);
                }
                if ((modifiers & Modifier.PUBLIC) != 0)
                    System.out.print("public ");
                if (modifiers == 0) {
                    System.out.print("package local ");
                    field.setAccessible(true);
                }
                System.out.println(field.getName() + " = "
                        + field.get(obj));
                // field.set(obj, newValue);
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage() + " " +
                        field.getName());
                e.printStackTrace();
            }
        }
    }
}

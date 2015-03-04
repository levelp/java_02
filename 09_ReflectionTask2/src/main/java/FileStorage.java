import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 *
 */
public class FileStorage {
    /**
     * Сохранение объекта в текстовый файл
     *
     * @param obj      объект
     * @param fileName имя файла
     */
    public static void save(Object obj, String fileName)
            throws FileNotFoundException, IllegalAccessException {
        // TODO: реализовать
        PrintWriter writer = new PrintWriter(
                fileName
        );
        // Получаем объект-класс того объекта
        // который надо сохранить
        Class c = obj.getClass();
        // Печатаем имя класса
        writer.println(c.getName());
        // Сохраняем все поля: c.getDeclaredFields()
        for (Field field : c.getDeclaredFields()) {
            writer.print(field.getName() + " = ");
            // Получаем доступ к
            // private/protected/package local
            //-->
            field.setAccessible(true);
            //<--
            writer.println(field.get(obj));
        }
        writer.close();
    }

    /**
     * Загрузка объекта из файла
     *
     * @param fileName имя файла
     * @return объект
     */
    public static Object load(String fileName) throws FileNotFoundException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Scanner scanner = new Scanner(new File(fileName));
        // Считываем имя класса
        String className = scanner.nextLine();
        // Создаем класс по имени
        Class c = Class.forName(className);
        // Создаём экземпляр класса
        Object instance = c.newInstance();
        // Инициализируем поля
        for (Field field : c.getDeclaredFields()) {
            // Считываем строчку
            // scanner.next() читает символы
            // до разделителя (табуляции, пробела,
            // перевода строки)
            String name = scanner.next();
            assert name.equals(field.getName());
            scanner.next(); // "="
            //if (field.getType().equals(Double.TYPE)) {
            //    field.set(instance, scanner.nextDouble());
            //}
            String value = scanner.next();
            scanner.nextLine();

            System.out.println("" + field.getType());

            field.setAccessible(true);

            // Как присвоить значение полю произвольного типа?
            // ----------------------------------------------
            // Способ 1 - "много if":
            //-->
            if (field.getType().equals(Double.TYPE))
                field.set(instance, Double.valueOf(value));
            if (field.getType().equals(Integer.TYPE))
                field.set(instance, Integer.valueOf(value));
            //<--
            // Способ 2 - используем Reflection
            if (field.getType().isPrimitive()) {
                // Получаем класс-обёртку
                //-->
                String typeName = field.getType().getName();
                System.out.println("typeName = " + typeName);

                String fieldClassName;
                if (field.getType().equals(Integer.TYPE))
                    fieldClassName = Integer.class.getName();
                else if (field.getType().equals(Character.TYPE))
                    fieldClassName = Character.class.getName();
                else {
                    String firstLetter = "" + typeName.charAt(0);
                    fieldClassName = "java.lang." + firstLetter.toUpperCase() +
                            typeName.substring(1);
                    System.out.println("fieldClassName = " + fieldClassName);
                }
                //<--

                Class fieldClass = Class.forName(fieldClassName);
                Method methodValueOf =
                        fieldClass.getMethod("valueOf",
                                field.getType().equals(Character.TYPE) ?
                                        Character.TYPE : String.class);
                // null - для статических методов
                Object fieldValue = methodValueOf.invoke(null,
                        field.getType().equals(Character.TYPE) ?
                                value.charAt(0) : value);
                System.out.println("fieldValue = " + fieldValue);
                field.set(instance, fieldValue);
            }
        }
        scanner.close();
        return instance;
    }
}

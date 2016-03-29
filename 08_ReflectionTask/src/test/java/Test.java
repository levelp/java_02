import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Тестирование работы функции showAllData
 */
public class Test {
    public static void main(String[] args) throws Exception {
        showAllData("A");
        showAllData("B");
    }

    static void showAllData(String className) throws Exception {
        //-->
        System.out.println("Show class: " + className);

        // Получаю экземпляр класса
        Class c = Class.forName(className);

        // Создаём объект (экземпляр класса className)
        Object obj = c.newInstance();

        // Получаем массив полей и выводим значения
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            Object value = field.get(obj);
            System.out.println(field.getName() + " = " + value);
        }

        // Вызываем все методы
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            method.invoke(obj);
        }
        //<--
    }
}

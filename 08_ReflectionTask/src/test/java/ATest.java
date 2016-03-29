import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

// Reflection API
// --------------
public class ATest {


    public static void main(String[] args) throws Exception {
        //-->
        // Загружаем класс A
        Class cls = Class.forName("A");
        // Создаём экземпляр класса A
        Object obj = cls.newInstance();
        // Получаем метод по имени
        Method method = cls.getMethod("newMethod");
        // Вызываем метод и получаем значение
        Integer integer = (Integer) method.invoke(obj);
        System.out.println("integer = " + integer);

        // Вызываем все статические методы
        for (Method m : cls.getDeclaredMethods()) {
            if ((m.getModifiers() & Modifier.STATIC) == 0)
                continue;
            m.invoke(null);
        }
        //<--
    }
}

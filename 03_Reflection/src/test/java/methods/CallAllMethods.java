package methods;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Вызво всех методов класса
 */
public class CallAllMethods {
    public static void main(String[] args) throws Exception {
        Class c = Class.forName("simple.MyClass");
        // Создаю экземпляр класса
        Object obj = c.newInstance();

        for (Method method : c.getDeclaredMethods()) {
            if ((method.getModifiers() & Modifier.STATIC) != 0) {
                System.out.println("Skip static method: " + method.getName());
                continue;
            }
            if (method.getParameterTypes().length == 1) {
                method.invoke(obj, 2);
                // вызов метода с 3 параметрами
                //method.invoke(obj, 1, 2, 3);
            } else {
                method.invoke(obj);
            }
        }
    }
}

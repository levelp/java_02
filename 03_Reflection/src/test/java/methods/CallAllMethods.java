package methods;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

import static java.lang.reflect.Modifier.PUBLIC;
import static java.lang.reflect.Modifier.STATIC;

/**
 * Вызво всех методов класса
 */
public class CallAllMethods extends Assert {
    public static void main(String[] args) throws Exception {
        Class c = Class.forName("simple.MyClass");
        // Создаю экземпляр класса
        Object obj = c.newInstance();

        for (Method method : c.getDeclaredMethods()) {
            if ((method.getModifiers() & STATIC) != 0) {
                System.out.println("Skip static method: " + method.getName());
                continue;
            }
            if (method.getParameterTypes().length == 1) {
                method.invoke(obj, 2);
                // Вызов статического метода
                //method.invoke(null, 2);
                // вызов метода с 3 параметрами
                //method.invoke(obj, 1, 2, 3);
            } else {
                method.invoke(obj);
            }
        }
    }

    @Test
    public void testCallAllMethods() throws Exception {
        Class c = Class.forName("simple.MyClass");
        assertEquals("simple.MyClass", c.getName());
        assertEquals("MyClass", c.getSimpleName());
        // Создаю экземпляр класса
        Object obj = c.newInstance();
        assertNotNull(obj);

        Method[] methods = c.getDeclaredMethods();
        assertEquals(4, methods.length);
        Method m0 = methods[0];
        assertEquals("main", m0.getName());
        assertEquals(STATIC | PUBLIC, m0.getModifiers());
        assertEquals(1, m0.getParameterTypes().length);
        assertEquals("method1", methods[1].getName());
        assertEquals(PUBLIC, methods[1].getModifiers());
        assertEquals(0, methods[1].getParameterTypes().length);
        assertEquals("method2", methods[2].getName());
        assertEquals(PUBLIC, methods[2].getModifiers());
        assertEquals(1, methods[2].getParameterTypes().length);
        assertEquals("method3", methods[3].getName());
        assertEquals(PUBLIC, methods[3].getModifiers());
        assertEquals(0, methods[3].getParameterTypes().length);

        for (Method method : c.getDeclaredMethods()) {
            if ((method.getModifiers() & STATIC) != 0) {
                System.out.println("Skip static method: " + method.getName());
                continue;
            }
            if (method.getParameterTypes().length == 1) {
                method.invoke(obj, 2);
                // Вызов статического метода
                //method.invoke(null, 2);
                // вызов метода с 3 параметрами
                //method.invoke(obj, 1, 2, 3);
            } else {
                method.invoke(obj);
            }
        }
    }
}

package classes;

import org.junit.Test;
import simple.ExampleClass;
import simple.MyClass;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Java Reflection API Test
 */
public class ReflectionTest {

    /**
     * Получаем класс по объекту
     *
     * @throws ClassNotFoundException
     */
    @Test
    public void getObjectClass() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c = "foo".getClass();
        assertEquals("java.lang.String", c.getName());
        Integer ii = 2;
        assertEquals("java.lang.Integer", ii.getClass().getName());

        Class strClass = Class.forName("java.lang.String");
        // String newStr = new String();
        String newStr = (String) strClass.newInstance();

        Set<String> s = new HashSet<String>();
        s.add("test1");
        Class c1 = s.getClass();
        assertEquals("java.util.HashSet", c1.getName());

        showClass(1);
        showClass(1.1);
        showClass(1.2f);
        showClass(true);
        showClass(new MyClass());
    }

    private void showClass(Object object) {
        System.out.println("object = " + object.toString() +
                " -> Class = " + object.getClass());
    }


    @Test
    public void createClass() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c = "foo".getClass();
        assertEquals("java.lang.String", c.getName());
        // Создание нового экзепляра
        String newString = (String) c.newInstance(); // "Test"
        assertEquals("", newString);
        assertEquals(0, newString.length());

        // [I
        Class cDoubleArray = Class.forName("[D");
        assertEquals("double[]", cDoubleArray.getCanonicalName());

        // Загрузка класса
        Class simpleClass = Class.forName("simple.ExampleClass");
        System.out.println("Before>>>");
        // Создаём экземпляр класса
        ExampleClass obj = (ExampleClass) simpleClass.newInstance();
        System.out.println("<<<After");
        obj.myMethod();
        //double[] obj = (double[]) cDoubleArray.newInstance();

        Class cStringArray = Class.forName("[[Ljava.lang.String;");
        assertEquals("java.lang.String[][]", cStringArray.getCanonicalName());
        //Object string = cStringArray.newInstance();
    }
}

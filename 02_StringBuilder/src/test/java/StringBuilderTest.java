import org.junit.Assert;
import org.junit.Test;

/// Маленькие хитрости Java. StringBuilder
/// --------------------------------------
/// Тест производительности.
/**
 * "Склеивание" строк при помощи класса StringBuilder.
 * <p/>
 * Для оптимизации работы виртуальной машины, разработчики языка
 * Java приняли решение сделать класс String
 * неизменяемым (immutable).
 * Это в действительности упрощает и ускоряет многие операции со строками.
 * <p/>
 * Но, кроме плюсов, это накладывает ещё и определённые ограничения.
 * Операция конкатенации (склейки) строк - медленная.
 * Когда мы хотим склеить две строки, str1 и str2, мы обычно пишем просто String str3 = str1 + str2.
 * Результат верный, все довольны. Но при каждой операции создаётся новая строка.
 * Если строк много и они длинные, то используется много памяти и программа работает медленно.
 */
public class StringBuilderTest extends Assert {

    /// Демонстрация работы методов StringBuilder: append, insert, delete
    @Test
    public void testAppendInsertDelete() {
        //-->
        StringBuilder s = new StringBuilder();

        s.append("boolean: ");
        s.append(true);
        System.out.println(s);
        assertEquals("boolean: true", s.toString());

        s.append("  double: ");
        s.append(1.0);
        System.out.println(s);
        assertEquals("boolean: true  double: 1.0", s.toString());

        // Вставляем подстроку в позицию 13
        s.insert(13, ",");
        System.out.println(s);
        assertEquals("boolean: true,  double: 1.0", s.toString());

        // Удаляем кусок
        s.delete(0, 9);
        System.out.println(s);
        assertEquals("true,  double: 1.0", s.toString());

        s = new StringBuilder();

        // Цепочка действий
        s.append("boolean: ")
                .append(true)
                .append(" double: ")
                .append(1.2)
                .insert(13, ",");
        System.out.println(s);
        assertEquals("boolean: true, double: 1.2", s.toString());

        s.append("  "); // Отступ
        Point point = new Point(2, 3);
        s.append(point);
        assertEquals("boolean: true, double: 1.2  Point{x=2.0, y=3.0}", s.toString());
        //<--
    }
}

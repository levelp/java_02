import org.junit.Assert;
import org.junit.Test;

/**
 * Метод hashCode()
 * Примеры реализации hashCode() в стандартной библиотеке
 */
public class HashCodeTest extends Assert {

    /**
     * Для целых чисел hashCode обычно равен самому числу
     */
    @Test
    public void testInteger() {
        assertEquals(1, Integer.hashCode(1));
        assertEquals(101, Integer.hashCode(101));
    }

    /**
     * Для long:  старшая_часть ^ младшая_часть
     */
    @Test
    public void testLong() {
        assertEquals(1, Long.hashCode(1));
        assertEquals(101, Long.hashCode(101));
        assertEquals(1 ^ 2, Long.hashCode((1L << 32) + 2));
    }

    /**
     * Строки: s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
     */
    @Test
    public void testString() {
        assertEquals((int) '1', "1".hashCode());
        assertEquals((int) 'X', "X".hashCode());
        assertEquals(((int) '1' * 31) + (int) '2', "12".hashCode());
        assertEquals((((int) '1' * 31) + (int) '2') * 31 + '3', "123".hashCode());
    }
}

import org.junit.Assert;
import org.junit.Test;

/**
 * Переполнение в целых числах
 */
public class OverflowTest extends Assert {
    @Test
    public void test() {
        System.out.println("Integer.MAX_VALUE+1 = " +
                (Integer.MAX_VALUE + 1));
        byte b1 = (byte) (Byte.MAX_VALUE + 1);
        assertEquals(Byte.MIN_VALUE, b1);
        short s1 = (short) (Short.MAX_VALUE + 1);
        assertEquals(Short.MIN_VALUE, s1);
        assertEquals(Integer.MIN_VALUE, Integer.MAX_VALUE + 1);
        assertEquals(Long.MIN_VALUE, Long.MAX_VALUE + 1);
    }
}

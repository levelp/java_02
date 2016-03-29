import org.junit.AfterClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Сравнение производительности String vs StringBuilder
 * StringBuilder - если пользуемся из одного потока
 * StringBuffer - если из нескольких потоков
 */
public class StringVsStringBuilder {
    /**
     * Количество операций
     */
    static final int INTS = 50000;

    // Результат для строки
    static String resString;
    // Результат для StringBuilder
    static String resStringBuilder;
    // Результат для StringBuffer
    static String resStringBuffer;

    /**
     * Сравнение результатов (после всех тестов)
     */
    @AfterClass
    static public void compareResults() {
        if (resString != null && resStringBuilder != null) {
            assertEquals(resString, resStringBuilder);
        }
        if (resString != null && resStringBuffer != null) {
            assertEquals(resString, resStringBuffer);
        }
    }

    @Test
    //@Ignore
    public void testString() {
        String s = "";
        for (int i = 0; i < INTS; ++i)
            s += i;
        resString = s;
    }

    @Test
    public void testStringBuilder() {
        //Runtime.getRuntime().gc();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < INTS; ++i)
            s.append(i);
        resStringBuilder = s.toString();
    }

    @Test
    public void testStringBuffer() {
        //Runtime.getRuntime().gc();
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < INTS; ++i)
            s.append(i);
        resStringBuffer = s.toString();
    }
}

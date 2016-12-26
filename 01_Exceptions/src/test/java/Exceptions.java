import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Исключения
 */
public class Exceptions {
    Random random = new Random();

    @Test
    public void testRuntimeException() {
        try {
            ///....

            f(10);

            // ...
        } catch (MyRuntimeException ex) {
            ex.printStackTrace();
            assertEquals("Моё исключение", ex.name);
            assertEquals(23, ex.i);
            assertEquals(2.3, ex.d, 1e-15);
        } catch (Exception ex) {
            //throw ex;
            //ex.printStackTrace();
        }
    }

    private void f(int i) {
        if (i == 0) {
            throw new MyRuntimeException("Моё исключение", 23, 2.3);
        }
        f(i - 1);
    }

    @Test
    public void testException() {
        try {
            ///....

            g(10);

            // ...
        } catch (MyException ex) {
            assertEquals("Моё исключение", ex.name);
            assertEquals(23, ex.i);
            assertEquals(2.3, ex.d, 1e-15);
        } catch (Exception ex) {

        }
    }

    private void g(int i) throws MyException {
        if (i == 0)
            throw new MyException("Моё исключение", 23, 2.3);
        g(i - 1);
    }

    /**
     * Переполнение стека
     */
    @Test(expected = StackOverflowError.class)
    public void testStackOverflow() {
        rec();
    }

    private void rec() {
        for (int i = 0; i < 10; i++) {
            rec();
        }
    }
}

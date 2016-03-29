/**
 * Exception
 * RuntimeException
 */
public class RuntimeExceptionDemo {

    public static void main(String[] args) throws IllegalArgumentException {
        g(10);
    }

    private static void g(int i) {
        if (i == 0)
            throw new MyRuntimeException("i == 0", 1, 1.0);
        g(i - 1);
    }
}

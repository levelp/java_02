
/**
 * Exception
 * RuntimeException
 */
public class ExceptionDemo {

    public static void main(String[] args) throws Exception {
        //System.setOut(new PrintStream("my.log"));
        System.out.println("Запуск программы");
        f(10);
    }

    private static void f(int i) throws IllegalArgumentException {
        //throw new MyRuntimeException("xx", 2, 1);
        if (i == 0)
            throw new MyRuntimeException("11", 1, 2);
        if (i < 0)
            throw new IllegalArgumentException("i == 0");
        f(i - 1);
    }
}

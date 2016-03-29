/**
 *
 */
public class MyRuntimeException extends RuntimeException {

    final double d;
    final String name;
    final int i;

    public MyRuntimeException(String name, int i, double d) {
        super();
        this.name = name;
        this.i = i;
        this.d = d;
    }
}

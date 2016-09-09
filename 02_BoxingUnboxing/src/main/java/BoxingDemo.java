/**
 *
 */
public class BoxingDemo {
    public static void main(String[] args) {
        // Boxing
        int i = 10;
        Integer i2 = i;
        Integer nullInt = null;
        int i3 = nullInt; // Unboxing
        intMethod(i);
        intMethod(i2);

        // Unboxing
        Integer ii = 20;
        int ii2 = ii;
        intMethod2(ii);
        intMethod2(ii2);
    }

    private static void intMethod2(int ii) {
        System.out.println("ii = " + ii);
    }

    // immutable
    private static void intMethod(Integer i) {
        System.out.println("i = " + i);
    }
}

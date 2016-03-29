import org.junit.Assert;
import org.junit.Test;

public class Main extends Assert {

    public static void main(String[] args) {
        chain();
        chainWithBrackets();
    }

    private static void chain() {
        int a = 11, b = 3;
        a ^= b ^= a ^= b;
        System.out.println("a = " + a + " b = " + b); // Неверный ответ: a = 0 b = 11
    }

    static volatile int a = 11, b = 3;

    private static void chainWithBrackets() {
        //x = 2;
        //a = x+x;
        //b = 2*x + x + x;
        a ^= (b ^= (a ^= b));
        System.out.println("a = " + a + " b = " + b); // Неверный ответ: a = 0 b = 11
    }

    @Test
    public void chainAsExpected() {
        int a = 11, b = 3;

        //            a              b
        a ^= b; //  a ^ b            b
        b ^= a; //  a ^ b        b ^ a ^ b = a
        a ^= b; //  a ^ b ^ a = b    a

        assertEquals(3, a);
        assertEquals(11, b);

        System.out.println("a = " + a + " b = " + b);
    }

}

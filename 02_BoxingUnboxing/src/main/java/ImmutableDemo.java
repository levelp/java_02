/**
 *
 */
public class ImmutableDemo {

    public static void main(String[] args) {
        Integer v = 1;
        MyInteger myValue = new MyInteger(1);
        System.out.println("v (before) = " + v);
        System.out.println("myValue = " + myValue);
        f(v);
        f1(myValue);
        System.out.println("v (after) = " + v);
        System.out.println("myValue = " + myValue);
    }

    private static void f1(MyInteger v1) {
        v1.value++;
        System.out.println("f1: myValue = " + v1);
    }

    private static void f(Integer v) {
        v++;
        System.out.println("v (in f) = " + v);
    }

    static class MyInteger {
        int value;

        public MyInteger(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return Integer.toString(value);
        }
    }
}

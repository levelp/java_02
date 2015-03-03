import static org.junit.Assert.assertEquals;

/**
 * Статический конструктор
 */
public class StaticConstructor {

    public static void main(String[] args) {
        MyClass a = new MyClass(), b = new MyClass();

    }

    static class MyClass {
        // Инициализация выполняется последовательно
        // Строчка за строчкой
        // Сначала всё со словом static
        // Потом инициализация не static переменных
        static int x = 1;
        static int counter = 10;
        static int a = counter * 2;
        static int cnt2 = a + 10;
        static int countInstances = 0;
        static {
            //counter = 0;
            x++;
            assertEquals(2, x);
            System.out.println("counter = " + counter);
            System.out.println("a = " + a);
            for (int i = 0; i < 10; i++)
                a += i;
            System.out.println("a = " + a);
        }

        static int x2 = x++;

        static {
            x2++;
            assertEquals(3, x);
            assertEquals(3, x2);
        }
        int id = ++countInstances;
        String name = "Test " + cnt2;

        public MyClass() {
            id = ++counter;
            System.out.println("id = " + id + "  " + name);
            System.out.println("cnt2 = " + cnt2);
        }
    }
}


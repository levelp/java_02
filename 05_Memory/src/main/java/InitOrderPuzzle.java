/**
 * Created by ilia on 5/23/15.
 */
public class InitOrderPuzzle {
    static int i = 100;
    static int j;

    static {
        System.out.println("First static block");
    }

    static {
        System.out.println("Last static initialization");
    }

    int k = 10;

    {
        // Блок инициализации (выполняется перед
        // конструктором)
        j = i + k;
        System.out.println("Block");
    }

    public InitOrderPuzzle() {
        System.out.println("Constructor");
    }

    public static void main(String[] args) {
        System.out.println("InitOrderPuzzle.main");
        new InitOrderPuzzle().method();
    }

    public void method() {
        System.out.println("Method");
    }
}

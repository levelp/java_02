/**
 * Created by ilia on 5/23/15.
 */
public class InitOrderPuzzle {
    static {
        System.out.println("Static block");
    }

    {
        System.out.println("Block");
    }

    public InitOrderPuzzle() {
        System.out.println("Constructor");
    }

    public void method() {
        System.out.println("Method");
    }

    public static void main(String[] args) {
        new InitOrderPuzzle().method();
    }
}

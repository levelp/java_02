/**
 * Created by potapov.ov on 16.09.2015.
 */
public class Main {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        stack.push("c");
        stack.push("b");
        stack.push("a");

        System.out.print("show stack: ");
        while (!stack.isEmpty())
            System.out.print(stack.pop()+"; ");

        System.out.println();

        Queue <Integer> queue = new Queue<Integer>();

        for (int i = 1; i<6; i++) queue.put(i);

        System.out.print("show queue: ");
        while (!queue.isEmpty())
            System.out.print(queue.get()+ "; ");

    }
}

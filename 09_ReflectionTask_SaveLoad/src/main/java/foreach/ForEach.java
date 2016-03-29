package foreach;

/**
 * Цикл foreach
 */
public class ForEach {

    public static void main(String[] args) {
        int[] a = {1, 2, 4, 6};
        for (int i : a) {
            System.out.println("i = " + i);
            i = 11;
            //a[0] = 10;
        }
        // Если я перебираю элементы массива используя индексы
        // то я могу модифицировать элементы массива
        for (int i = 0; i < a.length; i++) {
            System.out.println("a[" + i + "] = " + a[i]);
            a[i]++;
            System.out.println("a[" + i + "] = " + a[i]);
        }
    }
}

package levelp;

/**
 * Разложение на слагаемые
 */
public class Decomposition {
    final int[] res;
    int N;

    public Decomposition(int N) {
        this.N = N;
        res = new int[N];
        rec(N, N, 0);
    }

    /**
     * @param sum      Оставшаяся сумма
     * @param prevItem Предыдушее слагаемое (максимум для этого слагаемого)
     * @param index    Индекс следующего слагаемого
     */
    void rec(int sum, int prevItem, int index) {
        // Если сумма кончилась
        if (sum == 0) {
            System.out.print(N + " = " + res[0]);
            for (int i = 1; i < index; i++)
                System.out.print(" + " + res[i]);
            System.out.println();
            return;
        }
        // x - очередное слагаемое
        for (int x = Math.min(sum, prevItem); x >= 1; x--) {
            // Записываем очередное слагаемое
            res[index] = x;
            // Вызываем рекурсивно себя изменяя параметры
            rec(sum - x, x, index + 1);
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 9; i++) {
            new Decomposition(i);
        }
    }
}

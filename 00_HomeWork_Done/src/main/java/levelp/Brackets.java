package levelp;

/**
 * Скобочки
 */
public class Brackets {


    public static void main(String[] args) {
        for (int i = 1; i <= 6; i++) {
            genBrackets(i);
        }
    }

    /**
     * Генерация правильных скобочных последовательностей
     * (())
     * ()()
     *
     * @param N Количество пар скобок
     */
    static void genBrackets(int N) {
        System.out.println("N = " + N);
        genBrackets(N, 0, 0, "");
    }

    /**
     * @param N      Сколько всего надо поставить пар скобок
     *               N >= 1
     * @param open   Открывающих скобок поставлено
     * @param close  Закрывающих скобок поставлено
     * @param result Строчка со скобками
     */
    static void genBrackets(int N, int open, int close, String result) {
        if (N < 1)
            throw new IllegalArgumentException("N >= 1");
        if (open > N)
            throw new IllegalArgumentException("open = " + open +
                    " > N = " + N);
        // Если все скобки уже поставлены
        if (open == N && close == N) {
            System.out.println(result);
            return;
        }
        // Можем ли поставить открывающую скобку?
        if (open < N) {
            genBrackets(N, open + 1, close, result + "(");
        }
        // Можем ли поставить закрывающую скобку?
        if (close < open) { // Открывающих меньше чем закрывающих
            genBrackets(N, open, close + 1, result + ")");
        }
    }
}



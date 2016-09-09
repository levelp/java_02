package levelp;

/**
 * Скобочки
 */
public class Brackets {
    /**
     * Тестирование
     */
    public static void main(String[] args) {
        for (int N = 1; N <= 6; N++) {
            genBrackets(N);
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
        genBrackets(N, "", 0, 0);
    }

    /**
     * @param N      Сколько всего надо поставить пар скобок
     *               N >= 1
     * @param result Строчка со скобками
     * @param open   Открывающих скобок поставлено
     * @param close  Закрывающих скобок поставлено
     */
    static void genBrackets(int N,
                            String result,
                            int open, int close) {
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
        // Можем ли поставить (дописать в конец)
        // открывающую скобку?
        if (open < N) {
            genBrackets(N, result + "(", open + 1, close);
        }
        // Можем ли поставить закрывающую скобку?
        if (close < open) { // Открывающих меньше чем закрывающих
            genBrackets(N, result + ")", open, close + 1);
        }
    }
}



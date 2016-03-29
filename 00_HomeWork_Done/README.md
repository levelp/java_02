<!-- doc.py -->

Исключения в Java
----------------
Бывают двух видов:
* Наследники от класса **Exception**
надо указывать throws в цепочке вызовов.
* Наследники от класса **RuntimeException**
не надо указывать throws.
``` java
// Любое значение X
public class AnyXException extends RuntimeException {
}
```

[src/main/java/levelp/AnyXException.java](src/main/java/levelp/AnyXException.java)

Если все скобки уже поставлены
Можем ли поставить (дописать в конец)
открывающую скобку?
Можем ли поставить закрывающую скобку?
[src/main/java/levelp/Brackets.java](src/main/java/levelp/Brackets.java)

Если сумма кончилась
x - очередное слагаемое
Записываем очередное слагаемое
Вызываем рекурсивно себя изменяя параметры
[src/main/java/levelp/Decomposition.java](src/main/java/levelp/Decomposition.java)

Практика: Решение квадратного уравнения
``` java
    // Точность вычислений
    public static final double DELTA = 0.000000001;

    /**
     * Решение квадратного уравнения: ax^2 + bx + c = 0
     *
     * @param a коэффициент уравнения a
     * @param b коэффициент уравнения b
     * @param c коэффициент уравнения c
     * @return массив решений уравнения
     */
    public static double[] solve(double a, double b, double c) throws AnyXException {
        // Обработка вырожденных случаев
        if (abs(a) < DELTA) {
            if (abs(b) < DELTA) {
                if (abs(c) < DELTA)
                    throw new AnyXException();
                return new double[]{};
            }
            return new double[]{-c / b};
        }

        // Вычислим дискриминант
        double D = pow(b, 2) - 4 * a * c;

        // Если D = 0 => одно решение
        if (abs(D) < DELTA) {
            return new double[]{-b / (2 * a)};
        }

        // Если D > 0 => 2 решения
        if (D > 0) {
            return new double[]{
                    (-b - sqrt(D)) / (2 * a),
                    (-b + sqrt(D)) / (2 * a)
            };
        }
        // Нет решений
        return new double[]{};
    }
```

[src/main/java/levelp/SquareEq.java](src/main/java/levelp/SquareEq.java)

Первый (самый простой) тест
``` java
    @Test
    public void testSimple() {
        // assertEquals( ожидаемое_значение, вычисленное значение )
        //       сообщение_если_значения_не_равны, ..., ...
        // a*x^2 + b*x + c = 0
        assertArrayEquals("x^2 = 0",
                new double[]{0.0},
                SquareEq.solve(1.0, 0.0, 0.0), SquareEq.DELTA);
    }
```

Рассматриваем случай, когда два решения уравнения
``` java
    @Test
    public void twoSolutions() {
        assertArrayEquals("x^2 - 1 = 0",
                new double[]{-1.0, 1.0},
                SquareEq.solve(1.0, 0.0, -1.0), DELTA);
        assertArrayEquals("x^2 - 4 = 0",
                new double[]{-2.0, 2.0},
                SquareEq.solve(1.0, 0.0, -4.0), DELTA);
    }

    /**
     * Отдельный тест когда b != 0
     */
    @Test
    public void twoSolutionsBNotNull() {
        // (x-1)(x-2) = x^2 - 3x + 2
        assertArrayEquals("x^2 - 3x + 2 = 0",
                new double[]{1.0, 2.0},
                SquareEq.solve(1.0, -3.0, 2.0), DELTA);
    }
```

Тестируем вырожденный случай: a = 0, b = 0
``` java
    @Test
    public void zeroAZeroB() {
        assertArrayEquals("1 = 0",
                new double[]{},
                SquareEq.solve(0.0, 0.0, 1.0), DELTA);
    }
```

Вырожденный случай: a = 0, b = 0, c = 0
Ожидаемое исключение
``` java
    @Test(expected = AnyXException.class)
    public void zeroAZeroBZeroC() {
        assertArrayEquals("0 = 0",
                new double[]{},
                SquareEq.solve(0.0, 0.0, 0.0), DELTA);
    }
```

[src/test/java/SquareEqTest.java](src/test/java/SquareEqTest.java)


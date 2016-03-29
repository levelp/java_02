<!-- doc.py -->
Операторы и структура кода. Исключения
--------------------------------------

* RuntimeException
* Exception

System.setOut(new PrintStream("my.log"));
throw new MyRuntimeException("xx", 2, 1);
[src/main/java/ExceptionDemo.java](src/main/java/ExceptionDemo.java)

Объявление своего класса-исключения
``` java
public class MyException extends Exception {
    // Могут быть поля-значения
    final double d;
    final String name;
    final int i;

    // Конструктор
    public MyException(String name, int i, double d) {
        super();
        this.name = name;
        this.i = i;
        this.d = d;
    }
}
```

[src/main/java/MyException.java](src/main/java/MyException.java)

java.lang.ArithmeticException: / by zero
[src/test/java/DivideByZero.java](src/test/java/DivideByZero.java)

....
...
throw ex;
ex.printStackTrace();
....
...
[src/test/java/Exceptions.java](src/test/java/Exceptions.java)


<!-- doc.py -->
Тестирование что метод пишет на консоль
---------------------------------------
Пусть есть класс, который что-то выводит на консоль
``` java
public class MyClass {

    public void show() {
        System.out.println(getText());
    }

    String getText() {
        return "SHOW";
    }
}
```

[src/main/java/MyClass.java](src/main/java/MyClass.java)

И есть тест, который должен проверить что класс выводит на консоль
Сравнить строку с заданной очень просто
``` java
        MyClass myClass = new MyClass();
        assertEquals("SHOW", myClass.getText());
```

А для сравнения вывода можно перехватить вывод на консоль
``` java
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            PrintStream save = System.out;
            System.setOut(new PrintStream(stream));
            // Вызываем тестируемый метод
            myClass.show();
            //
            System.setOut(save);

            String result = stream.toString().trim();
            assertEquals("SHOW", result); // Сравниваем
        }
```

[src/test/java/MyClassTest.java](src/test/java/MyClassTest.java)


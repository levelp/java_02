<!-- doc.py -->
Маленькие хитрости Java. StringBuilder
--------------------------------------
Тест производительности.
Демонстрация работы методов StringBuilder: append, insert, delete
``` java
        StringBuilder s = new StringBuilder();

        s.append("boolean: ");
        s.append(true);
        System.out.println(s);
        assertEquals("boolean: true", s.toString());

        s.append("  double: ");
        s.append(1.0);
        System.out.println(s);
        assertEquals("boolean: true  double: 1.0", s.toString());

        // Вставляем подстроку в позицию 13
        s.insert(13, ",");
        System.out.println(s);
        assertEquals("boolean: true,  double: 1.0", s.toString());

        // Удаляем кусок
        s.delete(0, 9);
        System.out.println(s);
        assertEquals("true,  double: 1.0", s.toString());

        s = new StringBuilder();

        // Цепочка действий
        s.append("boolean: ")
                .append(true)
                .append(" double: ")
                .append(1.2)
                .insert(13, ",");
        System.out.println(s);
        assertEquals("boolean: true, double: 1.2", s.toString());

        s.append("  "); // Отступ
        Point point = new Point(2, 3);
        s.append(point);
        assertEquals("boolean: true, double: 1.2  Point{x=2.0, y=3.0}", s.toString());
```

[src/test/java/StringBuilderTest.java](src/test/java/StringBuilderTest.java)

Результат для строки
Результат для StringBuilder
Результат для StringBuffer
@Ignore
Runtime.getRuntime().gc();
Runtime.getRuntime().gc();
[src/test/java/StringVsStringBuilder.java](src/test/java/StringVsStringBuilder.java)

File f1 = new File(STRING_FILENAME);
[src/test/java/StringsTest.java](src/test/java/StringsTest.java)


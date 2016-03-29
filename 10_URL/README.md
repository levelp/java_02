<!-- doc.py -->
Разбор обычных URL
``` java
        URL url = new URL("http://ya.ru/");
        assertEquals("Протокол", "http", url.getProtocol());
        assertEquals("Доменное имя сайта", "ya.ru", url.getHost());
        assertEquals("Путь от корня сайта", "/", url.getPath());
```

Добавляем свой обработчик нестандартных протоколов
Вывод протокола для отладки:
System.out.println("protocol = " + protocol);
Для протокола chrome://
Проверяем разбор нестандартного URL
Разбор стандартных URL по-прежнему работает
System.out.println(url.getContent());
[src/test/java/URLTest.java](src/test/java/URLTest.java)


<!-- doc.py -->
TODO: реализовать
Получаем объект-класс того объекта
который надо сохранить
Печатаем имя класса
Сохраняем все поля: c.getDeclaredFields()
Получаем доступ к
private/protected/package local
``` java
            field.setAccessible(true);
```

Считываем имя класса
Создаем класс по имени
Создаём экземпляр класса
Инициализируем поля
Считываем строчку
scanner.next() читает символы
до разделителя (табуляции, пробела,
перевода строки)
if (field.getType().equals(Double.TYPE)) {
field.set(instance, scanner.nextDouble());
}
Как присвоить значение полю произвольного типа?
----------------------------------------------
Способ 1 - "много if":
``` java
            if (field.getType().equals(Double.TYPE))
                field.set(instance, Double.valueOf(value));
            if (field.getType().equals(Integer.TYPE))
                field.set(instance, Integer.valueOf(value));
```

Способ 2 - используем Reflection
Получаем класс-обёртку
``` java
                String typeName = field.getType().getName();
                System.out.println("typeName = " + typeName);

                String fieldClassName;
                if (field.getType().equals(Integer.TYPE))
                    fieldClassName = Integer.class.getName();
                else if (field.getType().equals(Character.TYPE))
                    fieldClassName = Character.class.getName();
                else {
                    String firstLetter = "" + typeName.charAt(0);
                    fieldClassName = "java.lang." + firstLetter.toUpperCase() +
                            typeName.substring(1);
                    System.out.println("fieldClassName = " + fieldClassName);
                }
```

null - для статических методов
[src/main/java/FileStorage.java](src/main/java/FileStorage.java)


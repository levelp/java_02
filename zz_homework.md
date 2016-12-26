Домашнее задание
================

Свой односвязанный список (Generics)
------------------------------------

Сохранение и загрузка произвольного класса через Reflection API
---------------------------------------------------------------
Дан класс с полями всех примитивных типов:
```
public class AllCases {
    public int publicInt = 3;
    protected int protectedInt = 2;
    byte aByte = 10;
    short aShort = 111;
    int aInt = 1213;
    long aLong = 1225324234;
    float aFloat = 21432.1f;
    double aDouble = 12345.5678;
    char aChar = 'A';
    boolean aBoolean = true;
    private int privateInt = 1;
}
```

Реализовать:
```
public class FileStorage {
  Object load(fileName);
  save(fileName);
}
```

Работа над своим проектом (ядро)
--------------------------------
* Договориться о проекте (объединиться в команды по 2-3 человека)
* Дополнить классы модели конструкторами, equals, hashCode, toString.
* Реализовать типы объектной модели через enum.
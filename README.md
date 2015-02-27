Типы. Операторы. Объектная модель в Java
========================================

git
---



Контрольные вопросы по ООП. Кодировка в java. Типы данных.

* Операторы

Структура памяти: куча, стек, регистры, константы
-------------------------------------------------

**Динамическое распределение памяти** - способ выделения оперативной памяти компьютера для объектов в программе,
при котором выделение памяти под объект осуществляется во время выполнения программы.

Параметры методов. Преобразование типов. Boxing
-----------------------------------------------

Пакеты. import. Модификаторы доступа. Область видимости
-------------------------------------------------------

Object, equals, hashCode, toString
----------------------------------

Класс Class. Конструктор. Инициализация
---------------------------------------

this, super, abstract, instanceof, static
-----------------------------------------

Соглашения по именованию
------------------------


Enum - перечисления
-------------------

Программируя мы часто сталкиваемся с необходимостью ограничить множество допустимых значений для некоторого типа данных.
Например, день недели может иметь 7 разных значений, месяц в году - 12, а время года - 4.
Для решения подобных задач во многих языках программирования со статической типизацией предусмотрен специальный тип данных - перечисление (enum).

В Java перечисления появилось с версии 1.5.



Времена года
``` java
enum Season { WINTER, SPRING, SUMMER, AUTUMN }
```
Пол: мужской, женский
``` java
public enum Sex {
    MALE, FEMALE
}
```
Элементы перечисления - экземпляры enum-класса,
доступные статически.
Планеты солнечной системы
``` java
public enum SolarSystemPlanet {
    EARTH("Земля"),
    MARS("Марс"),
    VENUS("Венера");

    private final String name;

    SolarSystemPlanet(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Планета \"" + name + "\"";
    }
}
```
Конструктор
Getting the runtime reference from system
Заводим массив в динамической памяти
counter = 0;
immutable
Считать количество цифр
Считать количество букв
+ Подсчитывать количество букв A,
B, C... всех символов таблицы UTF-8
Double.MAX_VALUE;
Double.MIN_VALUE;
Double.NaN;
﻿Generic - создание своего контейнера
====================================

System.arraycopy(data, 0, newData, 0, data.length);
Сортировка
Возвращаем результат
assertArrayEquals(new String[]);
Reflection API + использоваение в библиотеках ORM
=================================================

Аннотации
---------

Объявление аннотации
``` java
public @interface AnnotationName {
}
```



Использование аннотаций
``` java
    private static void saveToDB(Object obj) {
        Class c = obj.getClass();
        // Пробегаем по всем полям класса
        for (Field field : c.getDeclaredFields()) {
            try {
                int modifiers = field.getModifiers();
                if ((modifiers & Modifier.PRIVATE) != 0) {
                    continue;
                }
                if ((modifiers & Modifier.PUBLIC) != 0)
                    System.out.print("public ");
                System.out.println(field.getName() + " = "
                        + field.get(obj) + " " + modifiers);

                boolean isPrimaryKey = field.getAnnotation(PrimaryKey.class) != null;
                System.out.println("isPrimaryKey = " + isPrimaryKey);

                db.annotations.Field fieldAnnotation = field.getAnnotation(db.annotations.Field.class);
                if (fieldAnnotation != null)
                    System.out.println("DB field name = " + fieldAnnotation.value());
            } catch (IllegalAccessException e) {
                System.out.println(field.getName());
                e.printStackTrace();
            }
        }
    }
```
@Field("PASSWORD")
Object obj = cDoubleArray.newInstance();
Object string = cStringArray.newInstance();
По объекту получаем класс
Значение и имя класса
Получаем класс по имени класса
Пытаемся создать экземпляр класса
Выводим экземпляр класса
Создаю экземпляр класса
Добавляем свой обработчик нестандартных протоколов
Вывод протокола для отладки:
System.out.println("protocol = " + protocol);
Для протокола chrome://
Проверяем разбор нестандартного URL
Разбор стандартных URL по-прежнему работает
System.out.println(url.getContent());
TODO: показать
TODO: показать
TODO: показать
Треугольник
assertEquals();
Вызываем все статические методы
Object objectA = new A();
Получаю экземпляр класса
Создаём объект (экземпляр класса className)
TODO: Вывести все поля
Получаем массив полей
TODO: Вызвать все методы
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:");
Сколько секунд прошло
System.out.println("inSecond: " + inSeconds);
XORTest - Отличия Java от C++
=============================


Поле недоступно, т.к. другой пакет
packageField = 10;
Доступно везде
Внутри класса A и во всех наследниках
Внутри класса A
во всех классах внутри этого пакета
Только внутри класса A
privateField = 2;
Нет доступа из другого пакета
a.packageField = 10;
...
sendEmail();
Создаём тестового пользователя
Параметры
Получаю значения из интерфейса
Сумма чисел
Отправляю результат в интерфейс
Новый элемент становится первым
и ссылается на тот список, который был до операции
добавления
Новый элемент -> последний
Новый элемент добавляем в конец списка
Восстанавливаем вывод на консоль
﻿Домашнее задание
----------------
* Дополнить классы модели конструкторами, equals, hashCode, toString.
* Реализовать типы объектной модели через enum.


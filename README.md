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



.\00_enum\pom.xml
.\00_enum\src\main\java\Season.java
Времена года
``` java
enum Season { WINTER, SPRING, SUMMER, AUTUMN }
```
.\00_enum\src\main\java\Sex.java
Пол: мужской, женский
``` java
public enum Sex {
    MALE, FEMALE
}
```
.\00_enum\src\main\java\SolarSystemPlanet.java
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
.\00_enum\src\test\java\SolarSystemTest.java
Память
======
* Статическая
* Динамическая - **new** / GC.
* Стек - локальные переменные в функциях и методах.

.\01_Memory\pom.xml
.\01_Memory\src\main\java\A_Memory.java
Getting the runtime reference from system
Заводим массив в динамической памяти
Пауза в 10 миллисекунд
Заводии в динамической памяти массив
Номер массива
.\01_Memory\src\main\java\Constructors.java
Конструкторы
------------
Конструктор - метод, который вызывается при созданни объекта.
``` java
public class Constructors {

    public static void main(String[] args) {
        MyClass myClass = new MyClass(2);
        MyClass myClass1 = new MyClass(2.0);
        MyClass myClass3 = new MyClass("Test");
        MyClass2 myClass2 = new MyClass2();
    }

    // Класс с 3 конструкторами:
    static class MyClass {
        public MyClass() { // Без параметров
            System.out.println("Конструктор");
        }

        public MyClass(double i) { // Один параметр - double
            System.out.println("Конструктор: d = " + i);
        }

        public MyClass(int i) {  // Один параметр - int
            System.out.println("Конструктор: i = " + i);
        }

        public MyClass(String s) {
            System.out.println("Конструктор: s = " + s);
        }
    }

    static class MyClass2 extends MyClass {

    }
}
```
.\01_Memory\src\main\java\DynMemoryClass.java
Стек. Переполнение стека
------------------------
``` java
public class DynMemoryClass {

    // Статическая память
    static int staticVar = 10;

    public static void main(String[] args) {
        // Стек + динамическая
        MyClass myClass = new MyClass();

        // Стек
        int i = 2;
        rec(i);
    }

    private static void rec(int i) {
        double d = 10; // Стек
        // Ссылка myClass - в стеке
        // А см объект MyClass - в куче
        // (в динамической памяти)
        MyClass myClass = new MyClass();
        System.out.println("i = " + i);
        rec(i + 1);
    }

    static public class MyClass {
        int counter = 0;

        public MyClass() {
            counter++;
        }
    }
}
```
.\01_Memory\src\main\java\StaticConstructor.java
Инициализация выполняется последовательно
Строчка за строчкой
Сначала всё со словом static
Потом инициализация не static переменных
counter = 0;
.\02_Boxing\pom.xml
.\02_Boxing\src\main\java\BoxingDemo.java
immutable
.\02_Boxing\src\main\java\ImmutableDemo.java
.\02_Boxing\src\main\java\TypesDemo.java
.\02_Boxing\src\test\java\CountChars.java
Считать количество цифр
Считать количество букв
+ Подсчитывать количество букв A,
B, C... всех символов таблицы UTF-8
.\02_Boxing\src\test\java\TypesDemoTest.java
Double.MAX_VALUE;
Double.MIN_VALUE;
Double.NaN;
﻿Generic - создание своего контейнера
====================================

.\02_DataStruct\pom.xml
.\02_DataStruct\src\main\java\ArrayListSizeDemo.java
arrayList.ensureCapacity(100);
.\02_DataStruct\src\main\java\MyStack.java
``` java
public class MyStack<T> {
    // Количество элементов
    int counter = 0;
    // Тут реально будем хранить данные
    Object[] data = new Object[0];

    /**
     * Добавить значение в стек
     *
     * @param value значение
     */
    public void push(T value) {
        ++counter;
        if (data.length < counter) {
            Object[] newData = new Object[counter * 2];
            // Копируем массив встроенными средствами
            System.arraycopy(data, 0, newData, 0, data.length);
            // Копируем поэлементно
            for (int i = 0; i < data.length; ++i)
                newData[i] = data[i];
            // Ссылку на новый массив сохраняем
            data = newData;
        }
        // Новое значение
        data[counter - 1] = value;
    }

    public int size() {
        return counter;
    }

    /**
     * Получить значение с вершины стека
     *
     * @return значение
     */
    @SuppressWarnings("unchecked")
    public T pop() {
        T value = (T) data[data.length - 1];
        counter--;
        if (counter * 2 < data.length) {
            Object[] newData = new Object[data.length - 1];
            System.arraycopy(data, 0, newData, 0, data.length - 1);
            data = newData;
        }
        return value;
    }
}
```
.\02_DataStruct\src\main\java\Sort.java
Сортировка
Возвращаем результат
.\02_DataStruct\src\test\java\SortTest.java
assertArrayEquals(new String[]);
.\02_DataStruct\src\test\java\StackTest.java
Reflection API + использоваение в библиотеках ORM
=================================================

Аннотации
---------

Объявление аннотации
``` java
public @interface AnnotationName {
}
```



.\03_Reflection\pom.xml
.\03_Reflection\src\main\java\annotations\Show.java
.\03_Reflection\src\main\java\db\annotations\Autoincrement.java
.\03_Reflection\src\main\java\db\annotations\Field.java
.\03_Reflection\src\main\java\db\annotations\MyAnnotation.java
.\03_Reflection\src\main\java\db\annotations\PrimaryKey.java
.\03_Reflection\src\main\java\db\dao\SaveToDB.java
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
.\03_Reflection\src\main\java\db\model\User.java
@Field("PASSWORD")
.\03_Reflection\src\main\java\simple\AnnotationDemo.java
.\03_Reflection\src\main\java\simple\MyClass.java
.\03_Reflection\src\test\java\classes\ReflectionTest.java
.\03_Reflection\src\test\java\classes\ShowClass.java
По объекту получаем класс
Значение и имя класса
Получаем класс по имени класса
Пытаемся создать экземпляр класса
Выводим экземпляр класса
.\03_Reflection\src\test\java\fields\ShowAllFields.java
.\03_Reflection\src\test\java\fields\ShowFieldsWithAnnotation.java
.\03_Reflection\src\test\java\methods\CallAllMethods.java
Создаю экземпляр класса
.\04_Object_Equals_hashCode_toString\pom.xml
.\04_Object_Equals_hashCode_toString\src\main\java\Equals.java
.\04_Object_Equals_hashCode_toString\src\main\java\ThisLink.java

Использование ссылки this
-------------------------
``` java
        // Создаём журнал
        Journal journal = new Journal();

        // И двух подписчиков
        User A = new User("Петя");
        User B = new User("Вася");
        A.subscribe(journal);
        B.subscribe(journal);

        // Два выпуска журнала
        journal.release("Сентябрь 2014");
        journal.release("Октябрь 2014");
```
``` java
        // Когда журнал выходит
        public void release(String name) {
            for (User user : users) {
                // Он отправляется всем подписчикам
                user.send(name);
            }
        }
```
.\04_Object_Equals_hashCode_toString\src\main\java\geometry\NamedPoint.java
.\04_Object_Equals_hashCode_toString\src\main\java\geometry\Point.java
Координаты
Double.hashCode(y);
.\04_Object_Equals_hashCode_toString\src\main\java\geometry\PrimaryKey.java
.\04_Object_Equals_hashCode_toString\src\main\java\geometry\Segment.java
.\04_Object_Equals_hashCode_toString\src\main\java\geometry\Settings.java
.\04_Object_Equals_hashCode_toString\src\test\java\HashMapTest.java
.\04_Object_Equals_hashCode_toString\src\test\java\PointTest.java
.\05_URL\pom.xml
.\05_URL\src\test\java\URLTest.java
Добавляем свой обработчик нестандартных протоколов
Вывод протокола для отладки:
System.out.println("protocol = " + protocol);
Для протокола chrome://
Проверяем разбор нестандартного URL
Разбор стандартных URL по-прежнему работает
System.out.println(url.getContent());
.\06_Shapes\pom.xml
.\06_Shapes\src\main\java\Circle.java
TODO: показать
.\06_Shapes\src\main\java\Point.java
.\06_Shapes\src\main\java\Rectangle.java
TODO: показать
.\06_Shapes\src\main\java\Shape.java
.\06_Shapes\src\main\java\Triangle.java
TODO: показать
.\06_Shapes\src\test\java\ShapesTest.java
Треугольник
assertEquals();
﻿Тестирование что метод пишет на консоль
---------------------------------------

.\07_JUnit_Void\pom.xml
.\07_JUnit_Void\src\main\java\MyClass.java

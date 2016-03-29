<!-- doc.py -->
﻿Типы. Операторы. Объектная модель в Java
========================================

git
---



Контрольные вопросы по ООП. Кодировка в java. Типы данных
---------------------------------------------------------

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

Библи




``` java
// Любое значение X
public class AnyXException extends RuntimeException {
}
```

[00_HomeWork_Done/src/main/java/levelp/AnyXException.java](00_HomeWork_Done/src/main/java/levelp/AnyXException.java)

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

[00_HomeWork_Done/src/main/java/levelp/SquareEq.java](00_HomeWork_Done/src/main/java/levelp/SquareEq.java)

``` java
    @Test
    public void testSimple() {
        // assertEquals( ожидаемое_значение, вычисленное значение )
        //       сообщение_если_значения_не_равны, ..., ...
        // a*x^2 + b*x + c = 0
        assertArrayEquals("x^2 = 0",
                new double[]{0.0},
                SquareEq.solve(1.0, 0.0, 0.0), DELTA);
    }
```

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

``` java
    @Test
    public void zeroAZeroB() {
        assertArrayEquals("1 = 0",
                new double[]{},
                SquareEq.solve(0.0, 0.0, 1.0), DELTA);
    }
```

``` java
    @Test(expected = AnyXException.class)
    public void zeroAZeroBZeroC() {
        assertArrayEquals("0 = 0",
                new double[]{},
                SquareEq.solve(0.0, 0.0, 0.0), DELTA);
    }
```

[00_HomeWork_Done/src/test/java/SquareEqTest.java](00_HomeWork_Done/src/test/java/SquareEqTest.java)

Enum - перечисления
-------------------

Программируя мы часто сталкиваемся с необходимостью ограничить множество допустимых значений для некоторого типа данных.
Например, день недели может иметь 7 разных значений, месяц в году - 12, а время года - 4.
Для решения подобных задач во многих языках программирования со статической типизацией предусмотрен специальный тип данных - перечисление (enum).

В Java перечисления появилось с версии 1.5.



``` java
enum Season {
    WINTER("Зима"),
    SPRING("Весна"),
    SUMMER("Лето"),
    AUTUMN("Осень");

    public final String name;

    /**
     * Конструктор
     *
     * @param name Название сезона по-русски
     */
    Season(String name) {
        this.name = name;
        // Про final
        final int[] intArray = new int[1000];
        intArray[1] = 22;
        System.out.println("intArray[4] = " + intArray[4]);
        //intArray = new int[100]; //
        intArray[0] = 100;
        final int CONST = 100;
        //CONST = 1000; // Не можем менять значение
    }
}
```

[01_enum/src/main/java/Season.java](01_enum/src/main/java/Season.java)

``` java
public enum Sex {
    // 0 1 2
    MALE, FEMALE, UNKNOWN;

    public static void main(String[] args) {
        Sex x = new Random().nextInt(2) == 0 ?
                MALE : FEMALE;
        switch (x) {
            case MALE:
                System.out.println("x = " + x);
                break;
            case FEMALE:
                System.out.println("!!!");
                break;
        }
    }
}
```

[01_enum/src/main/java/Sex.java](01_enum/src/main/java/Sex.java)

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

[01_enum/src/main/java/SolarSystemPlanet.java](01_enum/src/main/java/SolarSystemPlanet.java)

``` java
        SolarSystemPlanet planet = SolarSystemPlanet.EARTH;

        needColonization(planet);
```

``` java
        Random random = new Random();

        Sex sex = random.nextInt(2) == 0 ? Sex.FEMALE : Sex.MALE;
        if (sex == Sex.MALE) {
            System.out.println("Мужской");
        } else {
            System.out.println("Женский");
        }
```

[01_enum/src/test/java/SolarSystemTest.java](01_enum/src/test/java/SolarSystemTest.java)

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

[03_Reflection/src/main/java/db/dao/SaveToDB.java](03_Reflection/src/main/java/db/dao/SaveToDB.java)

``` java
public class MyStack<T> {
    // Количество элементов
    int numberOfElements = 0;
    // Реально будем хранить данные в массиве
    Object[] data = new Object[0];

    /**
     * Добавить значение в стек
     *
     * @param value значение
     */
    public void push(T value) {
        //T[] = new T[100];
        ++numberOfElements;
        // Если не хватает места для хранения данных
        if (data.length < numberOfElements) {
            Object[] newData = new Object[numberOfElements * 2];
            // Копируем массив встроенными средствами
            System.arraycopy(data, 0, newData, 0, data.length);
            // Копируем поэлементно
            // for (int i = 0; i < data.length; ++i)
            //     newData[i] = data[i];
            // Ссылку на новый массив сохраняем
            data = newData;
        }
        // Новое значение
        data[numberOfElements - 1] = value;
    }

    public int size() {
        return numberOfElements;
    }

    /**
     * Получить значение с вершины стека
     *
     * @return значение
     */
    @SuppressWarnings("unchecked")
    public T pop() {
        T value = (T) data[data.length - 1];
        numberOfElements--;
        if (numberOfElements * 2 < data.length) {
            Object[] newData = new Object[data.length - 1];
            System.arraycopy(data, 0, newData, 0, data.length - 1);
            data = newData;
        }
        return value;
    }
}
```

[05_DataStruct/src/main/java/MyStack.java](05_DataStruct/src/main/java/MyStack.java)

Память
======
* Статическая
* Динамическая - **new** / GC.
* Стек - локальные переменные в функциях и методах.

Опции JVM для настройки размера памяти:
---------------------------------------
* -Xmnsize - initial and maximum size (in bytes) of the heap for the young generation (nursery)
* -Xmssize - размер кучи (heap)
* -Xsssize - размер стека для потока
* -Xmx8G - размер памяти, которую можно использовать JVM (всего)

256 мегабайт в разных единицах: -Xmn256m -Xmn262144k -Xmn268435456

Составить строчку с настройками Java-машины: http://jvmmemory.com/

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

[05_Memory/src/main/java/Constructors.java](05_Memory/src/main/java/Constructors.java)

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
        int i1 = 10, i2 = 11, i3 = 12, i4 = 14;
        double d1, d2, d3, d4, d5, d6, d7, d8, d9;
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

[05_Memory/src/main/java/DynMemoryClass.java](05_Memory/src/main/java/DynMemoryClass.java)

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

[05_Object_Equals_hashCode_toString/src/main/java/ThisLink.java](05_Object_Equals_hashCode_toString/src/main/java/ThisLink.java)

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

[07_JUnit_Void/src/main/java/MyClass.java](07_JUnit_Void/src/main/java/MyClass.java)

``` java
        MyClass myClass = new MyClass();
        assertEquals("SHOW", myClass.getText());
```

``` java
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            PrintStream save = System.out;
            System.setOut(new PrintStream(stream));
            // Вызываем тестируемый метод
            myClass.show();
            //
            System.setOut(save);

            String result = stream.toString();
            assertEquals("SHOW\r\n", result); // Сравниваем
        }
```

[07_JUnit_Void/src/test/java/MyClassTest.java](07_JUnit_Void/src/test/java/MyClassTest.java)

``` java
        // Загружаем класс A
        Class cls = Class.forName("A");
        // Создаём экземпляр класса A
        Object obj = cls.newInstance();
        // Получаем метод по имени
        Method method = cls.getMethod("newMethod");
        // Вызываем метод и получаем значение
        Integer integer = (Integer) method.invoke(obj);
        System.out.println("integer = " + integer);

        // Вызываем все статические методы
        for (Method m : cls.getDeclaredMethods()) {
            if ((m.getModifiers() & Modifier.STATIC) == 0)
                continue;
            m.invoke(null);
        }
```

[08_ReflectionTask/src/test/java/ATest.java](08_ReflectionTask/src/test/java/ATest.java)

``` java
        System.out.println("Show class: " + className);

        // Получаю экземпляр класса
        Class c = Class.forName(className);

        // Создаём объект (экземпляр класса className)
        Object obj = c.newInstance();

        // Получаем массив полей и выводим значения
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            Object value = field.get(obj);
            System.out.println(field.getName() + " = " + value);
        }

        // Вызываем все методы
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            method.invoke(obj);
        }
```

[08_ReflectionTask/src/test/java/Test.java](08_ReflectionTask/src/test/java/Test.java)

``` java
            field.setAccessible(true);
```

``` java
            if (field.getType().equals(Double.TYPE))
                field.set(instance, Double.valueOf(value));
            if (field.getType().equals(Integer.TYPE))
                field.set(instance, Integer.valueOf(value));
```

``` java
                String typeName = field.getType().getName();
                System.out.println("typeName = " + typeName);

                String fieldClassName;
                // int - Integer
                // char - Character
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

[09_ReflectionTask_SaveLoad/src/main/java/FileStorage.java](09_ReflectionTask_SaveLoad/src/main/java/FileStorage.java)

Графические (GUI) библиотеки
============================

1. AWT - Abstract Window Toolkit
2. Swing -
3. JavaFX

``` cpp
#include <iostream>

using namespace std;

int main(){
  int a = 3, b = 10;

  a = 3; b = 10;
  cout << "a ^= b;" << endl;
  a ^= b;
  cout << "b ^= a;" << endl;
  b ^= a;
  cout << "a ^= b;" << endl;
  a ^= b;
  cout << "a = " << a << "   b = " << b << endl;

  a = 3; b = 10;
  cout << "a ^= b ^= a ^= b;" << endl;
  a ^= b ^= a ^= b;

  cout << "a = " << a << "   b = " << b << endl;

  a = 3; b = 10;
  cout << "a ^= (b ^= (a ^= b));" << endl;
  a ^= (b ^= (a ^= b));

  cout << "a = " << a << "   b = " << b << endl;

  return 0;
}
```

[98_XORTest/cpp_check.cpp](98_XORTest/cpp_check.cpp)

Стек и очередь на Java
======================

Стек: Stack<T>
* push добавить на вершину
* pop получить с вершины и удалить
* isEmpty() стек пуст?

``` java
 Stack<Integer> stack = new Stack<>();
 stack.push(1);
 stack.push(2);
 stack.push(3);
 assertEquals(3, stack.pop().intValue());
 assertEquals(2, stack.pop().intValue());
 assertEquals(1, stack.pop().intValue());
```

Очередь: Queue<T>
* put добавить в начало очереди
* get получить из конца очереди и удалить
* isEmpty() стек пуст?

``` java
Queue<String> queue = new Queue<>();
queue.put("Hello");
queue.put("world");
assertEquals("Hello", queue.get());
assertEquals("world", queue.get());
````

Аgitация: система контроля версий Git
-------------------------------------

Зачем нужны и в чём полезны системы контроля версий?
* Устойчивость / уверенность - вы всегда можете вернуться к "хорошей" / "удачной" версии программы
* Объединение / слияние версий. Легче объединять два набора поименнованных изменений, чем просто два разных каталога.
  - можно найти общего предка и накладывать понятные небольшие патчи, проводя интеграционное тестирование после каждого патча.

**Антишаблоны:**
* Коммиты с одним и тем же сообщением (раз в 5 минут или раз в день) - найти среди таких коммитов что-то ценное затруднительно.
* Мега-коммиты (содержащие изменения в десятки и сотни файлов)
 * Исключение: начальный шаблон/состояние проекта (если он до этого не был под системой контроля версий или история версий не читабельна всё равно)
 * Очень тяжёл merge двух мегакоммитов, часто приводит к ошибкам, особенно если покрытие кода тестами невелико.

**Системы:**
* Централизованные
* Децентрализованные

Книга: Pro git (creative common)

**msysgit** - ставим и в командной строке используем команду **git**.

**Понятия:**
* Репозиторий - remote / local
* Коммит
* Патч
* Ветка
* Слияние (merge)
* Конфликт при слиянии

**Команды:**
* **init** - создание репозитория
* **add** - добавление файлов
* **status** - просмотр текущего состояния
* **commit -a -m** - фиксирование изменений, использование **vi**
* **log** - просмотр истории изменений
* **clone** - локальная копия remote репозитория
* **pull** - обновление + stash + merge
* **push** - отправление изменений из локального репозитория в удалённый, генерация ключей, ssh-keygen -t rsa

**GUI утилиты:**
* TortoiseGit
* Intellij Idea


http://habrahabr.ru/post/125799/ - Как начать работать с GitHub: быстрый старт
http://habrahabr.ru/post/147192/ - Изучить Github за 15 минут
https://try.github.io - интерактивные уроки по Git



﻿Домашнее задание:
-----------------

Сохранение и загрузка через Reflection API
------------------------------------------
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

Работа над проектом (ядро):
---------------------------
* Дополнить классы модели конструкторами, equals, hashCode, toString.
* Реализовать типы объектной модели через enum.


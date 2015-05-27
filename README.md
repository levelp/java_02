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

[00_enum/src/main/java/Season.java](00_enum/src/main/java/Season.java)

Пол: мужской, женский
``` java
public enum Sex {
    MALE, FEMALE
}
```

[00_enum/src/main/java/Sex.java](00_enum/src/main/java/Sex.java)

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

[00_enum/src/main/java/SolarSystemPlanet.java](00_enum/src/main/java/SolarSystemPlanet.java)

Использование перечислений:
Присваивать можем только одно из значений enum'а
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

[00_enum/src/test/java/SolarSystemTest.java](00_enum/src/test/java/SolarSystemTest.java)

Память
======
* Статическая
* Динамическая - **new** / GC.
* Стек - локальные переменные в функциях и методах.

Getting the runtime reference from system
Заводим массив в динамической памяти
Пауза в 10 миллисекунд
Заводии в динамической памяти массив
Номер массива
[01_Memory/src/main/java/A_Memory.java](01_Memory/src/main/java/A_Memory.java)

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

[01_Memory/src/main/java/Constructors.java](01_Memory/src/main/java/Constructors.java)

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

[01_Memory/src/main/java/DynMemoryClass.java](01_Memory/src/main/java/DynMemoryClass.java)

Инициализация выполняется последовательно
Строчка за строчкой
Сначала всё со словом static
Потом инициализация не static переменных
counter = 0;
[01_Memory/src/main/java/StaticConstructor.java](01_Memory/src/main/java/StaticConstructor.java)

immutable
[02_Boxing/src/main/java/BoxingDemo.java](02_Boxing/src/main/java/BoxingDemo.java)

Считать количество цифр
Считать количество букв
+ Подсчитывать количество букв A,
B, C... всех символов таблицы UTF-8
[02_Boxing/src/test/java/CountChars.java](02_Boxing/src/test/java/CountChars.java)

Double.MAX_VALUE;
Double.MIN_VALUE;
Double.NaN;
[02_Boxing/src/test/java/TypesDemoTest.java](02_Boxing/src/test/java/TypesDemoTest.java)

arrayList.ensureCapacity(100);
[02_DataStruct/src/main/java/ArrayListSizeDemo.java](02_DataStruct/src/main/java/ArrayListSizeDemo.java)

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

[02_DataStruct/src/main/java/MyStack.java](02_DataStruct/src/main/java/MyStack.java)

Сортировка
Возвращаем результат
[02_DataStruct/src/main/java/Sort.java](02_DataStruct/src/main/java/Sort.java)

assertArrayEquals(new String[]);
[02_DataStruct/src/test/java/SortTest.java](02_DataStruct/src/test/java/SortTest.java)

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

[03_Reflection/src/main/java/db/dao/SaveToDB.java](03_Reflection/src/main/java/db/dao/SaveToDB.java)

@Field("PASSWORD")
[03_Reflection/src/main/java/db/model/User.java](03_Reflection/src/main/java/db/model/User.java)

По объекту получаем класс
Значение и имя класса
Получаем класс по имени класса
Пытаемся создать экземпляр класса
Выводим экземпляр класса
[03_Reflection/src/test/java/classes/ShowClass.java](03_Reflection/src/test/java/classes/ShowClass.java)

Создаю экземпляр класса
[03_Reflection/src/test/java/methods/CallAllMethods.java](03_Reflection/src/test/java/methods/CallAllMethods.java)


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

[04_Object_Equals_hashCode_toString/src/main/java/ThisLink.java](04_Object_Equals_hashCode_toString/src/main/java/ThisLink.java)

Координаты
http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java
Double.hashCode(y);
[04_Object_Equals_hashCode_toString/src/main/java/geometry/Point.java](04_Object_Equals_hashCode_toString/src/main/java/geometry/Point.java)

Добавляем свой обработчик нестандартных протоколов
Вывод протокола для отладки:
System.out.println("protocol = " + protocol);
Для протокола chrome://
Проверяем разбор нестандартного URL
Разбор стандартных URL по-прежнему работает
System.out.println(url.getContent());
[05_URL/src/test/java/URLTest.java](05_URL/src/test/java/URLTest.java)

TODO: показать
[06_Shapes/src/main/java/Circle.java](06_Shapes/src/main/java/Circle.java)

TODO: показать
[06_Shapes/src/main/java/Rectangle.java](06_Shapes/src/main/java/Rectangle.java)

TODO: показать
[06_Shapes/src/main/java/Triangle.java](06_Shapes/src/main/java/Triangle.java)

Треугольник
assertEquals();
[06_Shapes/src/test/java/ShapesTest.java](06_Shapes/src/test/java/ShapesTest.java)

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

[07_JUnit_Void/src/main/java/MyClass.java](07_JUnit_Void/src/main/java/MyClass.java)

И есть тест, который должен проверить что класс выводит на консоль
Сравнить строку с заданной очень просто
``` java
        MyClass myClass = new MyClass();
        assertEquals("SHOW", myClass.getText());
```

А для сравнения вывода можно перехватить вывод на консоль
``` java
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        // Вызываем тестируемый метод
        myClass.show();
        //
        stream.close();

        String result = stream.toString();
        assertEquals("SHOW\r\n", result); // Сравниваем
```

[07_JUnit_Void/src/test/java/MyClassTest.java](07_JUnit_Void/src/test/java/MyClassTest.java)

Reflection API
--------------
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

Object objectA = new A();
[08_ReflectionTask/src/test/java/CreateInstance.java](08_ReflectionTask/src/test/java/CreateInstance.java)

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
[09_ReflectionTask_SaveLoad/src/main/java/FileStorage.java](09_ReflectionTask_SaveLoad/src/main/java/FileStorage.java)

SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:");
Сколько секунд прошло
System.out.println("inSecond: " + inSeconds);
[11_Timer/src/main/java/MainForm.java](11_Timer/src/main/java/MainForm.java)

``` cpp
#include <iostream>

using namespace std;

int main(){
  int a = 3, b = 10;

  a ^= b ^= a ^= b;

  cout << "a = " << a << "   b = " << b << endl;

  a = 3; b = 10;

  a ^= (b ^= (a ^= b));

  cout << "a = " << a << "   b = " << b << endl;

  return 0;
}
```

[98_XORTest/cpp_check.cpp](98_XORTest/cpp_check.cpp)

...
[Q1/src/main/java/Resume.java](Q1/src/main/java/Resume.java)

sendEmail();
[Q1/src/main/java/User.java](Q1/src/main/java/User.java)

Создаём тестового пользователя
Параметры
[Q1/src/test/java/UserTest.java](Q1/src/test/java/UserTest.java)

Получаю значения из интерфейса
Сумма чисел
Отправляю результат в интерфейс
[SwingDemo/src/main/java/MainForm.java](SwingDemo/src/main/java/MainForm.java)


Свой односвязанный список
-------------------------
Тип элементов - T
Новый элемент становится первым
и ссылается на тот список, который был до операции
добавления
Новый элемент -> последний
Новый элемент добавляем в конец списка
[Task_Generic/src/main/java/MyList.java](Task_Generic/src/main/java/MyList.java)

Восстанавливаем вывод на консоль
[Task_Generic/src/test/java/ListTest.java](Task_Generic/src/test/java/ListTest.java)

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
* Дополнить классы модели конструкторами, equals, hashCode, toString.
* Реализовать типы объектной модели через enum.


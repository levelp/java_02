<!-- doc.py -->
﻿
[![Build Status](https://travis-ci.org/levelp/java_02.svg?branch=master)](https://travis-ci.org/levelp/java_02)
[![Coverage Status](https://coveralls.io/repos/github/levelp/java_02/badge.svg?branch=master)](https://coveralls.io/github/levelp/java_02?branch=master)

Типы. Операторы. Объектная модель в Java
========================================

Контрольные вопросы по ООП. Кодировка в java. Типы данных
---------------------------------------------------------

* Операторы

Структура памяти: куча, стек, регистры, константы
-------------------------------------------------

**Динамическое распределение памяти** - способ выделения оперативной памяти компьютера для объектов в программе,
при котором выделение памяти под объект осуществляется во время выполнения программы.


Итак, память процесса различается на heap (куча) и non-heap (стек) память, и состоит из 5 областей (memory pools, memory spaces):

* **Eden Space** (heap) – в этой области выделятся память под все создаваемые из программы объекты.
Большая часть объектов живет недолго (итераторы, временные объекты, используемые внутри методов и т.п.),
и удаляются при выполнении сборок мусора это области памяти, не перемещаются в другие области памяти.
Когда данная область заполняется (т.е. количество выделенной памяти в этой области превышает некоторый заданный процент),
GC выполняет быструю (minor collection) сборку мусора.
По сравнению с полной сборкой мусора она занимает мало времени, и затрагивает только эту область памяти
- очищает от устаревших объектов Eden Space и перемещает выжившие объекты в следующую область.
* **Survivor Space** (heap) – сюда перемещаются объекты из предыдущей, после того,
как они пережили хотя бы одну сборку мусора.
Время от времени долгоживущие объекты из этой области перемещаются в Tenured Space.
* **Tenured (Old) Generation** (heap) - Здесь скапливаются долгоживущие объекты
(крупные высокоуровневые объекты, синглтоны, менеджеры ресурсов и проч.).
Когда заполняется эта область, выполняется полная сборка мусора (full, major collection),
которая обрабатывает все созданные JVM объекты.
* **Permanent Generation** (non-heap) – Здесь хранится метаинформация, используемая JVM (используемые классы, методы и т.п.)
* **Code Cache (non-heap)** - эта область используется JVM,
когда включена JIT-компиляция, в ней кешируется скомпилированный платформенно — зависимый код.

this, super, abstract, instanceof, static
-----------------------------------------

Соглашения по именованию
------------------------
Классы/Интерфейсы - CamelCase -
http://iprg.ru/forum/index.php?topic=367.0



Исключения в Java
----------------
Бывают двух видов:
* Наследники от класса **Exception**
надо указывать throws в цепочке вызовов.
* Наследники от класса **RuntimeException**
не надо указывать throws.
``` java
// Любое значение X
public class AnyXException extends RuntimeException {
}
```

[00_HomeWork_Done/src/main/java/levelp/AnyXException.java](00_HomeWork_Done/src/main/java/levelp/AnyXException.java)

Если все скобки уже поставлены
Можем ли поставить (дописать в конец)
открывающую скобку?
Можем ли поставить закрывающую скобку?
[00_HomeWork_Done/src/main/java/levelp/Brackets.java](00_HomeWork_Done/src/main/java/levelp/Brackets.java)

Если сумма кончилась
x - очередное слагаемое
Записываем очередное слагаемое
Вызываем рекурсивно себя изменяя параметры
[00_HomeWork_Done/src/main/java/levelp/Decomposition.java](00_HomeWork_Done/src/main/java/levelp/Decomposition.java)

Практика: Решение квадратного уравнения
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

Первый (самый простой) тест
``` java
    @Test
    public void testSimple() {
        // assertEquals( ожидаемое_значение, вычисленное значение )
        //       сообщение_если_значения_не_равны, ..., ...
        // a*x^2 + b*x + c = 0
        assertArrayEquals("x^2 = 0",
                new double[]{0.0},
                SquareEq.solve(1.0, 0.0, 0.0), SquareEq.DELTA);
    }
```

Рассматриваем случай, когда два решения уравнения
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

Тестируем вырожденный случай: a = 0, b = 0
``` java
    @Test
    public void zeroAZeroB() {
        assertArrayEquals("1 = 0",
                new double[]{},
                SquareEq.solve(0.0, 0.0, 1.0), DELTA);
    }
```

Вырожденный случай: a = 0, b = 0, c = 0
Ожидаемое исключение
``` java
    @Test(expected = AnyXException.class)
    public void zeroAZeroBZeroC() {
        assertArrayEquals("0 = 0",
                new double[]{},
                SquareEq.solve(0.0, 0.0, 0.0), DELTA);
    }
```

[00_HomeWork_Done/src/test/java/SquareEqTest.java](00_HomeWork_Done/src/test/java/SquareEqTest.java)

Операторы и структура кода. Исключения
--------------------------------------

* RuntimeException
* Exception

System.setOut(new PrintStream("my.log"));
throw new MyRuntimeException("xx", 2, 1);
[01_Exceptions/src/main/java/ExceptionDemo.java](01_Exceptions/src/main/java/ExceptionDemo.java)

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

[01_Exceptions/src/main/java/MyException.java](01_Exceptions/src/main/java/MyException.java)

java.lang.ArithmeticException: / by zero
[01_Exceptions/src/test/java/DivideByZero.java](01_Exceptions/src/test/java/DivideByZero.java)

....
...
throw ex;
ex.printStackTrace();
....
...
[01_Exceptions/src/test/java/Exceptions.java](01_Exceptions/src/test/java/Exceptions.java)

Enum - перечисления
-------------------

Программируя мы часто сталкиваемся с необходимостью
ограничить множество допустимых значений для
некоторого типа данных.
Например, день недели может иметь 7 разных значений,
месяц в году - 12, а время года - 4 значения.
Для решения подобных задач во многих языках
программирования со статической типизацией
предусмотрен специальный тип данных - перечисление (enum).

В Java перечисления появилось с версии 1.

**Когда использовать перечисления?**

Использовать если: меняется или может поменяться
логика работы программы при добавлении новых значений.





case NEW_DAY:
System.out.println("!!! Новое поведение !!!");
[01_enum/src/main/java/EnumSwitch.java](01_enum/src/main/java/EnumSwitch.java)

Времена года
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
        System.out.println("Конструктор: " + name);
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

Пол: мужской, женский
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

[01_enum/src/main/java/SolarSystemPlanet.java](01_enum/src/main/java/SolarSystemPlanet.java)

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

[01_enum/src/test/java/SolarSystemTest.java](01_enum/src/test/java/SolarSystemTest.java)

Параметры методов. Преобразование типов. Boxing, Unboxing
---------------------------------------------------------
Boxing
Unboxing
immutable
[02_BoxingUnboxing/src/main/java/BoxingDemo.java](02_BoxingUnboxing/src/main/java/BoxingDemo.java)

Считать количество цифр
Считать количество букв
+ Подсчитывать количество букв A,
B, C... всех символов таблицы UTF-8
[02_BoxingUnboxing/src/test/java/CountChars.java](02_BoxingUnboxing/src/test/java/CountChars.java)

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

[02_StringBuilder/src/test/java/StringBuilderTest.java](02_StringBuilder/src/test/java/StringBuilderTest.java)

Результат для строки
Результат для StringBuilder
Результат для StringBuffer
@Ignore
Runtime.getRuntime().gc();
Runtime.getRuntime().gc();
[02_StringBuilder/src/test/java/StringVsStringBuilder.java](02_StringBuilder/src/test/java/StringVsStringBuilder.java)

File f1 = new File(STRING_FILENAME);
[02_StringBuilder/src/test/java/StringsTest.java](02_StringBuilder/src/test/java/StringsTest.java)

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

String s;
System.out.println("s = " + s);
[03_Reflection/src/main/java/simple/MyClass.java](03_Reflection/src/main/java/simple/MyClass.java)

String newStr = new String();
Создание нового экзепляра
[I
Загрузка класса
Создаём экземпляр класса
double[] obj = (double[]) cDoubleArray.newInstance();
Object string = cStringArray.newInstance();
[03_Reflection/src/test/java/classes/ReflectionTest.java](03_Reflection/src/test/java/classes/ReflectionTest.java)

По объекту получаем класс
Значение и имя класса
Получаем класс по имени класса
Пытаемся создать экземпляр класса
Выводим экземпляр класса
[03_Reflection/src/test/java/classes/ShowClass.java](03_Reflection/src/test/java/classes/ShowClass.java)

Получаем метаинформацию о классе
Получаем все поля объявленные в классе
кроме унаследованных
Разрешаем доступ к private-полю
field.set(obj, newValue);
[03_Reflection/src/test/java/fields/ShowAllFields.java](03_Reflection/src/test/java/fields/ShowAllFields.java)

Создаю экземпляр класса
Вызов статического метода
method.invoke(null, 2);
вызов метода с 3 параметрами
method.invoke(obj, 1, 2, 3);
Создаю экземпляр класса
Вызов статического метода
method.invoke(null, 2);
вызов метода с 3 параметрами
method.invoke(obj, 1, 2, 3);
[03_Reflection/src/test/java/methods/CallAllMethods.java](03_Reflection/src/test/java/methods/CallAllMethods.java)

Чтение из файла
[04_File/src/test/java/SaveLoadTest.java](04_File/src/test/java/SaveLoadTest.java)

try{
//...
} catch (Exception ex){
out.close();
}
Point newPoint = new Point();
Получаем название класса
Получаем метаданные класса
JVM его загружает если ещё не загрузила
+ возвращает метаданные
Создаем экземпляр класса
Считываем значения всех полей класса
Включаем доступ к не-public полям
out.println(field.get(object).toString());
newPoint.x = in.nextDouble();
newPoint.y = in.nextDouble();
Получаем метаданные класса
В первой строчке название класса
Выводим значения всех полей
Включаем доступ к не-public полям
out.println(point.x);
out.println(point.y);
[04_File/src/test/java/TextFileSaveLoad.java](04_File/src/test/java/TextFileSaveLoad.java)

arrayList.ensureCapacity(100);
[05_DataStruct/src/main/java/ArrayListSizeDemo.java](05_DataStruct/src/main/java/ArrayListSizeDemo.java)

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
        T value = (T) data[numberOfElements - 1];
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

Сортировка
Возвращаем результат
[05_DataStruct/src/main/java/Sort.java](05_DataStruct/src/main/java/Sort.java)

Память (виды памяти)
--------------------
* Статическая
* Динамическая - **new** / GC.
* Стек - локальные переменные в функциях и методах.

Даём "совет" сборщику мусора выполнить сборку
Количество частей
Одна часть в байтах
Сохранять ли ссылки на выделяемые куски памяти
runtime - объект для обращения к JVM
Заводим массив в динамической памяти для хранения ссылок
Заводим в динамической памяти массив
Заполняем его значениями
Номер массива
if (saveReferences) {
allRefs[i] = intArray;
}
Пауза в 10 миллисекунд
pause();
[05_Memory/src/main/java/A_Memory.java](05_Memory/src/main/java/A_Memory.java)

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

[05_Memory/src/main/java/Constructors.java](05_Memory/src/main/java/Constructors.java)

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

Блок инициализации (выполняется перед
конструктором)
[05_Memory/src/main/java/InitOrderPuzzle.java](05_Memory/src/main/java/InitOrderPuzzle.java)

Инициализация выполняется последовательно
Строчка за строчкой
Сначала всё со словом static
Потом инициализация не static переменных
counter = 0;
[05_Memory/src/main/java/StaticConstructor.java](05_Memory/src/main/java/StaticConstructor.java)



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

[05_Object_Equals_hashCode_toString/src/main/java/ThisLink.java](05_Object_Equals_hashCode_toString/src/main/java/ThisLink.java)

Object, toString. Сравнение объектов: equals, hashCode. Контракт между equals и hashCode
----------------------------------------------------------------------------------------
o1.equals(o2) == true  =>  o1.hashCode() == o2.hashCode()
Точка
Координаты
При вычислении hashCode должны участвовать те же поля что и в equals
http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java
Wrong: return 1;
[05_Object_Equals_hashCode_toString/src/main/java/geometry/Point.java](05_Object_Equals_hashCode_toString/src/main/java/geometry/Point.java)

Круг
``` java
public class Circle extends Shape {
    public Circle(String name, double radius) {
        super(name);
    }

    @Override
    void show() {
        // TODO: показать
    }
}
```

[06_Shapes/src/main/java/Circle.java](06_Shapes/src/main/java/Circle.java)

Точка
``` java
public class Point extends Shape {
    private double x, y;

    public Point(String name, double x, double y) {
        super(name);
        this.x = x;
        this.y = y;
    }

    @Override
    void show() {
        System.out.println(toString());
    }

    public String toString() {
        return name + " (" + x + "; " + y + ")";
    }
}
```

[06_Shapes/src/main/java/Point.java](06_Shapes/src/main/java/Point.java)

Прямоугольник
``` java
public class Rectangle extends Shape {
    public Rectangle(String name, Point leftTop, Point rightBottom) {
        super(name);
    }

    @Override
    void show() {
       // TODO: показать
    }
}
```

[06_Shapes/src/main/java/Rectangle.java](06_Shapes/src/main/java/Rectangle.java)

Фигура
``` java
public abstract class Shape {
    protected final String name;

    public Shape(String name) {
        this.name = name;
    }

    /**
     * Имя фигуры и все параметры
     */
    abstract void show();
}
```

[06_Shapes/src/main/java/Shape.java](06_Shapes/src/main/java/Shape.java)

Треугольник
``` java
public class Triangle extends Shape {
    public Triangle(String name, Point p1, Point p2, Point p3) {
        super(name);
    }

    @Override
    void show() {
        // TODO: показать
    }
}
```

[06_Shapes/src/main/java/Triangle.java](06_Shapes/src/main/java/Triangle.java)

Тестирование работы с нескольникими фигурами
``` java
        // Треугольник
        Triangle triangle = new Triangle("Треугольник 1",
                new Point("A", 1, 2),
                new Point("B", 4, 5),
                new Point("C", 6, 7)
        );

        Shape[] shapes = {
                triangle,
                new Point("Просто точка", 1, 2),
                new Rectangle("Прямоугольник",
                        new Point("Левый верхний угол", 10, 20),
                        new Point("Правый нижний угол", 100, 230)
                ),
        };

        for (Shape shape : shapes) {
            shape.show();
        }
        // assertEquals();
```

[06_Shapes/src/test/java/ShapesTest.java](06_Shapes/src/test/java/ShapesTest.java)

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

[07_JUnit_Void/src/main/java/MyClass.java](07_JUnit_Void/src/main/java/MyClass.java)

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
Печатаем имя класса в файл
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
**Как присвоить значение полю произвольного типа?**

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

null - для статических методов
[09_ReflectionTask_SaveLoad/src/main/java/FileStorage.java](09_ReflectionTask_SaveLoad/src/main/java/FileStorage.java)

a[0] = 10;
Если я перебираю элементы массива используя индексы
то я могу модифицировать элементы массива
[09_ReflectionTask_SaveLoad/src/main/java/foreach/ForEach.java](09_ReflectionTask_SaveLoad/src/main/java/foreach/ForEach.java)

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
[10_URL/src/test/java/URLTest.java](10_URL/src/test/java/URLTest.java)

Графические (GUI) библиотеки
----------------------------
1. AWT - Abstract Window Toolkit
2. Swing -
3. JavaFX

SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:");
Сколько секунд прошло
System.out.println("inSecond: " + inSeconds);
[11_Timer/src/main/java/MainForm.java](11_Timer/src/main/java/MainForm.java)

Создаём окно и задаём ему заголовок
Создаём класс, соответствующий нашей форме
Основную панель помещаем внутрь окна
Размещение компонент внутри окна
рекурсивное определение размеров компонент
Когда закрываем этот frame => закроется всё приложение
Обычно задаётся для "основного" окна приложения
Показываем окно
[15_SwingDemo2/src/main/java/MainForm.java](15_SwingDemo2/src/main/java/MainForm.java)

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

x = 2;
a = x+x;
b = 2*x + x + x;
a              b
[98_XORTest/src/main/java/Main.java](98_XORTest/src/main/java/Main.java)

Стек и очередь на Java (Generics)
---------------------------------

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
=====================================

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


JVMS

Книга


﻿Домашнее задание
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


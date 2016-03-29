<!-- doc.py -->
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
[src/main/java/A_Memory.java](src/main/java/A_Memory.java)

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

[src/main/java/Constructors.java](src/main/java/Constructors.java)

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

[src/main/java/DynMemoryClass.java](src/main/java/DynMemoryClass.java)

Блок инициализации (выполняется перед
конструктором)
[src/main/java/InitOrderPuzzle.java](src/main/java/InitOrderPuzzle.java)

Инициализация выполняется последовательно
Строчка за строчкой
Сначала всё со словом static
Потом инициализация не static переменных
counter = 0;
[src/main/java/StaticConstructor.java](src/main/java/StaticConstructor.java)


// Конструкторы
// ------------
// Конструктор - метод, который вызывается при созданни объекта.
//-->
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
//<--

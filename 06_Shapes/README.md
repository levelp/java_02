<!-- doc.py -->
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

[src/main/java/Circle.java](src/main/java/Circle.java)

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

[src/main/java/Point.java](src/main/java/Point.java)

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

[src/main/java/Rectangle.java](src/main/java/Rectangle.java)

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

[src/main/java/Shape.java](src/main/java/Shape.java)

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

[src/main/java/Triangle.java](src/main/java/Triangle.java)

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

[src/test/java/ShapesTest.java](src/test/java/ShapesTest.java)


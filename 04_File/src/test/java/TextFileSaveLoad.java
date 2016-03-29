import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Locale;
import java.util.Scanner;

public class TextFileSaveLoad extends Assert {
    private static final double EPS = 1e-15;

    @Test
    public void testSaveLoad() throws Exception {
        String fileName = "point.txt";
        Point point = new Point(2.3, 4.5);
        assertEquals(2.3, point.x, EPS);
        assertEquals(4.5, point.y, EPS);

        save(fileName, point);

        Point newPoint = (Point) load(fileName);
        assertEquals(point.x, newPoint.x, EPS);
        assertEquals(point.y, newPoint.y, EPS);

        QuadEq quadEq = new QuadEq();
        quadEq.a = 1;
        quadEq.b = 2.0;
        quadEq.c = 3.0;
        String fileNameEq = "eq.txt";

        save(fileNameEq, quadEq);

        QuadEq quadEq2 = (QuadEq) load(fileNameEq);
        assertEquals(quadEq.a, quadEq2.a, EPS);
        assertEquals(quadEq.b, quadEq2.b, EPS);
        assertEquals(quadEq.c, quadEq2.c, EPS);

        //try{
        //    //...
        //} catch (Exception ex){
        //    out.close();
        //}
    }

    private Object load(String fileName) throws FileNotFoundException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        try (Scanner in = new Scanner(new FileInputStream(fileName))) {
            in.useLocale(Locale.US);

            // Point newPoint = new Point();
            // Получаем название класса
            String className = in.nextLine();
            // Получаем метаданные класса
            // JVM его загружает если ещё не загрузила
            // + возвращает метаданные
            Class cls = Class.forName(className);
            // Создаем экземпляр класса
            Object object = cls.newInstance();
            // Считываем значения всех полей класса
            for (Field field : cls.getDeclaredFields()) {
                // Включаем доступ к не-public полям
                if ((field.getModifiers() & Modifier.PUBLIC) == 0) {
                    field.setAccessible(true);
                }
                if (field.getType().equals(Double.class) ||
                        field.getType().equals(Double.TYPE))
                    field.set(object, Double.valueOf(in.next()));
                //out.println(field.get(object).toString());
            }
            return object;
            //newPoint.x = in.nextDouble();
            //newPoint.y = in.nextDouble();
        }
    }

    private void save(String fileName, Object object) throws
            FileNotFoundException, IllegalAccessException {
        try (PrintStream out = new PrintStream(fileName)) {
            // Получаем метаданные класса
            Class cls = object.getClass();
            // В первой строчке название класса
            out.println(cls.getName());
            // Выводим значения всех полей
            for (Field field : cls.getDeclaredFields()) {
                // Включаем доступ к не-public полям
                if ((field.getModifiers() & Modifier.PUBLIC) == 0) {
                    field.setAccessible(true);
                }
                out.println(field.get(object).toString());
            }
            // out.println(point.x);
            // out.println(point.y);
        } // out.close();
    }

    static private class Point {
        private double x;
        private double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public Point() {
        }
    }

    private static class QuadEq {
        public double a, b, c;

        public QuadEq() {
        }
    }
}

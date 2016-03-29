import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

/**
 * Сохранение и загрузка
 */
public class SaveLoadTest extends Assert {
    public static final String S = "Привет, мир!";

    /**
     * Сохранение и загрузка файла
     */
    @Test
    public void testSaveLoad() throws Exception {
        File file = new File("data.txt");
        try (PrintStream ps = new PrintStream(
                new FileOutputStream(file), true, "UTF-8")) {
            ps.println(S);
            ps.println(2 * 2);
        }
        // Чтение из файла
        try (Scanner scanner = new Scanner(
                new InputStreamReader(
                        new FileInputStream(file), "UTF-8"))) {
            String line = scanner.nextLine();
            assertEquals(S, line);
            int i = scanner.nextInt();
            assertEquals(4, i);
            double d = scanner.nextDouble();
        }
    }

    /**
     * Сохранить точку в файл без Reflection API
     */
    @Test
    public void testSaveLoadPoint() throws FileNotFoundException {
        class Point {
            double x, y;
        }

        Point p = new Point();
        p.x = 10;
        p.y = 20;
        try (PrintWriter writer = new PrintWriter("point.txt")) {
            writer.println(p.x);
            writer.println(p.y);
        }
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 */
public class FileStorage {
    /**
     * Сохранение объекта в текстовый файл
     *
     * @param obj      объект
     * @param fileName имя файла
     */
    public static void save(Object obj, String fileName)
            throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(
                fileName
        );
        Class c = obj.getClass();
        writer.println(c.getName());
        // TODO: реализовать
        writer.close();
    }

    /**
     * Загрузка объекта из файла
     *
     * @param fileName имя файла
     * @return объект
     */
    public static Object load(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        // Считываем имя класса
        String className = scanner.nextLine();

        scanner.close();
        return null;
    }
}

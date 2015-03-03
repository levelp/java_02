import java.io.FileNotFoundException;
import java.io.PrintWriter;

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
    public static Object load(String fileName) {
        return null;
    }
}

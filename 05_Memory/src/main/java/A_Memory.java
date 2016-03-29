/**
 * Демонстрация работы Garbage Collector
 */
public class A_Memory {
    public static void main(String[] args) {
        // Даём "совет" сборщику мусора выполнить сборку
       /* Runtime.getRuntime().gc();
        try {
            Process pr = Runtime.getRuntime().exec("dir");
            try (Scanner sc = new Scanner(pr.getInputStream())) {
                while (sc.hasNext()) {
                    System.out.println(sc.nextLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return; */

        final int KB = 1024; // Килобайт
        final int MB = KB * 1024; // Мегабайт
        // Количество частей
        final int PARTS = 100000;
        // Одна часть в байтах
        final int ONE_PART_SIZE = 64 * MB;
        System.out.println("ONE_PART_SIZE = " + ONE_PART_SIZE);

        // Сохранять ли ссылки на выделяемые куски памяти
        boolean saveReferences = false;

        // runtime - объект для обращения к JVM
        Runtime runtime = Runtime.getRuntime();

        // Заводим массив в динамической памяти для хранения ссылок
        byte[][] allRefs = new byte[PARTS][];
        for (int i = 0; i < PARTS; ++i) {
            // Заводим в динамической памяти массив
            byte[] intArray = new byte[ONE_PART_SIZE];
            // Заполняем его значениями
            for (int j = 0; j < intArray.length; ++j)
                intArray[j] = (byte) j;
            // Номер массива
            System.out.println("i = " + i);
            //if (saveReferences) {
            //  allRefs[i] = intArray;
            //}

            long used = runtime.totalMemory() - runtime.freeMemory();
            System.out.println("Used: " + used / KB + " (" + used / MB + " Mb) "
                    + " from " + runtime.totalMemory() / MB);
            // Пауза в 10 миллисекунд
            //pause();
        }
    }

    private static void pause() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

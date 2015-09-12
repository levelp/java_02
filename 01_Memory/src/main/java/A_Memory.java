/**
 * Демонстрация работы Garbage Collector
 */
public class A_Memory {

    public static void main(String[] args) {
        int kb = 1024;
        int mb = kb * 1024;

        boolean saveReferences = false;

        // Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();

        // Заводим массив в динамической памяти
        int[][] allRefs = new int[100000][];
        for (int i = 0; i < 100000; ++i) {
            // Пауза в 10 миллисекунд
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Заводии в динамической памяти массив
            int[] intArray = new int[10000000];
            for (int j = 0; j < intArray.length; ++j)
                intArray[j] = j;
            // Номер массива
            System.out.println("i = " + i);
            if (saveReferences) {
                allRefs[i] = intArray;
            }

            long used = runtime.totalMemory() - runtime.freeMemory();
            System.out.println("Used: " + used / kb + " (" + used / mb + " Mb) "
                    + " from " + runtime.totalMemory() / mb);
        }
    }
}

/**
 * Стек, в котором будут хранится элементы
 * с классом (типом) T
 */
//-->
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
        T value = (T) data[data.length - 1];
        numberOfElements--;
        if (numberOfElements * 2 < data.length) {
            Object[] newData = new Object[data.length - 1];
            System.arraycopy(data, 0, newData, 0, data.length - 1);
            data = newData;
        }
        return value;
    }
}
//<--
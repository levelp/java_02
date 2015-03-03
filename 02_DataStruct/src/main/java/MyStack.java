/**
 * Стек, в котором будут хранится элементы
 * с классом (типом) T
 */
//-->
public class MyStack<T> {
    // Количество элементов
    int counter = 0;
    // Тут реально будем хранить данные
    Object[] data = new Object[0];

    /**
     * Добавить значение в стек
     *
     * @param value значение
     */
    public void push(T value) {
        ++counter;
        if (data.length < counter) {
            Object[] newData = new Object[counter * 2];
            // Копируем массив встроенными средствами
            System.arraycopy(data, 0, newData, 0, data.length);
            // Копируем поэлементно
            for (int i = 0; i < data.length; ++i)
                newData[i] = data[i];
            // Ссылку на новый массив сохраняем
            data = newData;
        }
        // Новое значение
        data[counter - 1] = value;
    }

    public int size() {
        return counter;
    }

    /**
     * Получить значение с вершины стека
     *
     * @return значение
     */
    @SuppressWarnings("unchecked")
    public T pop() {
        T value = (T) data[data.length - 1];
        counter--;
        if (counter * 2 < data.length) {
            Object[] newData = new Object[data.length - 1];
            System.arraycopy(data, 0, newData, 0, data.length - 1);
            data = newData;
        }
        return value;
    }
}
//<--
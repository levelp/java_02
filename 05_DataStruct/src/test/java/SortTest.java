import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Сортировки
 */
public class SortTest {

    @Test(timeout = 100)
    public void testSortInt() {
        Integer[] ints = {2, 3, 1, 10};
        Sort<Integer> sortInt = new Sort<>(ints);
        assertArrayEquals(new Integer[]{1, 2, 3, 10},
                sortInt.sorted());

        String[] strings = {"hello", "my", "smart", "group"};
        Sort<String> stringSort = new Sort<>(strings);
        assertArrayEquals(new String[]{"group", "hello", "my", "smart"},
                stringSort.sorted());
    }
}

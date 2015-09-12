import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 */
public class ListTest extends Assert {

    @Test
    public void testList() throws IOException {
        MyList<String> myList = new MyList<String>();
        assertNull(myList.root);
        assertEquals(0, myList.size());

        myList.addToBegin("Test");
        assertEquals("Test", myList.root.value);
        assertNull(myList.root.next);
        assertEquals(1, myList.size());

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream saveOut = System.out;
        System.setOut(new PrintStream(stream));
        myList.show();
        stream.close();
        // Восстанавливаем вывод на консоль
        System.setOut(saveOut);
        assertEquals("Test\r\n", stream.toString());

        myList.addToEnd("Элемент_в_конце");
        assertEquals("Элемент_в_конце", myList.root.next.value);
        assertNull(myList.root.next.next);
        assertEquals(2, myList.size());

        myList.show();
    }

    /**
     * Теперь тот же список с целыми числами
     */
    @Test
    public void testIntegers() {
        MyList<Integer> myList = new MyList<Integer>();
        myList.addToBegin(3);
        myList.addToEnd(4);
    }
}

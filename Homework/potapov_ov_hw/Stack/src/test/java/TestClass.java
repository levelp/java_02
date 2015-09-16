import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by potapov.ov on 16.09.2015.
 */
public class TestClass {

    @Test
    public void checkStack(){
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop().intValue());
        assertEquals(2, stack.pop().intValue());
        assertEquals(1, stack.pop().intValue());
        assertTrue("���� ����", stack.isEmpty());

    }


    @Test
    public void checkQueue(){
        Queue <String> queue = new Queue<>();
        queue.put("Hello");
        queue.put("world");
        assertEquals("Hello", queue.get());
        assertEquals("world", queue.get());
        assertTrue("������� �����", queue.isEmpty());

    }
}

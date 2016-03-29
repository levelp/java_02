import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Использование ассоциативного массива
 */
public class HashMapTest extends Assert {

    @Test
    public void testHashMap() {
        HashMap<Integer, String> hashMap = new LinkedHashMap<>();

        hashMap.put(1, "один");
        hashMap.put(2, "два");

        hashMap.get(2);

        assertEquals(2, hashMap.size());
    }
}

import org.junit.Test;

import static org.junit.Assert.*;

public class TypesDemoTest {
    @Test
    public void testBoxing() {

        double d = 1.2;

        assertEquals(1.4, Double.parseDouble("1.4"), 0.0001);

        assertEquals(1.7976931348623157E308, Double.MAX_VALUE, 1);
        assertEquals(4.9E-324, Double.MIN_VALUE, 0.000001);
        assertEquals("NaN", Double.toString(Double.NaN));

        assertTrue(Boolean.parseBoolean("true"));
        assertFalse(Boolean.parseBoolean("false"));

        Integer k = 65535; // FFFF
        assertEquals("ffff", Integer.toHexString(k));
        assertEquals("4d2", Integer.toHexString(1234));
        assertEquals("7296", Integer.toHexString(29334));
        assertEquals("86e4", Integer.toHexString(34532));

        Boolean b = true;


        Character c = 'A';
    }
}

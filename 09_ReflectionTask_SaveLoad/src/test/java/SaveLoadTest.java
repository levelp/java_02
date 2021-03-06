import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование работы FileStorage
 */
public class SaveLoadTest {
    @Test
    public void saveLoadPoint() throws Exception {
        Point p = new Point(1.0, 2.2);
        FileStorage.save(p, "p.txt");
        Point p2 = (Point) FileStorage.load("p.txt");
        assertEquals(p.x, p2.x, 1e-10);
        assertEquals(p.y, p2.y, 1e-10);
    }

    @Test
    public void saveLoadAllCases() throws Exception {
        AllCases allCases = new AllCases();
        FileStorage.save(allCases, "all.txt");
        AllCases p2 = (AllCases) FileStorage.load("all.txt");
        assertEquals(allCases.publicInt, p2.publicInt);
    }
}

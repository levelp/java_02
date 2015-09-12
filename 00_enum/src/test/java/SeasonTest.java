import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by gtee on 12.09.2015.
 */
public class SeasonTest {
    @Test
    public void test() {
        String fromDB = "WINTER";
        Season season = Season.valueOf(fromDB);
        assertNotNull(season);
        System.out.println("season.name = " + season.name);
    }
}

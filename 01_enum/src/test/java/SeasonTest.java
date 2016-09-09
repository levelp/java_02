import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Демонстрация работы с enum - времена года
 */
public class SeasonTest {
    @Test
    public void test() {
        String fromDB = "WINTER";
        Season season = Season.valueOf(fromDB);
        assertNotNull(season);
        System.out.println("season = " + season);
        System.out.println("season.name = " + season.name);

        String seasons = "WINTER SUMMER SPRING";
        String[] array = seasons.split(" ");
        for (String s : array) {
            Season curSeason = Season.valueOf(s);
            System.out.println("Сезон: " + curSeason.name);
            switch (curSeason) {
                case WINTER:
                    System.out.println("Потеплее оденься!");
                    break;
                case SPRING:
                    System.out.println("!!!!");
                    break;
                case SUMMER:
                    System.out.println("Поехали загорать!");
                    break;
                case AUTUMN:
                    System.out.println("На улице мокро");
                    break;
            }

            switch (curSeason) {
                case WINTER:
                case SPRING:

                    break;
                case SUMMER:
                    break;
                default:

            }
        }
    }
}

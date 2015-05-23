import java.util.Random;

// Использование перечислений:
public class SolarSystemTest {
    public static void main(String[] args) {
        // Присваивать можем только одно из значений enum'а
        //-->
        SolarSystemPlanet planet = SolarSystemPlanet.EARTH;

        needColonization(planet);
        //<--

        //-->
        Random random = new Random();

        Sex sex = random.nextInt(2) == 0 ? Sex.FEMALE : Sex.MALE;
        if (sex == Sex.MALE) {
            System.out.println("Мужской");
        } else {
            System.out.println("Женский");
        }
        //<--

        String s = "MALE";
        Sex sex1 = Sex.valueOf(s);
        Sex sex2 = Sex.MALE;
    }

    private static boolean needColonization(SolarSystemPlanet planet) {
        switch (planet) {
            case EARTH:
                System.out.println(planet + " - надо подумать :)");
                return false;
            case MARS:
                return true;
            case VENUS:
                return false;
            default:
                return false;
        }
    }
}

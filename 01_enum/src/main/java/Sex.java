import java.util.Random;

// Пол: мужской, женский
//-->
public enum Sex {
    // 0 1 2
    MALE, FEMALE, UNKNOWN;

    public static void main(String[] args) {
        Sex x = new Random().nextInt(2) == 0 ?
                MALE : FEMALE;
        switch (x) {
            case MALE:
                System.out.println("x = " + x);
                break;
            case FEMALE:
                System.out.println("!!!");
                break;
        }
    }
}
//<--

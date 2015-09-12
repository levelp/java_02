import java.util.Random;

// Пол: мужской, женский
//-->
public enum Sex {
    MALE, FEMALE;

    public static void main(String[] args) {
        Sex x = new Random().nextInt(2) == 0 ?
                MALE : FEMALE;
        switch (x) {
            case MALE:
                break;
            case FEMALE:
                break;
        }
    }
}
//<--

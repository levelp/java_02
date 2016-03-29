/**
 * Enum + switch
 */
public class EnumSwitch {
    public static void main(String[] args) {
        EnumTest firstDay = new EnumTest(Day.MONDAY);
        firstDay.tellItLikeItIs();
        EnumTest thirdDay = new EnumTest(Day.WEDNESDAY);
        thirdDay.tellItLikeItIs();
        EnumTest fifthDay = new EnumTest(Day.FRIDAY);
        fifthDay.tellItLikeItIs();
        EnumTest sixthDay = new EnumTest(Day.SATURDAY);
        sixthDay.tellItLikeItIs();
        EnumTest seventhDay = new EnumTest(Day.SUNDAY);
        seventhDay.tellItLikeItIs();
    }

    public enum Day {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
        THURSDAY, FRIDAY, SATURDAY //, NEW_DAY
    }

    public static class EnumTest {
        Day day;

        public EnumTest(Day day) {
            this.day = day;
        }

        public void tellItLikeItIs() {
            switch (day) {
                case MONDAY:
                    System.out.println("Mondays are bad.");
                    break;

                case FRIDAY:
                    System.out.println("Fridays are better.");
                    break;

                case SATURDAY:
                case SUNDAY:
                    System.out.println("Weekends are best.");
                    break;

                //case NEW_DAY:
                //   System.out.println("!!! Новое поведение !!!");

                default:
                    System.out.println("Midweek days are so-so.");
                    break;
            }
        }

    }
}

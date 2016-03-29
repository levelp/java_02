// Времена года
//-->
enum Season {
    WINTER("Зима"),
    SPRING("Весна"),
    SUMMER("Лето"),
    AUTUMN("Осень");

    public final String name;

    /**
     * Конструктор
     *
     * @param name Название сезона по-русски
     */
    Season(String name) {
        System.out.println("Конструктор: " + name);
        this.name = name;
        // Про final
        final int[] intArray = new int[1000];
        intArray[1] = 22;
        System.out.println("intArray[4] = " + intArray[4]);
        //intArray = new int[100]; //
        intArray[0] = 100;
        final int CONST = 100;
        //CONST = 1000; // Не можем менять значение
    }
}
//<--
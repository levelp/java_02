<!-- doc.py -->
Enum - перечисления
-------------------

Программируя мы часто сталкиваемся с необходимостью
ограничить множество допустимых значений для
некоторого типа данных.
Например, день недели может иметь 7 разных значений,
месяц в году - 12, а время года - 4 значения.
Для решения подобных задач во многих языках
программирования со статической типизацией
предусмотрен специальный тип данных - перечисление (enum).

В Java перечисления появилось с версии 1.

**Когда использовать перечисления?**

Использовать если: меняется или может поменяться
логика работы программы при добавлении новых значений.





case NEW_DAY:
System.out.println("!!! Новое поведение !!!");
[src/main/java/EnumSwitch.java](src/main/java/EnumSwitch.java)

Времена года
``` java
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
```

[src/main/java/Season.java](src/main/java/Season.java)

Пол: мужской, женский
``` java
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
```

[src/main/java/Sex.java](src/main/java/Sex.java)

Элементы перечисления - экземпляры enum-класса,
доступные статически.
Планеты солнечной системы
``` java
public enum SolarSystemPlanet {
    EARTH("Земля"),
    MARS("Марс"),
    VENUS("Венера");

    private final String name;

    SolarSystemPlanet(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Планета \"" + name + "\"";
    }
}
```

[src/main/java/SolarSystemPlanet.java](src/main/java/SolarSystemPlanet.java)

Использование перечислений:
Присваивать можем только одно из значений enum'а
``` java
        SolarSystemPlanet planet = SolarSystemPlanet.EARTH;

        needColonization(planet);
```

``` java
        Random random = new Random();

        Sex sex = random.nextInt(2) == 0 ? Sex.FEMALE : Sex.MALE;
        if (sex == Sex.MALE) {
            System.out.println("Мужской");
        } else {
            System.out.println("Женский");
        }
```

[src/test/java/SolarSystemTest.java](src/test/java/SolarSystemTest.java)


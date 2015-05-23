<!-- doc.py -->
Enum - перечисления
-------------------

Программируя мы часто сталкиваемся с необходимостью ограничить множество допустимых значений для некоторого типа данных.
Например, день недели может иметь 7 разных значений, месяц в году - 12, а время года - 4.
Для решения подобных задач во многих языках программирования со статической типизацией предусмотрен специальный тип данных - перечисление (enum).

В Java перечисления появилось с версии 1.5.



Времена года
``` java
enum Season { WINTER, SPRING, SUMMER, AUTUMN }
```

[src/main/java/Season.java](src/main/java/Season.java)

Пол: мужской, женский
``` java
public enum Sex {
    MALE, FEMALE
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


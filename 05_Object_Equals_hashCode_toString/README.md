<!-- doc.py -->


Использование ссылки this
-------------------------
``` java
        // Создаём журнал
        Journal journal = new Journal();

        // И двух подписчиков
        User A = new User("Петя");
        User B = new User("Вася");
        A.subscribe(journal);
        B.subscribe(journal);

        // Два выпуска журнала
        journal.release("Сентябрь 2014");
        journal.release("Октябрь 2014");
```

``` java
        // Когда журнал выходит
        public void release(String name) {
            for (User user : users) {
                // Он отправляется всем подписчикам
                user.send(name);
            }
        }
```

[src/main/java/ThisLink.java](src/main/java/ThisLink.java)

Object, toString. Сравнение объектов: equals, hashCode. Контракт между equals и hashCode
----------------------------------------------------------------------------------------
o1.equals(o2) == true  =>  o1.hashCode() == o2.hashCode()
Точка
Координаты
При вычислении hashCode должны участвовать те же поля что и в equals
http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java
Wrong: return 1;
[src/main/java/geometry/Point.java](src/main/java/geometry/Point.java)


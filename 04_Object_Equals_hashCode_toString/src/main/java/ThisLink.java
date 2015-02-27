import java.util.ArrayList;
import java.util.List;

//
// Использование ссылки this
// -------------------------
public class ThisLink {

    public static void main(String[] args) {
        //-->
        // Создаём журнал
        Journal journal = new Journal();

        // И двух подписчиков
        User A = new User("Петя");
        A.subscribe(journal);
        B.subscribe(journal);
        B.subscribe(journal);

        // Два выпуска журнала
        journal.release("Сентябрь 2014");
        journal.release("Октябрь 2014");
        //<--
    }
   
    static class Journal {
        List<User> users = new ArrayList<User>();

        //-->
        // Когда журнал выходит
        public void release(String name) {
            for (User user : users) {
                // Он отправляется всем подписчикам
                user.send(name);
            }
        }
        //<--

        public void add(User user) {
            users.add(user);
        }
    }

    static class User {
        private final String name;

        public User(String name) {
            this.name = name;
        }

        public void subscribe(Journal journal) {
            journal.add(this);
        }

        public void send(String name) {
            System.out.println("Пользователь " + this.name +
                    " получил журнал " + name);
        }
    }
}

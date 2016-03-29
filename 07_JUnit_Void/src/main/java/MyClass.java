/// Тестирование что метод пишет на консоль
/// ---------------------------------------
// Пусть есть класс, который что-то выводит на консоль
//-->
public class MyClass {

    public void show() {
        System.out.println(getText());
    }

    String getText() {
        return "SHOW";
    }
}
//<--

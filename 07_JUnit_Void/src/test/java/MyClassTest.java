import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;

// И есть тест, который должен проверить что класс выводит на консоль
public class MyClassTest {

    @Test
    public void voidMethod() throws IOException {
        // Сравнить строку с заданной очень просто
        //-->
        MyClass myClass = new MyClass();
        assertEquals("SHOW", myClass.getText());
        //<--

        // А для сравнения вывода можно перехватить вывод на консоль
        //-->
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            PrintStream save = System.out;
            System.setOut(new PrintStream(stream));
            // Вызываем тестируемый метод
            myClass.show();
            //
            System.setOut(save);

            String result = stream.toString().trim();
            assertEquals("SHOW", result); // Сравниваем
        }
        //<--
    }
}

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * Работа с URL
 */
public class URLTest extends Assert {

    // Разбор обычных URL
    @Test
    public void testUrlParsing() throws IOException {
        //-->
        URL url = new URL("http://ya.ru/");
        assertEquals("Протокол", "http", url.getProtocol());
        assertEquals("Доменное имя сайта", "ya.ru", url.getHost());
        assertEquals("Путь от корня сайта", "/", url.getPath());
        //<--
    }

    /**
     * Нестандартные протоколы
     *
     * @throws IOException
     */
    @Test
    public void testMyProtocolParsing() throws IOException {
        // Добавляем свой обработчик нестандартных протоколов
        URL.setURLStreamHandlerFactory(protocol -> {
            // Вывод протокола для отладки:
            // System.out.println("protocol = " + protocol);
            // Для протокола chrome://
            if (protocol.equals("chrome")) {
                return new URLStreamHandler() {
                    @Override
                    protected URLConnection openConnection(URL url) throws IOException {
                        return new URLConnection(url) {
                            @Override
                            public void connect() throws IOException {
                                System.out.println("URLTest.connect");
                            }
                        };
                    }
                };
            } else if (protocol.equals("my")) {
                return new URLStreamHandler() {
                    @Override
                    protected URLConnection openConnection(URL url) throws IOException {
                        return new URLConnection(url) {
                            @Override
                            public void connect() throws IOException {
                                System.out.println("URLTest.connect");
                            }
                        };
                    }
                };
            }
            return null;
        });

        // Проверяем разбор нестандартного URL
        URL url = new URL("chrome://mychromeextension.ru/test_dir");
        assertEquals("Протокол", "chrome", url.getProtocol());
        assertEquals("Доменное имя сайта", "mychromeextension.ru", url.getHost());
        assertEquals("Путь от корня сайта", "/test_dir", url.getPath());

        URL url2 = new URL("my://myresource.my/test_dir");
        assertEquals("Протокол", "my", url2.getProtocol());
        assertEquals("Доменное имя сайта", "myresource.my", url2.getHost());
        assertEquals("Путь от корня сайта", "/test_dir", url2.getPath());

        // Разбор стандартных URL по-прежнему работает
        url = new URL("http://yandex.ru/yandsearch?text=Java");
        assertEquals("Протокол", "http", url.getProtocol());
        assertEquals("Доменное имя сайта", "yandex.ru", url.getHost());
        assertEquals("Путь от корня сайта", "/yandsearch", url.getPath());
        assertEquals("Параметры запроса", "text=Java", url.getQuery());
        // System.out.println(url.getContent());
    }
}

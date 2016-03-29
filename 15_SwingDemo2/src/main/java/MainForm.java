import javax.swing.*;

/**
 * Основная форма приложения
 */
public class MainForm {
    private JPanel mainPanel;
    private JTextField text1;
    private JTextField text2;
    private JButton concatStringsButton;
    private JTextField result;

    public MainForm() {
        text1.setText("abcd");
        text2.setText("1234");

        concatStringsButton.addActionListener(actionEvent ->
                result.setText(text1.getText() + text2.getText()));
    }

    public static void main(String[] args) {
        // Создаём окно и задаём ему заголовок
        JFrame frame = new JFrame("Заголовок окна");
        // Создаём класс, соответствующий нашей форме
        MainForm mainForm = new MainForm();
        // Основную панель помещаем внутрь окна
        frame.setContentPane(mainForm.mainPanel);
        // Размещение компонент внутри окна
        // рекурсивное определение размеров компонент
        frame.pack();
        // Когда закрываем этот frame => закроется всё приложение
        // Обычно задаётся для "основного" окна приложения
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Показываем окно
        frame.setVisible(true);
    }
}

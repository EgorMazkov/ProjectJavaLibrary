package jFrame.print;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PrintText extends JFrame {
    public PrintText(String message) throws HeadlessException {
        super("Вывод информации");
        if (Objects.equals(message, ""))
            message = "Данных нет";
        JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
        setVisible(false);
    }
}

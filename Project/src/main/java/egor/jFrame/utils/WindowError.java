package egor.jFrame.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static egor.jFrame.utils.LaunchingANewWindow.startLibraryJFrame;

@Component
@Scope("prototype")
public class WindowError extends JFrame {
    JLabel window = new JLabel("Ошибка: Введены не правильные данные\n");
    JButton repeat = new JButton("Повторить");

    public WindowError() throws HeadlessException {
        JPanel jPanel = new JPanel(new GridLayout());

        jPanel.add(window, BorderLayout.NORTH);
        jPanel.add(repeat);

        add(jPanel, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        repeat.addActionListener(this::exit);
    }

    private void exit(ActionEvent actionEvent) {
        repeat.addActionListener(e -> {
            setVisible(false);
            startLibraryJFrame();
        });
    }
}

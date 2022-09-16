package jFrame.utils;

import jFrame.library.LibraryJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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
            LibraryJFrame libraryJFrame = new LibraryJFrame();
            libraryJFrame.setVisible(true);
            libraryJFrame.setBounds(300, 300, 1000, 500);
            libraryJFrame.setLayout(new GridLayout(3, 2, 2, 2));
            libraryJFrame.setVisible(true);
        });
    }
}

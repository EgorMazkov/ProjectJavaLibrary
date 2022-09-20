package jFrame.book;

import jFrame.print.PrintText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;
import static jFrame.utils.LaunchingANewWindow.*;

public class SearchForNumber extends JFrame {
    JLabel enterNumberBook = new JLabel("Введите номер книги: ");
    JTextField numberBook = new JTextField(10);
    JButton search = new JButton("Поиск");
    JButton back = new JButton("Вернуться назад");

    public SearchForNumber() throws HeadlessException {
        super("Поиск по номеру книги");

        JPanel buttonJPanel = new JPanel(new FlowLayout());

        buttonJPanel.add(enterNumberBook);
        buttonJPanel.add(numberBook);
        buttonJPanel.add(search);
        buttonJPanel.add(back);

        add(buttonJPanel, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        search.addActionListener(this::search);
        back.addActionListener(this::backForSearchBooks);
    }

    private void backForSearchBooks(ActionEvent actionEvent) {
        back.addActionListener(e -> {
            setVisible(false);
            startBook();
        });
    }

    private void search(ActionEvent actionEvent) {
        String message = MESSAGES_REPOSITORY.searchForNumber(numberBook.getText());
        search.addActionListener(e -> {
            PrintText printText = new PrintText(message);
            setVisible(false);
            startBook();
        });
    }
}
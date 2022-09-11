package jFrame.books;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SearchForAuthor extends JFrame {

    public SearchForAuthor() throws HeadlessException {
        JLabel window = new JLabel();
        JTextField author = new JTextField("Введите автора книги");
        JButton search = new JButton("Поиск");

        JPanel buttonJPanel = new JPanel(new FlowLayout());

        buttonJPanel.add(window);
        buttonJPanel.add(author);
        buttonJPanel.add(search);

        add(buttonJPanel, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        search.addActionListener(this::search);
    }

    private void search(ActionEvent actionEvent) {
        // TODO написать метод поиска по автору в messagesRepository
    }
}

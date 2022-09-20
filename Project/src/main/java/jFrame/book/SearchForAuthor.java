package jFrame.book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;
import static jFrame.utils.LaunchingANewWindow.*;

public class SearchForAuthor extends JFrame {

    JLabel enterNameAuthorBook = new JLabel("Введите автора книги");
    JTextField author = new JTextField(10);
    JButton search = new JButton("Поиск");
    JButton back = new JButton("Вернуться назад");

    public SearchForAuthor() throws HeadlessException {
        super("Поиск по автору книги");

        JPanel jPanel = new JPanel(new FlowLayout());

        jPanel.add(enterNameAuthorBook);
        jPanel.add(author);
        jPanel.add(search);
        jPanel.add(back);

        add(jPanel, BorderLayout.NORTH);
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
        MESSAGES_REPOSITORY.searchForAuthor(author.getText());
        search.addActionListener(e -> {
            setVisible(false);
            startBook();
        });
    }
}

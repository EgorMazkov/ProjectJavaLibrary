package jFrame.books;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;
import static jFrame.books.Books.newSearchBook;

public class SearchForAuthor extends JFrame {
        JLabel window = new JLabel();
        JLabel enterNameAuhorBook = new JLabel("Введите автора книги");
        JTextField author = new JTextField(10);
        JButton search = new JButton("Поиск");
        JButton back = new JButton("Вернуться назад");
    public SearchForAuthor() throws HeadlessException {

        JPanel buttonJPanel = new JPanel(new FlowLayout());

        buttonJPanel.add(window);
        buttonJPanel.add(enterNameAuhorBook);
        buttonJPanel.add(author);
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
            newSearchBook();
        });
    }

    private void search(ActionEvent actionEvent) {
        MESSAGES_REPOSITORY.searchForAuthor(author.getText());
        search.addActionListener(e -> {
            setVisible(false);
            newSearchBook();
        });
    }
}

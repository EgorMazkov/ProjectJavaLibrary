package jFrame.books;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SearchBooks extends JFrame {
        JLabel window = new JLabel();
        JButton searchForNumber = new JButton("Поиск по номеру книги");
        JButton searchForAuthor = new JButton("Поиск по автору книги");
        JButton listFotBooks = new JButton("Список всех Книг");

    public SearchBooks() {

        JPanel buttonPanel = new JPanel(new FlowLayout());

        buttonPanel.add(searchForAuthor);
        buttonPanel.add(searchForNumber);
        buttonPanel.add(listFotBooks);

        buttonPanel.add(window, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        searchForAuthor.addActionListener(this::searchForAuthor);
        searchForNumber.addActionListener(this::searchForNumber);
        listFotBooks.addActionListener(this::listForBooks);

    }

    private void listForBooks(ActionEvent actionEvent) {
        // TODO написать метод для вывода списка из бд на окно jframe
    }

    private void searchForNumber(ActionEvent actionEvent) {
        searchForNumber.addActionListener(e -> {
            setVisible(false);
            SearchForNumber searchForNumber = new SearchForNumber();
            searchForNumber.setBounds(300, 300, 1000, 500);
            searchForNumber.setLayout(new GridLayout(3, 2, 2, 2));
            searchForNumber.setVisible(true);
        });
    }

    private void searchForAuthor(ActionEvent actionEvent) {
        searchForAuthor.addActionListener(e -> {
            setVisible(false);
            SearchForAuthor searchForAuthor = new SearchForAuthor();
            searchForAuthor.setBounds(300, 300, 1000, 500);
            searchForAuthor.setLayout(new GridLayout(3, 2, 2, 2));
            searchForAuthor.setVisible(true);
        });
    }
}

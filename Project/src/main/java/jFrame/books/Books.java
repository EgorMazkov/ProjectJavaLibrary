package jFrame.books;

import jFrame.delivery.DeliveryJFrame;
import jFrame.print.PrintText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;
import static app.Main.newWindowJFrame;

public class Books extends JFrame {

    public static void newSearchBook() {
        Books searchBooks = new Books();
        searchBooks.setBounds(300, 300, 1000, 1000);
        searchBooks.setLayout(new GridLayout(3, 2, 2, 2));
        searchBooks.setVisible(true);
    }

    JLabel window = new JLabel();
    JButton searchForNumber = new JButton("Поиск по номеру книги");
    JButton searchForAuthor = new JButton("Поиск по автору книги");
    JButton listForBooks = new JButton("Список всех Книг");
    JButton addBooks = new JButton("Добавить новую книгу");
    JButton back = new JButton("Вернуться назад");
    JButton returnOfTheBook = new JButton("Возврат книги");
    JButton delivery = new JButton("Выдача");
    JButton listNotReturnOfTheBooks = new JButton("Книги которые не вернули");

    public Books() {

        JPanel buttonPanel = new JPanel(new FlowLayout());

        buttonPanel.add(listForBooks);
        buttonPanel.add(listNotReturnOfTheBooks);
        buttonPanel.add(delivery);
        buttonPanel.add(returnOfTheBook);
        buttonPanel.add(searchForAuthor);
        buttonPanel.add(searchForNumber);
        buttonPanel.add(addBooks);
        buttonPanel.add(back);
        add(buttonPanel, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        searchForAuthor.addActionListener(this::searchForAuthor);
        searchForNumber.addActionListener(this::searchForNumber);
        listForBooks.addActionListener(this::listForBooks);
        addBooks.addActionListener(this::addBooks);
        back.addActionListener(this::backForWindowJFrame);
        returnOfTheBook.addActionListener(this::returnBook);
        delivery.addActionListener(this::delivery);
        listNotReturnOfTheBooks.addActionListener(this::listNotReturnOfTheBooks);
    }

    private void listNotReturnOfTheBooks(ActionEvent actionEvent) {
        String message = MESSAGES_REPOSITORY.listNotReturnOfTheBooks();
        PrintText printText = new PrintText(message);
    }

    private void backForWindowJFrame(ActionEvent actionEvent) {
        back.addActionListener(e -> {
            setVisible(false);
            newWindowJFrame();
        });
    }

    private void addBooks(ActionEvent actionEvent) {
        addBooks.addActionListener(e -> {
            setVisible(false);
            AddBooks addBooks = new AddBooks();
            addBooks.setBounds(300, 300, 1000, 1000);
            addBooks.setLayout(new GridLayout(3, 2, 2, 2));
            addBooks.setVisible(true);
        });
    }

    private void listForBooks(ActionEvent actionEvent) {
        String message = MESSAGES_REPOSITORY.listForBooks();
        PrintText printText = new PrintText(message);
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

    private void returnBook(ActionEvent actionEvent) {
        returnOfTheBook.addActionListener(e -> {
            setVisible(false);
            ReturnOfTheBook returnOfTheBook = new ReturnOfTheBook();
            returnOfTheBook.setVisible(true);
            returnOfTheBook.setBounds(300, 300, 1000, 500);
            returnOfTheBook.setLayout(new GridLayout(3, 2, 2, 2));
        });
    }

    private void delivery(ActionEvent actionEvent) {
        delivery.addActionListener(e -> {
            setVisible(false);
            DeliveryJFrame deliveryJFrame = new DeliveryJFrame();
            deliveryJFrame.setBounds(300, 300, 1000, 500);
            deliveryJFrame.setLayout(new GridLayout(3, 2, 2, 2));
            deliveryJFrame.setVisible(true);
        });
    }
}

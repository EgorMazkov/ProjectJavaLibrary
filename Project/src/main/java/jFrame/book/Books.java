package jFrame.book;

import jFrame.print.PrintText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;
import static jFrame.utils.LaunchingANewWindow.*;

public class Books extends JFrame {

    JButton searchForNumber = new JButton("Поиск по номеру книги");
    JButton searchForAuthor = new JButton("Поиск по автору книги");
    JButton listForBooks = new JButton("Список всех Книг");
    JButton returnOfTheBook = new JButton("Возврат книги");
    JButton delivery = new JButton("Выдача");
    JButton listNotReturnOfTheBooks = new JButton("Книги которые не вернули");
    JButton back = new JButton("Вернуться назад");
    JButton addBooks = new JButton("Добавить книгу");
    public Books() {
        JPanel jPanel = new JPanel(new FlowLayout());

        jPanel.add(listForBooks, BorderLayout.CENTER);
        jPanel.add(listNotReturnOfTheBooks, BorderLayout.CENTER);
        jPanel.add(delivery, BorderLayout.CENTER);
        jPanel.add(returnOfTheBook, BorderLayout.CENTER);
        jPanel.add(searchForAuthor, BorderLayout.CENTER);
        jPanel.add(searchForNumber, BorderLayout.CENTER);
        jPanel.add(addBooks, BorderLayout.CENTER);
        jPanel.add(back, BorderLayout.CENTER);
        add(jPanel, BorderLayout.NORTH);
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
        String message = MESSAGES_REPOSITORY.booksThatWereNotReturned();
        PrintText printText = new PrintText(message);
    }

    private void backForWindowJFrame(ActionEvent actionEvent) {
        back.addActionListener(e -> {
            setVisible(false);
            startWindowJFrame();
        });
    }

    private void addBooks(ActionEvent actionEvent) {
        addBooks.addActionListener(e -> {
            setVisible(false);
            startAddBooks();
        });
    }

    private void listForBooks(ActionEvent actionEvent) {
        String message = MESSAGES_REPOSITORY.listForBooks();
        PrintText printText = new PrintText(message);
    }

    private void searchForNumber(ActionEvent actionEvent) {
        searchForNumber.addActionListener(e -> {
            setVisible(false);
            startSearchForNumber();
        });
    }

    private void searchForAuthor(ActionEvent actionEvent) {
        searchForAuthor.addActionListener(e -> {
            setVisible(false);
            startSearchForAuthor();
        });
    }

    private void returnBook(ActionEvent actionEvent) {
        returnOfTheBook.addActionListener(e -> {
            setVisible(false);
            startReturnOfTheBooks();
        });
    }

    private void delivery(ActionEvent actionEvent) {
        delivery.addActionListener(e -> {
            setVisible(false);
            startDelivery();
        });
    }
}

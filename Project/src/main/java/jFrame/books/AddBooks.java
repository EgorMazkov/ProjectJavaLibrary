package jFrame.books;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;
import static jFrame.books.Books.newSearchBook;

public class AddBooks extends JFrame {
    JLabel window = new JLabel("");
    JLabel enterNameBook = new JLabel("Введите название книги: ");
    JTextField nameBooks = new JTextField();
    JLabel enterBookAuthor = new JLabel("Введите автора книги: ");
    JTextField bookAuthor = new JTextField();
    JLabel enterNumberOfBooks = new JLabel("Введите количество книг: ");
    JTextField numberOfBooks = new JTextField();
    JLabel enterTheYearOfPubloshing = new JLabel("Введите год издания: ");
    JTextField theYearOfPublishingBooks = new JTextField(4);
    JButton addBooks = new JButton("Добавить");
    JButton back = new JButton("Вернуться назад");
    public AddBooks() throws HeadlessException {

        JPanel buttonJPanel = new JPanel(new GridLayout());

        buttonJPanel.add(window, BorderLayout.NORTH);
        buttonJPanel.add(enterNameBook);
        buttonJPanel.add(nameBooks);
        buttonJPanel.add(enterBookAuthor);
        buttonJPanel.add(bookAuthor);
        buttonJPanel.add(enterNumberOfBooks);
        buttonJPanel.add(numberOfBooks);
        buttonJPanel.add(enterTheYearOfPubloshing);
        buttonJPanel.add(theYearOfPublishingBooks);
        buttonJPanel.add(addBooks);
        buttonJPanel.add(back);

        add(buttonJPanel, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        addBooks.addActionListener(this::addBooksForSQL);
        back.addActionListener(this::backForSearchBooks);
    }

    private void backForSearchBooks(ActionEvent actionEvent) {
        back.addActionListener(e -> {
            setVisible(false);
            newSearchBook();
        });
    }

    private void addBooksForSQL(ActionEvent actionEvent) {
        MESSAGES_REPOSITORY.addBooks(nameBooks.getText(), numberOfBooks.getText(),
                bookAuthor.getText(), theYearOfPublishingBooks.getText());
        addBooks.addActionListener(e -> {
            setVisible(false);
            newSearchBook();
        });
    }
}

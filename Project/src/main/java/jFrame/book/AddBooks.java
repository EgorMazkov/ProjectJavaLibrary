package jFrame.book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;
import static jFrame.utils.LaunchingANewWindow.*;
import static jFrame.utils.LaunchingANewWindow.startBook;

public class AddBooks extends JFrame {
    int index = 0;
    JLabel enterNameBook = new JLabel("Введите название книги: ");
    JTextField nameBooks = new JTextField(10);
    JLabel enterBookAuthor = new JLabel("Введите автора книги: ");
    JTextField bookAuthor = new JTextField(10);
    JLabel enterNumberOfBooks = new JLabel("Введите количество книг: ");
    JTextField numberOfBooks = new JTextField(10);
    JLabel enterTheYearOfPubloshing = new JLabel("Введите год издания: ");
    JTextField theYearOfPublishingBooks = new JTextField(10);
    JButton addBooks = new JButton("Добавить данные");
    JButton back = new JButton("Вернуться назад");

    public AddBooks() throws HeadlessException {
        super("Добавление книги");

        JPanel buttonJPanel = new JPanel(new FlowLayout());

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
            startBook();
        });
    }

    private void addBooksForSQL(ActionEvent actionEvent) {
        if (index == 0) {
            index++;
            return;
        }
        MESSAGES_REPOSITORY.addBooks(nameBooks.getText(), numberOfBooks.getText(),
                bookAuthor.getText(), theYearOfPublishingBooks.getText());
        addBooks.addActionListener(e -> {
            setVisible(false);
            startBook();
            index = 0;
        });
    }
}

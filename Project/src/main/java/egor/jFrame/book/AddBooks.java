package egor.jFrame.book;

import egor.jFrame.print.PrintTableBooks;
import egor.jFrame.utils.repositories.MessagesRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


@Component
@Scope("prototype")
public class AddBooks extends JFrame {

    MessagesRepository messagesRepository;
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

    public AddBooks(MessagesRepository mr) throws HeadlessException {
        super("Добавление книги");

        messagesRepository = mr;

        JPanel buttonJPanel = new JPanel(new GridLayout(5, 2));

        buttonJPanel.add(enterNameBook);
        buttonJPanel.add(nameBooks);
        buttonJPanel.add(enterBookAuthor);
        buttonJPanel.add(bookAuthor);
        buttonJPanel.add(enterNumberOfBooks);
        buttonJPanel.add(numberOfBooks);
        buttonJPanel.add(enterTheYearOfPubloshing);
        buttonJPanel.add(theYearOfPublishingBooks);
        buttonJPanel.add(back);
        buttonJPanel.add(addBooks);

        add(buttonJPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        addBooks.addActionListener(this::addBooksForSQL);
        back.addActionListener(this::backForSearchBooks);
    }

    private void backForSearchBooks(ActionEvent actionEvent) {
        back.addActionListener(e -> {
            setVisible(false);
            String[][] arg = messagesRepository.listForBooks();
            PrintTableBooks printTableBooks = new PrintTableBooks(messagesRepository, arg, true);
        });
    }

    private void addBooksForSQL(ActionEvent actionEvent) {
        addBooks.addActionListener(e -> {
            if (index == 0) {
                index++;
                return;
            }
            messagesRepository.addBooks(nameBooks.getText(), numberOfBooks.getText(),
                    bookAuthor.getText(), theYearOfPublishingBooks.getText());
            setVisible(false);
            String[][] arg = messagesRepository.listForBooks();
            PrintTableBooks printTableBooks = new PrintTableBooks(messagesRepository, arg, true);
            index = 0;
        });
    }
}

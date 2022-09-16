package jFrame.books;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;
import static app.Main.newWindowJFrame;
import static jFrame.books.Books.newSearchBook;

public class ReturnOfTheBook extends JFrame {

    JLabel enterLibraryCard = new JLabel("Введите номер читательского билета: ");
    JTextField libraryCard = new JTextField();

    JLabel enterDateReturnOfTheBook = new JLabel("Введите дату возврата книги: ");
    JTextField dateReturnOfTheBook = new JTextField();

    JLabel enterNumberBook = new JLabel("Введите номер книги: ");
    JTextField numberBook = new JTextField();

    JButton save = new JButton("Сохранить");
    JButton back = new JButton("Вернуться назад");

    public ReturnOfTheBook() throws HeadlessException {
        JPanel jPanel = new JPanel(new GridLayout());

        jPanel.add(enterLibraryCard);
        jPanel.add(libraryCard);
        jPanel.add(enterDateReturnOfTheBook);
        jPanel.add(dateReturnOfTheBook);
        jPanel.add(enterNumberBook);
        jPanel.add(numberBook);
        jPanel.add(save);
        jPanel.add(back);

        add(jPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        save.addActionListener(this::saveReturnOfTheBook);
        back.addActionListener(this::backForWindowJFrame);
    }

    private void backForWindowJFrame(ActionEvent actionEvent) {
        back.addActionListener(e -> {
            setVisible(false);
            newWindowJFrame();
        });
    }

    private void saveReturnOfTheBook(ActionEvent actionEvent) {
        MESSAGES_REPOSITORY.returnBook(libraryCard.getText(),
                dateReturnOfTheBook.getText(),
                numberBook.getText());
        save.addActionListener(e -> {
            setVisible(false);
            newSearchBook();
        });
    }
}

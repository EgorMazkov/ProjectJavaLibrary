package jFrame.book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;
import static jFrame.utils.LaunchingANewWindow.*;

public class ReturnOfTheBook extends JFrame {
    JLabel enterLibraryCard = new JLabel("Введите номер читательского билета: ");
    JTextField libraryCard = new JTextField(10);
    JLabel enterDateReturnOfTheBook = new JLabel("Введите дату возврата книги: ");
    JTextField dateReturnOfTheBook = new JTextField(10);
    JLabel enterNumberBook = new JLabel("Введите номер книги: ");
    JTextField numberBook = new JTextField(10);
    JButton save = new JButton("Сохранить данные");
    JButton back = new JButton("Вернуться назад");

    public ReturnOfTheBook() throws HeadlessException {
        super("Возврат книги");

        JPanel jPanel = new JPanel(new FlowLayout());

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
            startBook();
        });
    }

    private void saveReturnOfTheBook(ActionEvent actionEvent) {
        save.addActionListener(e -> {
            MESSAGES_REPOSITORY.returnBook(libraryCard.getText(),
                    dateReturnOfTheBook.getText(),
                    numberBook.getText());
            setVisible(false);
            startBook();
        });
    }
}

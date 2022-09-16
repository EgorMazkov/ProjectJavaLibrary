package jFrame.delivery;

import jFrame.print.PrintText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;
import static app.Main.newWindowJFrame;
import static jFrame.books.Books.newSearchBook;

public class DeliveryJFrame extends JFrame {
    String lc, bc, doi, dod;
    JLabel window = new JLabel();
    JLabel enterLibraryCard = new JLabel("Введите номер читательского билета: ");
    JTextField libraryCard = new JTextField(10);
    JLabel enterBooksCode = new JLabel("Введите код книги: ");
    JTextField booksCode = new JTextField(10);
    JLabel enterDateOfIssue = new JLabel("Введите дату выдачи: ");
    JTextField dateOfIssue = new JTextField(30);
    JLabel enterDateOfDelivery = new JLabel("Введите дату возврата: ");
    JTextField dateOfDelivery = new JTextField(30);
    JButton update = new JButton("Сохранить");

    JButton back = new JButton("Вернуться назад");

    public DeliveryJFrame() {


        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(window, BorderLayout.SOUTH);
        buttonsPanel.add(enterLibraryCard);
        buttonsPanel.add(libraryCard);
        buttonsPanel.add(enterBooksCode);
        buttonsPanel.add(booksCode);
        buttonsPanel.add(enterDateOfIssue);
        buttonsPanel.add(dateOfIssue);
        buttonsPanel.add(enterDateOfDelivery);
        buttonsPanel.add(dateOfDelivery);
        buttonsPanel.add(update);
        buttonsPanel.add(back);

        add(buttonsPanel, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        update.addActionListener(this::Update);
        back.addActionListener(this::backForWindowJFrame);
    }

    private void backForWindowJFrame(ActionEvent actionEvent) {
        back.addActionListener(e -> {
            setVisible(false);
            newWindowJFrame();
        });
    }

    private void Update(ActionEvent actionEvent) {
        lc = libraryCard.getText();
        bc = booksCode.getText();
        doi = dateOfIssue.getText();
        dod = dateOfDelivery.getText();
        if (!doi.matches("\\d{2}\\.\\d{2}\\.\\d{4}") || !dod.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            PrintText printText = new PrintText("Ошибка ввода даты. \n Должно выглядить в виде XX.XX.XXXX");
            setVisible(false);
            newSearchBook();
        }
        MESSAGES_REPOSITORY.save(lc, bc, doi, dod);
        MESSAGES_REPOSITORY.updateDelivery(bc);
        update.addActionListener(e -> {
            setVisible(false);
            newSearchBook();
        });

    }
}

package jFrame.book;

import jFrame.print.PrintText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;
import static jFrame.utils.LaunchingANewWindow.*;

public class DeliveryBooks extends JFrame {

    JLabel enterLibraryCard = new JLabel("Введите номер чит билета: ");
    JTextField libraryCard = new JTextField(10);
    JLabel enterDateOfIssue = new JLabel("Введите дату выдачи: ");
    JTextField dateOfIssue = new JTextField(10);
    JLabel enterDateOfDelivery = new JLabel("Введите дату возврата: ");
    JTextField dateOfDelivery = new JTextField(10);
    JButton update = new JButton("Сохранить данные");
    JButton back = new JButton("Вернуться назад");
    JLabel enterBooksCode = new JLabel("Введите номер книги: ");
    JTextField booksCode = new JTextField(10);

    public DeliveryBooks() {
        super("Выдача книги");
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(enterLibraryCard, BorderLayout.WEST);
        buttonsPanel.add(libraryCard, BorderLayout.EAST);
        buttonsPanel.add(enterBooksCode, BorderLayout.WEST);
        buttonsPanel.add(booksCode, BorderLayout.EAST);
        buttonsPanel.add(enterDateOfIssue, BorderLayout.WEST);
        buttonsPanel.add(dateOfIssue, BorderLayout.EAST);
        buttonsPanel.add(enterDateOfDelivery, BorderLayout.WEST);
        buttonsPanel.add(dateOfDelivery, BorderLayout.EAST);
        buttonsPanel.add(update, BorderLayout.WEST);
        buttonsPanel.add(back, BorderLayout.EAST);

        add(buttonsPanel, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        update.addActionListener(this::Update);
        back.addActionListener(this::backForWindowJFrame);
    }

    private void backForWindowJFrame(ActionEvent actionEvent) {
        back.addActionListener(e -> {
            setVisible(false);
            startBook();
        });
    }

    private void Update(ActionEvent actionEvent) {
        if (!dateOfIssue.getText().matches("\\d{2}\\.\\d{2}\\.\\d{4}") ||
                !dateOfDelivery.getText().matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            PrintText printText = new PrintText("Ошибка ввода даты. \n Должно выглядить в виде XX.XX.XXXX");
            setVisible(false);
            startBook();
        }
        if (!MESSAGES_REPOSITORY.hasTheBookBeenIssued(libraryCard.getText(), booksCode.getText())) {
            MESSAGES_REPOSITORY.updateDelivery(booksCode.getText());
            MESSAGES_REPOSITORY.save(libraryCard.getText(), booksCode.getText(),
                    dateOfIssue.getText(), dateOfDelivery.getText());
        }
        update.addActionListener(e -> {
            setVisible(false);
            startBook();
        });
    }
}

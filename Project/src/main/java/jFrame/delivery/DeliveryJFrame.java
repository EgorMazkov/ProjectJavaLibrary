package jFrame.delivery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;

public class DeliveryJFrame extends JFrame {
    String lc, bc, doi, dod;

    public DeliveryJFrame() {
        JLabel window = new JLabel();
        JLabel enterLibraryCard = new JLabel("Введите номер читательского билета: ");
        JTextField libraryCard = new JTextField(10);
        JLabel enterBooksCode = new JLabel("Введите код книги: ");
        JTextField booksCode = new JTextField(10);
        JLabel enterDateOfIssue = new JLabel("Введите дату выдачи: ");
        JTextField dateOfIssue = new JTextField(30);
        JLabel enterDateOfDelivery = new JLabel("Введите дату возврата: ");
        JTextField dateOfDelivery = new JTextField(30);
        JButton update = new JButton("Изменить");

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

        add(buttonsPanel, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        lc = libraryCard.getText();
        bc = booksCode.getText();
        doi = dateOfIssue.getText();
        dod = dateOfDelivery.getText();
        update.addActionListener(this::Update);
    }

    private void Update(ActionEvent actionEvent) {
        MESSAGES_REPOSITORY.save(lc, bc, doi, dod);
        MESSAGES_REPOSITORY.updateDelivery(bc);
    }
}

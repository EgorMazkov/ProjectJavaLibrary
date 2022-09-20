package jFrame.ticket;

import jFrame.window.WindowJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;
import static jFrame.utils.LaunchingANewWindow.*;

public class AddTicket extends JFrame {
    JLabel enterSurname = new JLabel("Введите Фамилию: ");
    JTextField surname = new JTextField();
    JLabel enterName = new JLabel("Введите Имя: ");
    JTextField name = new JTextField();
    JLabel enterMiddleName = new JLabel("Введите Отчество: ");
    JTextField middleName = new JTextField();
    JLabel enterAddress = new JLabel("Введите адрес проживания: ");
    JTextField address = new JTextField();
    JLabel enterPhoneNumber = new JLabel("Введите номер телефона: ");
    JTextField phoneNumber = new JTextField();
    JButton addTicket = new JButton("Добавить читателя");
    JButton back = new JButton("Вернуться назад");

    public AddTicket() throws HeadlessException {
        super("Добавление читетеля");
        JPanel jPanel = new JPanel(new FlowLayout());

        jPanel.add(enterSurname);
        jPanel.add(surname);
        jPanel.add(enterName);
        jPanel.add(name);
        jPanel.add(enterMiddleName);
        jPanel.add(middleName);
        jPanel.add(enterAddress);
        jPanel.add(address);
        jPanel.add(enterPhoneNumber);
        jPanel.add(phoneNumber);
        jPanel.add(addTicket);
        jPanel.add(back);

        add(jPanel, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        addTicket.addActionListener(this::addTickets);
        back.addActionListener(this::backForWindowJFrame);
    }

    private void backForWindowJFrame(ActionEvent actionEvent) {
        back.addActionListener(e -> {
            setVisible(false);
            startTicket();
        });
    }

    private void addTickets(ActionEvent actionEvent) {
        MESSAGES_REPOSITORY.addTicket(surname.getText(),
                name.getText(),
                middleName.getName(),
                phoneNumber.getName(),
                address.getName());
        addTicket.addActionListener(e -> {
            setVisible(false);
            startTicket();
        });
    }
}

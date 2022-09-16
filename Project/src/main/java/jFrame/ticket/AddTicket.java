package jFrame.ticket;

import jFrame.window.WindowJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;
import static app.Main.newWindowJFrame;

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
    JButton addTicket = new JButton("Добавить");
    JButton back = new JButton("Вернуться назад");

    public AddTicket() throws HeadlessException {
        JPanel buttonJPanel = new JPanel(new GridLayout());

        buttonJPanel.add(enterSurname);
        buttonJPanel.add(surname);
        buttonJPanel.add(enterName);
        buttonJPanel.add(name);
        buttonJPanel.add(enterMiddleName);
        buttonJPanel.add(middleName);
        buttonJPanel.add(enterAddress);
        buttonJPanel.add(address);
        buttonJPanel.add(enterPhoneNumber);
        buttonJPanel.add(phoneNumber);
        buttonJPanel.add(addTicket);
        buttonJPanel.add(back);

        add(buttonJPanel, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        addTicket.addActionListener(this::addTickets);
        back.addActionListener(this::backForWindowJFrame);
    }

    private void backForWindowJFrame(ActionEvent actionEvent) {
        back.addActionListener(e -> {
            setVisible(false);
            Ticket ticket = new Ticket();
            ticket.setBounds(300, 300, 1000, 500);
            ticket.setLayout(new GridLayout(3, 2, 2, 2));
            ticket.setVisible(true);
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
            Ticket ticket = new Ticket();
            ticket.setBounds(300, 300, 1000, 500);
            ticket.setLayout(new GridLayout(3, 2, 2, 2));
            ticket.setVisible(true);
        });
    }
}

package jFrame.ticket;

import jFrame.window.WindowJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;
import static app.Main.newWindowJFrame;

public class DeleteTicket extends JFrame {

    JLabel enterDeleteTicket = new JLabel("Введите какой читательский билет удалить: ");
    JTextField deleteTicket = new JTextField();
    JButton back = new JButton("Вернуться назад");

    public DeleteTicket() throws HeadlessException {

        JPanel buttonJPanel = new JPanel(new GridLayout());

        buttonJPanel.add(enterDeleteTicket);
        buttonJPanel.add(deleteTicket);
        buttonJPanel.add(back);

        add(buttonJPanel, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        deleteTicket.addActionListener(this::deleleTicket);
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

    private void deleleTicket(ActionEvent actionEvent) {
        MESSAGES_REPOSITORY.delete(deleteTicket.getText());
        deleteTicket.addActionListener(e -> {
            setVisible(false);
            Ticket ticket = new Ticket();
            ticket.setBounds(300, 300, 1000, 500);
            ticket.setLayout(new GridLayout(3, 2, 2, 2));
            ticket.setVisible(true);
        });
    }
}

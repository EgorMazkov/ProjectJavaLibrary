package jFrame.ticket;

import jFrame.window.WindowJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;
import static jFrame.utils.LaunchingANewWindow.*;

public class DeleteTicket extends JFrame {
    JLabel enterDeleteTicket = new JLabel("Введите какой читательский билет удалить: ");
    JTextField deleteTickets = new JTextField();
    JButton back = new JButton("Вернуться назад");

    public DeleteTicket() throws HeadlessException {
        super("Удаление читателя");

        JPanel jPanel = new JPanel(new FlowLayout());

        jPanel.add(enterDeleteTicket);
        jPanel.add(deleteTickets);
        jPanel.add(back);

        add(jPanel, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        deleteTickets.addActionListener(this::deleleTicket);
        back.addActionListener(this::backForWindowJFrame);
    }

    private void backForWindowJFrame(ActionEvent actionEvent) {
        back.addActionListener(e -> {
            setVisible(false);
            startTicket();
        });
    }

    private void deleleTicket(ActionEvent actionEvent) {
        MESSAGES_REPOSITORY.delete(deleteTickets.getText());
        deleteTickets.addActionListener(e -> {
            setVisible(false);
            startTicket();
        });
    }
}

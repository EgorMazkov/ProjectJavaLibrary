package jFrame.ticket;

import jFrame.print.PrintText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;
import static jFrame.utils.LaunchingANewWindow.*;

public class Ticket extends JFrame {
    JButton addTicket = new JButton("Добавить читателя");
    JButton deleteTicket = new JButton("Удалить читателя");
    JButton listForTicket = new JButton("Список всех читателей");
    JButton back = new JButton("Вернуться назад");

    public Ticket() throws HeadlessException {
        JPanel jPanel = new JPanel(new FlowLayout());

        jPanel.add(addTicket);
        jPanel.add(deleteTicket);
        jPanel.add(listForTicket);
        jPanel.add(back);

        add(jPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        addTicket.addActionListener(this::addTicket);
        deleteTicket.addActionListener(this::deleteTicket);
        listForTicket.addActionListener(this::listForTicket);
        back.addActionListener(this::backForWindowJFrame);
    }

    private void listForTicket(ActionEvent actionEvent) {
        String message = MESSAGES_REPOSITORY.listForTicket();
        PrintText printText = new PrintText(message);
    }

    private void backForWindowJFrame(ActionEvent actionEvent) {
        back.addActionListener(e -> {
            setVisible(false);
            startWindowJFrame();
        });
    }

    private void addTicket(ActionEvent actionEvent) {
        addTicket.addActionListener(e -> {
            setVisible(false);
            startAddTicket();
        });
    }

    private void deleteTicket(ActionEvent actionEvent) {
        deleteTicket.addActionListener(e -> {
            setVisible(false);
            startDeleteTicket();
        });
    }
}

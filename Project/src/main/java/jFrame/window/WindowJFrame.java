package jFrame.window;

import jFrame.delivery.DeliveryJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;

public class WindowJFrame extends JFrame {
    JLabel window = new JLabel("");
    JButton books = new JButton("Книги");
    JButton employee = new JButton("Сотрудники");
    JButton delivery = new JButton("Выдача");
    JButton ticket = new JButton("Читатели");
    JButton treaty = new JButton("Данные сотрудников");


    public WindowJFrame() {
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(window, BorderLayout.NORTH);
        buttonsPanel.add(books);
        buttonsPanel.add(employee);
        buttonsPanel.add(delivery);
        buttonsPanel.add(ticket);
        buttonsPanel.add(treaty);

        add(buttonsPanel, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        delivery.addActionListener(this::delivery);
        books.addActionListener(this::bookSearch);
    }

    private void bookSearch(ActionEvent actionEvent) {
        books.addActionListener(e -> {
            setVisible(false);

        });
    }

    private void delivery(ActionEvent actionEvent) {
        delivery.addActionListener(e -> {
            setVisible(false);
            DeliveryJFrame deliveryJFrame = new DeliveryJFrame();
            deliveryJFrame.setBounds(300, 300, 1000, 500);
            deliveryJFrame.setLayout(new GridLayout(3, 2, 2, 2));
            deliveryJFrame.setVisible(true);
//            deliveryJFrame.pack();
        });
    }


}

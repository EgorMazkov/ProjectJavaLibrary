package jFrame.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static jFrame.utils.LaunchingANewWindow.*;


public class WindowJFrame extends JFrame {

    JButton books = new JButton("Книги");
    JButton employee = new JButton("Сотрудники");
    JButton ticket = new JButton("Читатели");
    JButton exit = new JButton("Выход");

    public WindowJFrame() {
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(books);
        buttonsPanel.add(employee);
        buttonsPanel.add(ticket);
        buttonsPanel.add(exit);

        add(buttonsPanel, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        books.addActionListener(this::bookSearch);
        exit.addActionListener(this::exit);
        ticket.addActionListener(this::ticket);
        employee.addActionListener(this::employee);

    }

    private void employee(ActionEvent actionEvent) {
        employee.addActionListener(e -> {
            setVisible(false);
            startEmployee();
        });
    }

    private void ticket(ActionEvent actionEvent) {
        ticket.addActionListener(e -> {
            setVisible(false);
            startTicket();
        });
    }

    private void exit(ActionEvent actionEvent) {
        exit.addActionListener(e -> {
            setVisible(false);
            startLibraryJFrame();
        });
    }

    private void bookSearch(ActionEvent actionEvent) {
        books.addActionListener(e -> {
            setVisible(false);
            startBook();
        });
    }


}

package jFrame.window;

import jFrame.books.Books;
import jFrame.employee.Employee;
import jFrame.library.LibraryJFrame;
import jFrame.ticket.Ticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


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
            Employee employee = new Employee();
            employee.setBounds(300, 300, 1000, 500);
            employee.setLayout(new GridLayout(3, 2, 2, 2));
            employee.setVisible(true);
        });
    }

    private void ticket(ActionEvent actionEvent) {
        ticket.addActionListener(e -> {
            setVisible(false);
            Ticket ticket = new Ticket();
            ticket.setBounds(300, 300, 1000, 500);
            ticket.setLayout(new GridLayout(3, 2, 2, 2));
            ticket.setVisible(true);
        });
    }

    private void exit(ActionEvent actionEvent) {
        exit.addActionListener(e -> {
            setVisible(false);
            LibraryJFrame libraryJFrame = new LibraryJFrame();
            libraryJFrame.setBounds(300, 300, 1000, 500);
            libraryJFrame.setLayout(new GridLayout(3, 2, 2, 2));
            libraryJFrame.setVisible(true);
        });
    }

    private void bookSearch(ActionEvent actionEvent) {
        books.addActionListener(e -> {
            setVisible(false);
            Books searchBooks = new Books();
            searchBooks.setBounds(300, 300, 1000, 1000);
            searchBooks.setLayout(new GridLayout(3, 2, 2, 2));
            searchBooks.setVisible(true);
        });
    }




}

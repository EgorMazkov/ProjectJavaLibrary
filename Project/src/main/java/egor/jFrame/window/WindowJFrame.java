package egor.jFrame.window;

import egor.jFrame.print.PrintEmployee;
import egor.jFrame.print.PrintTableBooks;
import egor.jFrame.utils.repositories.MessagesRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static egor.jFrame.library.LibraryJFrame.employeeOrDirectory;
import static egor.jFrame.utils.LaunchingANewWindow.*;

@Component
@Scope("prototype")
public class WindowJFrame extends JFrame {
    MessagesRepository messagesRepository;
    JButton books = new JButton("Книги");
    JButton employee = new JButton("Сотрудники");
    JButton ticket = new JButton("Читатели");
    JButton exit = new JButton("Выход");
    JButton statistics = new JButton("Статистика");

    public WindowJFrame(MessagesRepository mr) {
        super("Кнопки");
        messagesRepository = mr;

        JPanel buttonsPanel = new JPanel(new GridLayout(3, 1));

        buttonsPanel.add(books);

        if (employeeOrDirectory.equals("Сотрудник"))
            buttonsPanel.add(ticket);
        else
            buttonsPanel.add(employee);

        buttonsPanel.add(statistics);
        buttonsPanel.add(exit);

        add(buttonsPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        books.addActionListener(this::bookSearch);
        exit.addActionListener(this::exit);
        ticket.addActionListener(this::ticket);
        employee.addActionListener(this::employee);
        statistics.addActionListener(this::statictics);
    }

    private void statictics(ActionEvent actionEvent) {
        statistics.addActionListener(e -> {
            setVisible(false);
            startStatistics();
        });
    }

    private void employee(ActionEvent actionEvent) {
        employee.addActionListener(e -> {
            setVisible(false);
            PrintEmployee printEmployee = new PrintEmployee(messagesRepository);
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
            String[][] arg = messagesRepository.listForBooks();
            PrintTableBooks printTableBooks = new PrintTableBooks(messagesRepository, arg, true);
        });
    }
}

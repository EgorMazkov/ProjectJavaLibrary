package egor.jFrame.ticket;

import egor.jFrame.print.PrintText;
import egor.jFrame.utils.repositories.MessagesRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static egor.jFrame.utils.LaunchingANewWindow.startTicket;

@Component
@Scope("prototype")
public class SearchForTicket extends JFrame {
    MessagesRepository messagesRepository;
    JLabel enterSurname = new JLabel("Введите фамилию: ");
    JTextField surname = new JTextField(20);
    JButton exit = new JButton("Вернуться назад");
    JButton search = new JButton("Поиск");

    public SearchForTicket(MessagesRepository mr) throws HeadlessException {
        super("Поиск читателя по фамилии");

        messagesRepository = mr;

        JPanel jPanel = new JPanel(new GridLayout(2, 2));
        jPanel.add(enterSurname);
        jPanel.add(surname);
        jPanel.add(exit);
        jPanel.add(search);

        add(jPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        exit.addActionListener(this::exit);
        search.addActionListener(this::searhTicketForSurname);
    }

    private void searhTicketForSurname(ActionEvent actionEvent) {
        search.addActionListener(e -> {
            String message = "";
            message = messagesRepository.searchTicketForSurname(surname.getText());
            setVisible(false);
            PrintText printText = new PrintText(message);
            startTicket();
        });
    }

    private void exit(ActionEvent actionEvent) {
        exit.addActionListener(e -> {
            setVisible(false);
            startTicket();
        });
    }


}

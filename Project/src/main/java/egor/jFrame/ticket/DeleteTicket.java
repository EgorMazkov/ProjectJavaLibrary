package egor.jFrame.ticket;

import egor.jFrame.utils.repositories.MessagesRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static egor.jFrame.utils.LaunchingANewWindow.*;

@Component
@Scope("prototype")
public class DeleteTicket extends JFrame {
    MessagesRepository messagesRepository;
    JLabel enterDeleteTicket = new JLabel("Введите какой читательский билет удалить: ");
    JTextField deleteTickets = new JTextField(10);
    JButton back = new JButton("Вернуться назад");

    public DeleteTicket(MessagesRepository mr) throws HeadlessException {
        super("Удаление читателя");

        messagesRepository = mr;

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
        messagesRepository.delete(deleteTickets.getText());
        deleteTickets.addActionListener(e -> {
            setVisible(false);
            startTicket();
        });
    }
}

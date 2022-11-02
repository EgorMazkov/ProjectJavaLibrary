package egor.jFrame.ticket;

import egor.jFrame.print.PrintText;
import egor.jFrame.utils.repositories.MessagesRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

import static egor.jFrame.utils.LaunchingANewWindow.*;

@Component
@Scope("prototype")
public class SubscriptionRenewal extends JFrame {
    MessagesRepository messagesRepository;
    JLabel enterCode = new JLabel("Введите номер чит-билета: ");
    JLabel enter = new JLabel("Введите какой абонемент");
    JTextField text = new JTextField(10);
    JButton exit = new JButton("Вернуться назад");
    JButton button = new JButton("Продлить");
    JLabel labelFive = new JLabel("0 - открытие абонемента\n");
    JLabel label = new JLabel("1 - Увеличить на 1 книгу\n");
    JLabel labeltwo = new JLabel("2 - Увеличить на 5 книг\n");
    JLabel labelthree = new JLabel("3 - Увеличить на 10 книг\n");
    JLabel labelfour = new JLabel("4 - Увеличить на 20 книг\n");
    JLabel labelText = new JLabel("Список абонемента:");
    JComboBox codeTicket;

    public SubscriptionRenewal(MessagesRepository mr) {
        JPanel jPanel = new JPanel(new GridLayout(10, 2));
        messagesRepository = mr;

        String messageList = messagesRepository.listForTicket(2);
        String[] mes = messageList.split("\n");
        codeTicket = new JComboBox(mes);
        jPanel.add(enterCode);
        jPanel.add(codeTicket);
        jPanel.add(enter);
        jPanel.add(text);
        jPanel.add(exit);
        jPanel.add(button);
        jPanel.add(labelText);
        jPanel.add(labelFive);
        jPanel.add(label);
        jPanel.add(labeltwo);
        jPanel.add(labelthree);
        jPanel.add(labelfour);

        add(jPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        exit.addActionListener(this::exit);
        button.addActionListener(this::renewal);

    }

    private void renewal(ActionEvent actionEvent) {
        int renewalNumber = checkRenewal();
        if (renewalNumber != -1) {
            button.addActionListener(e -> {
                String args = (String) codeTicket.getSelectedItem();
                String[] arg = Objects.requireNonNull(args).split(" ");
                messagesRepository.closeSubscription(arg[0], "Открытый");
                if (renewalNumber != 0)
                    messagesRepository.updateRenewalTicket(arg[0], renewalNumber, 1);
                setVisible(false);
                startTicket();
            });
        } else {
            PrintText printText = new PrintText("Абонемент с таким номером не существует");
            setVisible(false);
            startSubscriptionRenewal();
        }
    }

    private int checkRenewal() {
        if (text.getText().equals("0")) {
            return 0;
        } else if (text.getText().equals("1")) {
            return 1;
        } else if (text.getText().equals("2")) {
            return 5;
        } else if (text.getText().equals("3")) {
            return 10;
        } else if (text.getText().equals("4")) {
            return 20;
        }
        return -1;
    }

    private void exit(ActionEvent actionEvent) {
        exit.addActionListener(e -> {
            setVisible(false);
            startSubscription();
        });
    }
}

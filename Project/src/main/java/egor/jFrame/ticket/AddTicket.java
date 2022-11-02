package egor.jFrame.ticket;

import egor.jFrame.print.PrintText;
import egor.jFrame.utils.repositories.MessagesRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static egor.jFrame.utils.LaunchingANewWindow.startAddTicket;
import static egor.jFrame.utils.LaunchingANewWindow.startTicket;

@Component
@Scope("prototype")
public class AddTicket extends JFrame {
    MessagesRepository messagesRepository;
    JLabel enterSurname = new JLabel("Введите Фамилию: ");
    JTextField surname = new JTextField(10);
    JLabel enterName = new JLabel("Введите Имя: ");
    JTextField name = new JTextField(10);
    JLabel enterMiddleName = new JLabel("Введите Отчество: ");
    JTextField middleName = new JTextField(10);
    JLabel enterAddress = new JLabel("Введите адрес проживания: ");
    JTextField address = new JTextField(10);
    JLabel enterPhoneNumber = new JLabel("Введите номер телефона: ");
    JTextField phoneNumber = new JTextField(10);
    JLabel enterSubscrition = new JLabel("Введите абонемент: ");
    JTextField subscription = new JTextField();
    JLabel label = new JLabel("1 - Увеличить на 1 книгу\n");
    JLabel labeltwo = new JLabel("2 - Увеличить на 5 книг\n");
    JLabel labelthree = new JLabel("3 - Увеличить на 10 книг\n");
    JLabel labelfour = new JLabel("4 - Увеличить на 20 книг\n");
    JButton addTicket = new JButton("Добавить читателя");
    JButton back = new JButton("Вернуться назад");

    public AddTicket(MessagesRepository mr) throws HeadlessException {
        super("Добавление читетеля");

        messagesRepository = mr;

        JPanel jPanel = new JPanel(new GridLayout(9, 2));

        jPanel.add(enterSurname);
        jPanel.add(surname);
        jPanel.add(enterName);
        jPanel.add(name);
        jPanel.add(enterMiddleName);
        jPanel.add(middleName);
        jPanel.add(enterAddress);
        jPanel.add(address);
        jPanel.add(enterPhoneNumber);
        jPanel.add(phoneNumber);
        jPanel.add(enterSubscrition);
        jPanel.add(subscription);
        jPanel.add(label);
        jPanel.add(labeltwo);
        jPanel.add(labelthree);
        jPanel.add(labelfour);
        jPanel.add(back);
        jPanel.add(addTicket);

        add(jPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        addTicket.addActionListener(this::addTickets);
        back.addActionListener(this::backForWindowJFrame);
    }

    private void backForWindowJFrame(ActionEvent actionEvent) {
        back.addActionListener(e -> {
            setVisible(false);
            startTicket();
        });
    }

    private void addTickets(ActionEvent actionEvent) {
        addTicket.addActionListener(e -> {
            setVisible(false);
            int sub = checkRenewal();
            if (sub == -1) {
                PrintText printText = new PrintText("Такого абонемента нет");
                startAddTicket();
            }
            if (messagesRepository.checkAddTicket(phoneNumber.getText())) {
                messagesRepository.addTicket(surname.getText(),
                        name.getText(),
                        middleName.getName(),
                        phoneNumber.getName(),
                        address.getName(),
                        sub);
            }
            startTicket();
        });
    }

    private int checkRenewal() {
        if (subscription.getText().equals("0")) {
            return 0;
        } else if (subscription.getText().equals("1")) {
            return 1;
        } else if (subscription.getText().equals("2")) {
            return 5;
        } else if (subscription.getText().equals("3")) {
            return 10;
        } else if (subscription.getText().equals("4")) {
            return 20;
        }
        return -1;
    }
}
package egor.jFrame.ticket;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static egor.jFrame.utils.LaunchingANewWindow.*;

@Component
@Scope("prototype")
public class Subscription extends JFrame {

    JButton subscriptionRenewal = new JButton("Открытие/Продление абонемента");
    JButton closeSubscription = new JButton("Закрытие абонемента");
    JButton exit = new JButton("Вернуться назад");

    public Subscription() throws HeadlessException {
        JPanel jPanel = new JPanel(new GridLayout(2, 2));

        jPanel.add(subscriptionRenewal);
        jPanel.add(closeSubscription);
        jPanel.add(exit);

        add(jPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        subscriptionRenewal.addActionListener(this::subRenewal);
        exit.addActionListener(this::exit);
        closeSubscription.addActionListener(this::close);

    }

    private void close(ActionEvent actionEvent) {
        closeSubscription.addActionListener(e -> {
            setVisible(false);
            startCloseSubscription();
        });
    }

    private void exit(ActionEvent actionEvent) {
        exit.addActionListener(e -> {
            setVisible(false);
            startTicket();
        });
    }

    private void subRenewal(ActionEvent actionEvent) {
        subscriptionRenewal.addActionListener(e -> {
            setVisible(false);
            startSubscriptionRenewal();
        });
    }
}

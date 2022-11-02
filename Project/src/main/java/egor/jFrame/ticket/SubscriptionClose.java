package egor.jFrame.ticket;

import egor.jFrame.print.PrintText;
import egor.jFrame.utils.repositories.MessagesRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static egor.jFrame.utils.LaunchingANewWindow.startSubscription;

@Component
@Scope("prototype")
public class SubscriptionClose extends JFrame {
    MessagesRepository messagesRepository;
    JLabel enterCodeTicket = new JLabel("Введите номер читательского билета: ");

    JButton exit = new JButton("Вернуться назад");
    JButton close = new JButton("Закрыть абонемент");
    JComboBox codeTicket;

    public SubscriptionClose(MessagesRepository mr) throws HeadlessException {
        super("Закрытие абонемента");

        messagesRepository = mr;

        JPanel jPanel = new JPanel(new GridLayout(2, 2));
        String message = messagesRepository.listForTicket(2);
        String[] mes = message.split("\n");
        codeTicket = new JComboBox(mes);

        jPanel.add(enterCodeTicket);
        jPanel.add(codeTicket);
        jPanel.add(exit);
        jPanel.add(close);

        add(jPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        exit.addActionListener(this::exit);
        close.addActionListener(this::close);

    }

    private void close(ActionEvent actionEvent) {
        close.addActionListener(e -> {
            setVisible(false);
            System.out.println(codeTicket.getToolTipText());
            String code = (String) codeTicket.getSelectedItem();
            String[] c = code.split(" :");
            messagesRepository.closeSubscription(String.valueOf(c[0]), "Закрыт");
            PrintText printText = new PrintText("Абонемент у читателя под номером " + c[0] + " закрыт");
            startSubscription();
        });
    }

    private void exit(ActionEvent actionEvent) {
        exit.addActionListener(e -> {
            setVisible(false);
            startSubscription();
        });
    }
}

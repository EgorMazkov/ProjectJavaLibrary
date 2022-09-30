package jFrame.ticket;

import jFrame.print.PrintText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;
import static jFrame.utils.LaunchingANewWindow.startTicket;

public class ListOfBooksThatReadersHaveNotReturned extends JFrame {
    JLabel enterIdTicket = new JLabel("Введите номер читателя: ");
    JTextField idTicket = new JTextField();
    JButton search = new JButton("Поиск");

    public ListOfBooksThatReadersHaveNotReturned() throws HeadlessException {
        JPanel jPanel = new JPanel(new FlowLayout());

        jPanel.add(enterIdTicket);
        jPanel.add(idTicket);
        jPanel.add(search);

        add(jPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        search.addActionListener(this::searchForListOfBooks);
    }

    private void searchForListOfBooks(ActionEvent actionEvent) {
        String message = MESSAGES_REPOSITORY.listOfBooksThatReadersHaveNotReturned(idTicket.getText());
        PrintText printText = new PrintText(message);
        search.addActionListener(e -> {
            setVisible(false);
            startTicket();
        });
    }
}

package jFrame.ticket;

import jFrame.print.PrintText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;
import static jFrame.utils.LaunchingANewWindow.startTicket;

public class ListOfBooksThatReadersHaveNotReturned extends JFrame {
    JLabel enterIdTicket = new JLabel("Введите номер читателя: ");
    JTextField idTicket = new JTextField(5);
    JButton search = new JButton("Поиск");
    JButton exit = new JButton("Вернуться назад");

    public ListOfBooksThatReadersHaveNotReturned() throws HeadlessException {
        JPanel jPanel = new JPanel(new FlowLayout());

        jPanel.add(enterIdTicket);
        jPanel.add(idTicket);
        jPanel.add(search);
        jPanel.add(exit);

        add(jPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        search.addActionListener(this::searchForListOfBooks);
        // TODO creat method exit
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


/*
| чит билет  | номер книги | Название книги | дата возврата |
  1 (Мазков) |      1      | Изучаем JAVA   |   03,10,2022  |
*/
/*

КНИГИ -> список всех книг
Общее количество книг в библиотеке: <number>
\ Номер книги \ название книги \ Автор \ год издания \ кол-во книг \
\      1      \
*/
/*
| Общее кол-во книг которые не вернули | Всего книг |
 */
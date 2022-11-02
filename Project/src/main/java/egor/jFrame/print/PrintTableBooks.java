package egor.jFrame.print;

import egor.jFrame.utils.repositories.MessagesRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static egor.app.Main.ATotalOfBooksWereTaken;
import static egor.jFrame.utils.LaunchingANewWindow.*;

public class PrintTableBooks extends JFrame {
    int length = 0;
    MessagesRepository messagesRepository;
    JLabel listBooks = new JLabel("Список всех книг");
    JLabel listBooksDelivery = new JLabel("Список книг которые не вернули");
    JButton exit = new JButton("Вернуться назад");
    JButton delivery = new JButton("Выдача");
    JButton returnOfTheBook = new JButton("Возврат книги");
    JButton addBooks = new JButton("Добавить книгу");


    public PrintTableBooks(MessagesRepository mr, String[][] args, boolean check) {
        messagesRepository = mr;

        String[] nameColumnsTableBooks = new String[]{"Номер_книги",
                "Название_книги",
                "Количество_книг",
                "Автор",
                "Год_издания"};

        String[] nameColumnsTableDelivery = new String[]{
                "Код_выдачи",
                "Чит_билет",
                "Код_книги",
                "Дата_выдачи",
                "Дата_возврата"
        };

        Box contents = new Box(BoxLayout.Y_AXIS);
        JPanel jPanel = new JPanel(new GridLayout(2, 2));

        if (check) {
            jPanel.add(delivery);
            jPanel.add(returnOfTheBook);
            jPanel.add(addBooks);
        } else {
            args = messagesRepository.listForBooks();
            jPanel.add(new JLabel("Всего взято книг: " + ATotalOfBooksWereTaken));
            if (ATotalOfBooksWereTaken != 0) {
                contents.add(listBooksDelivery);
                jPanel.add(new JLabel("Всего вернули книг: " + (ATotalOfBooksWereTaken - args.length)));
            }
        }
        jPanel.add(exit);
        for (String[] sortingArg : args) {
            length += Integer.parseInt(sortingArg[2]);
        }
        contents.add(listBooks);
        contents.add(new JLabel("Общее количество книг: " + length));
        contents.add(new JScrollPane(new JTable(args, nameColumnsTableBooks)));
        String[][] arg = messagesRepository.listForDelivery();
        if (arg != null)
            contents.add(new JScrollPane(new JTable(arg, nameColumnsTableDelivery)));


        contents.add(jPanel);
        setContentPane(contents);
        setBounds(0, 0, screenWidth, screenHeight);
        setVisible(true);

        returnOfTheBook.addActionListener(this::returnBooks);
        addBooks.addActionListener(this::addBook);
        delivery.addActionListener(this::delivery);

        if (check) {
            exit.addActionListener(this::exit);
        } else {
            exit.addActionListener(this::exitStat);
        }
    }

    private void exitStat(ActionEvent actionEvent) {
        exit.addActionListener(e -> {
            setVisible(false);
            startStatistics();
        });
    }

    private void addBook(ActionEvent actionEvent) {
        addBooks.addActionListener(e -> {
            setVisible(false);
            startAddBooks();
        });
    }

    private void returnBooks(ActionEvent actionEvent) {
        returnOfTheBook.addActionListener(e -> {
            setVisible(false);
            startReturnOfTheBooks();
        });
    }

    private void delivery(ActionEvent actionEvent) {
        delivery.addActionListener(e -> {
            setVisible(false);
            startDelivery();
        });
    }

    private void exit(ActionEvent actionEvent) {
        exit.addActionListener(e -> {
            setVisible(false);
            startWindowJFrame();
        });
    }
}

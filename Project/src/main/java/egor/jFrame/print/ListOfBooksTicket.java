package egor.jFrame.print;

import egor.jFrame.utils.repositories.MessagesRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.Objects;

import static egor.jFrame.utils.LaunchingANewWindow.*;

public class ListOfBooksTicket extends JFrame {
    MessagesRepository messagesRepository;
    JButton returnBooks = new JButton("Вернуть книгу");
    JButton exit = new JButton("Вернуться назад");
    String libraryCard;
    JComboBox listBooks;

    public ListOfBooksTicket(MessagesRepository mr, String[][] arg) throws HeadlessException {
        super("Список книг взятых читателем");
        messagesRepository = mr;
        libraryCard = arg[0][1];

        String[] nameColumnsTable = new String[]{
                "Код_выдачи",
                "Чит_билет",
                "Код_книги",
                "Дата_выдачи",
                "Дата_возврата"
        };

        String[] nameBooks = new String[arg.length];
        for (int i = 0; i < arg.length; i++) {
            nameBooks[i] = arg[i][2];
        }
        listBooks = new JComboBox(nameBooks);
        Box contents = new Box(BoxLayout.Y_AXIS);
        JPanel jPanel = new JPanel(new GridLayout(2, 2));
        jPanel.add(listBooks);
        jPanel.add(returnBooks);
        jPanel.add(exit);

        contents.add(new JScrollPane(new JTable(arg, nameColumnsTable)));
        contents.add(new JLabel("Выберите какую книгу вернуть"));
        contents.add(jPanel);

        setContentPane(contents);
        setBounds(0, 0, screenWidth, screenHeight);
        setVisible(true);

        returnBooks.addActionListener(this::returnBooks);
        exit.addActionListener(this::exit);
    }

    private void exit(ActionEvent actionEvent) {
        exit.addActionListener(e -> {
            setVisible(false);
            startTicket();
        });
    }

    private String[][] sorting(String[][] args) {
        int q = 0;
        String[][] string = new String[args.length][5];
        for (int i = 1; i < args.length + 1; i++) {
            for (int j = 0; true; j++) {
                if (i == Integer.parseInt(args[j][0])) {
                    string[q] = args[j];
                    q++;
                    break;
                }
            }
        }
        return string;
    }

    private void returnBooks(ActionEvent actionEvent) {
        returnBooks.addActionListener(e -> {
            setVisible(false);
            String args = (String) listBooks.getSelectedItem();
            String[] nameBooks = Objects.requireNonNull(args).split(" ");
            messagesRepository.returnBook(libraryCard, new Date().toString(), nameBooks[0]);
            startTicket();
        });
    }
}

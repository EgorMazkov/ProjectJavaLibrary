package egor.jFrame.book;

import egor.jFrame.print.PrintTableBooks;
import egor.jFrame.print.PrintText;
import egor.jFrame.utils.repositories.MessagesRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

import static egor.app.Main.ATotalOfBooksWereTaken;

@Component
@Scope("prototype")
public class DeliveryBooks extends JFrame {
    Calendar calendar = new GregorianCalendar();
    SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy.MM.dd");
    String dateToday = formattedDate.format(calendar.getTime());
    MessagesRepository messagesRepository;
    JLabel enterLibraryCard = new JLabel("Введите номер чит билета: ");
    JTextField libraryCard = new JTextField(10);
    JLabel enterDateOfIssue = new JLabel("Дата выдачи");
    JTextField dateOfIssue = new JTextField(dateToday);
    JLabel enterDateOfDelivery = new JLabel("Выберите на сколько дней(максимально 10 дней): ");
    JTextField dateOfDelivery = new JTextField(10);
    JButton update = new JButton("Сохранить данные");
    JButton back = new JButton("Вернуться назад");
    JLabel enterBooksCode = new JLabel("Введите номер книги: ");
    JLabel enterBooksCodeBox = new JLabel("Или выберите из списка: ");
    JTextField booksCode = new JTextField(10);
    JComboBox codeBooks;
    JComboBox howManyDays;

    public DeliveryBooks(MessagesRepository mr) {
        super("Выдача книги");

        messagesRepository = mr;

        String[] howMany = new String[4];

        howMany[0] = "1";
        howMany[1] = "2";
        howMany[2] = "5";
        howMany[3] = "10";

        String[][] arg = messagesRepository.listForBooks();
        String[] nameBooks = new String[arg.length];

        for (int i = 0; i < arg.length; i++) {
            nameBooks[i] = arg[i][0] + " - " + arg[i][1];
        }

        codeBooks = new JComboBox(nameBooks);
        JPanel buttonsPanel = new JPanel(new GridLayout(6, 2));
        howManyDays = new JComboBox(howMany);

        buttonsPanel.add(enterLibraryCard);
        buttonsPanel.add(libraryCard);
        buttonsPanel.add(enterBooksCode);
        buttonsPanel.add(booksCode);
        buttonsPanel.add(enterBooksCodeBox);
        buttonsPanel.add(codeBooks);
        buttonsPanel.add(enterDateOfIssue);
        buttonsPanel.add(dateOfIssue);
        buttonsPanel.add(enterDateOfDelivery);
        buttonsPanel.add(howManyDays);
        buttonsPanel.add(back);
        buttonsPanel.add(update);

        add(buttonsPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        update.addActionListener(this::Update);
        back.addActionListener(this::backForWindowJFrame);
    }

    private void backForWindowJFrame(ActionEvent actionEvent) {
        back.addActionListener(e -> {
            setVisible(false);
            String[][] arg = messagesRepository.listForBooks();
            PrintTableBooks printTableBooks = new PrintTableBooks(messagesRepository, arg, true);
        });
    }

    private void Update(ActionEvent actionEvent) {
        update.addActionListener(e -> {
            String args = (String) codeBooks.getSelectedItem();
            String[] nameBooks = Objects.requireNonNull(args).split(" ");
            if (booksCode.getText().equals("")) {
                deliveryBooksTicket(nameBooks[0]);
            } else {
                deliveryBooksTicket(booksCode.getText());
            }
            setVisible(false);
            ATotalOfBooksWereTaken++;
            String[][] arg = messagesRepository.listForBooks();
            PrintTableBooks printTableBooks = new PrintTableBooks(messagesRepository, arg, true);
        });
    }

    private void deliveryBooksTicket(String name) {
        String date = invoiceOfTheReturnDate();
        if (!messagesRepository.hasTheBookBeenIssued(libraryCard.getText(), name)) {
            if (!messagesRepository.updateRenewalTicket(libraryCard.getText(), 0, 2)) {
                PrintText printText = new PrintText("У данного читателя абонемент закрыт");
                String[][] arg = messagesRepository.listForBooks();
                PrintTableBooks printTableBooks = new PrintTableBooks(messagesRepository, arg, true);
                return;
            }
            messagesRepository.updateDelivery(name);
            messagesRepository.save(libraryCard.getText(), name,
                    dateOfIssue.getText(), date);
        } else {
            PrintText printText = new PrintText("Данная книга была уже выдана данному читателю");
        }
    }

    private String invoiceOfTheReturnDate() {
        String date;
        int dateInt;
        String[] dateBefore = dateOfIssue.getText().split("\\.");
        dateInt = Integer.parseInt(dateBefore[2]);
        dateInt += Integer.parseInt((String) howManyDays.getSelectedItem());
        date = dateBefore[0] + "." + dateBefore[1] + "." + dateInt;
        System.out.println(date);
        return date;
    }
}

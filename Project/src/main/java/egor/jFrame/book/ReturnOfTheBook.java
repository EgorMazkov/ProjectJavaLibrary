package egor.jFrame.book;

import egor.jFrame.print.PrintTableBooks;
import egor.jFrame.utils.repositories.MessagesRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@Component
@Scope("prototype")
public class ReturnOfTheBook extends JFrame {
    MessagesRepository messagesRepository;
    JLabel enterLibraryCard = new JLabel("Введите номер чит-билета: ");
    JTextField libraryCard = new JTextField(10);
    JLabel enterDateReturnOfTheBook = new JLabel("Введите дату возврата книги: ");
    JTextField dateReturnOfTheBook = new JTextField(10);
    JLabel enterNumberBook = new JLabel("Введите номер книги: ");
    JTextField numberBook = new JTextField(10);
    JButton save = new JButton("Сохранить данные");
    JButton back = new JButton("Вернуться назад");

    public ReturnOfTheBook(MessagesRepository mr) throws HeadlessException {
        super("Возврат книги");

        messagesRepository = mr;

        JPanel jPanel = new JPanel(new GridLayout(4, 2));

        jPanel.add(enterLibraryCard);
        jPanel.add(libraryCard);
        jPanel.add(enterDateReturnOfTheBook);
        jPanel.add(dateReturnOfTheBook);
        jPanel.add(enterNumberBook);
        jPanel.add(numberBook);
        jPanel.add(back);
        jPanel.add(save);

        add(jPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        save.addActionListener(this::saveReturnOfTheBook);
        back.addActionListener(this::backForWindowJFrame);
    }

    private void backForWindowJFrame(ActionEvent actionEvent) {
        back.addActionListener(e -> {
            setVisible(false);
            String[][] arg = messagesRepository.listForBooks();
            PrintTableBooks printTableBooks = new PrintTableBooks(messagesRepository, arg, true);
        });
    }

    private void saveReturnOfTheBook(ActionEvent actionEvent) {
        save.addActionListener(e -> {
            messagesRepository.returnBook(libraryCard.getText(),
                    dateReturnOfTheBook.getText(),
                    numberBook.getText());
            setVisible(false);
            String[][] arg = messagesRepository.listForBooks();
            PrintTableBooks printTableBooks = new PrintTableBooks(messagesRepository, arg, true);
        });
    }
}
